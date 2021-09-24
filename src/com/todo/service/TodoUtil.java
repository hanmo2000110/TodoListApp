package com.todo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter new item's name. > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("it is already exist.");
			return;
		}
		System.out.print("Enter new item's category. > ");
		sc.nextLine();
		category = sc.nextLine();
		
		System.out.print("Enter new item's description. > ");	
		desc = sc.nextLine();
		
		System.out.print("Enter new item's due date. > ");	
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("the item is added.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name of item which will be deleted. > ");
		
		int num = sc.nextInt();
		int i = 1;
		for (TodoItem item : l.getList()) {
			if (i == num) {
				System.out.print( i + item.toString() +"\nare you sure to delete this item? (y/n) > ");
				String yon = sc.next();
				if(yon.equals("y")) {
					l.deleteItem(item);
					System.out.println("the item is deleted");
				}
					
				break;
			}
			i++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of item which will be modified. > ");
		int num = sc.nextInt();
		if (l.length() < num) {
			System.out.println("The item is not exist.");
			return;
		}

		System.out.print("Enter the new name of item which is modifying. > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("The name is already exist.");
			return;
		}
		
		System.out.print("Enter the new description of item which is modifying. > ");
		sc.nextLine();
		String new_description = sc.nextLine();
		
		System.out.print("Enter the new category of item which is modifying. > ");
		String new_category = sc.nextLine();
		
		System.out.print("Enter the new due date of item which is modifying. > ");
		String new_due_date = sc.nextLine();
		int i = 1;
		for (TodoItem item : l.getList()) {
			if (i == num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("The item is modified.");
			}
			i++;
		}

	}

	public static void listAll(TodoList l) {
		int i = 1;
		System.out.println("[All List, " + l.length() + " items]");
		for (TodoItem item : l.getList()) {
			System.out.println(i + ". [" + item.getCategory() + "]: " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			i++;
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			FileWriter fw = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				fw.write(item.toSaveString());
			}
			fw.close();
		}
		catch(IOException o) {
			
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String s;
			while(true) {
				s = br.readLine();
				if(s == null) break;
				StringTokenizer stk = new StringTokenizer(s,"##");
				while(stk.hasMoreTokens()){
					TodoItem i = new TodoItem(stk.nextToken(),stk.nextToken(),stk.nextToken(),stk.nextToken() );
					i.setCurrent_date(stk.nextToken());
					l.addItem(i);
				}
			}
			br.close();
		}
		catch(IOException o) {
			
		}
	}
	
}
