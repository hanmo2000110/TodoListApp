package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;
	private int num;
	Connection conn;
	public TodoList() {
		this.list = new ArrayList<TodoItem>();
		this.conn = DbConnect. getConnection();
		num = 0;
	}

	
	public int getCount() { 
		Statement stmt;
		int count=0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql); 
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	
	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, category, current_date, due_date)"+ " values (?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,t.getTitle()); 
			pstmt.setString(2,t.getDesc()); 
			pstmt.setString(3,t.getCategory()); 
			pstmt.setString(4,t.getCurrent_date()); 
			pstmt.setString(5,t.getDue_date());
			count = pstmt.executeUpdate(); 
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id=?;"    ;
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,index);
			count = pstmt.executeUpdate(); 
			pstmt.close();
		}
		catch(SQLException e) {
			
		}
		return count;
	}

	public int updateItem(TodoItem t ) {
		String sql = "update list set title=?, memo=?, category=?, current_date=?, due_date=? where id = ?;"    ;
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc()); 
			pstmt.setString(3,t.getCategory()); 
			pstmt.setString(4,t.getCurrent_date()); 
			pstmt.setString(5,t.getDue_date());
			pstmt.setInt(6,t.getId());
			count = pstmt.executeUpdate(); 
			pstmt.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>(); 
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title"); 
				String description = rs.getString("memo"); 
				String category = rs.getString("category"); 
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				 
				TodoItem t = new TodoItem(title, description, category, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);	
				 
			}
			stmt.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return list;


	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("[All List, " + length() + " items]");
		int i = 1;
		for (TodoItem myitem : list) {
//			System.out.println(myitem.getTitle() + " " + myitem.getDesc());
			System.out.println(i + ". [" + myitem.getTitle() + "]: " + myitem.getDesc() + " - " + myitem.getCategory() + " - " + myitem.getDue_date() + " - " + myitem.getCurrent_date());
			i++;
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	
	public int length() {
		return num;
	}
	
	public void find(String keyword) {
		int i = 1,count = 0; 
		for(TodoItem item : list) {
			if(item.iscontain(keyword)) {
				count++;
				System.out.println(i + item.toString());
			}
			i++;
		}
		System.out.println(count + " items found.");
	}
	
	public void findCate(String keyword) {
		int i = 1,count = 0; 
		for(TodoItem item : list) {
			if(item.iscontainCate(keyword)) {
				count++;
				System.out.println(i + item.toString());
			}
			i++;
		}
		System.out.println(count + " items found.");
	}

	public int sortCate() {
		int count = 0,i=0;
		HashSet<String> s = new HashSet<String>();
		
		for(TodoItem item : list) {
			s.add(item.getCategory());
		}
		
		for(String cate : s) {
			System.out.print(cate);
			count++;
			i++;
			if(s.size() != i) System.out.print(" / ");
		}
		
		return count;
	}
	
	
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, memo, category, current_date, due_date)"+" values (?,?,?,?,?);";
			int records = 0;
			while( ( line = br.readLine() ) != null ) {
				StringTokenizer st = new StringTokenizer(line, "##"); 
				String title = st.nextToken(); 
				String description = st.nextToken(); 
				String category = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken(); 
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,title);
				pstmt.setString(2,description); 
				pstmt.setString(3,category); 
				pstmt.setString(4,current_date); 
				pstmt.setString(5,due_date);
				int count = pstmt.executeUpdate(); 
				if(count > 0) records++; 
				pstmt.close();
			}
			System.out.println(records + " records read!!"); 
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("category"));
			}
			
		}
		catch(SQLException e) {
			
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title"); 
				String description = rs.getString("memo"); 
				String category = rs.getString("category"); 
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				TodoItem t = new TodoItem(title, description, category, due_date);
				t.setId(id);
				list.add(t);
			}
			
		}
		catch(SQLException e) {
			
		}
		return list;
	}
	
	
	
	public ArrayList<TodoItem> getList(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		System.out.print(keyword);
		keyword = "%"+keyword+"%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("title");
				String desc = rs.getString("memo");
				String category = rs.getString("category");
				String createdate = rs.getString("current_date");
				String duedate = rs.getString("due_date");
				TodoItem t = new TodoItem(name,desc,category,createdate,duedate);
				t.setId(rs.getInt("id"));
				list.add(t);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby,int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY " + orderby;
			if(ordering == 0) sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("title");
				String desc = rs.getString("memo");
				String category = rs.getString("category");
				String createdate = rs.getString("current_date");
				String duedate = rs.getString("due_date");
				TodoItem t = new TodoItem(name,desc,category,createdate,duedate);
				t.setId(rs.getInt("id"));
				list.add(t);
			}
		}
		catch(SQLException e) {
			
		}
		return list;
	}
	
}
