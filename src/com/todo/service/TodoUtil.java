package com.todo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void checkItem(TodoList list) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[완료 체크]\n완료 체크할 항목의 번호를 입력하시오 > ");
		
		int num = sc.nextInt();
		
		if(list.checkItem(num) > 0) {
			System.out.print("완료 체크되었습니다.");
			
		}
	}
	
	public static void checkItemMulti(TodoList list) {
		List<Integer> numList = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		System.out.println("[완료 체크]");
		while(true) {
			System.out.print("체크할 항목의 번호를 입력하시오 (종료하려면 -1 을 입력하시오) > ");	
			int num = sc.nextInt();
			if(num == -1) break;
			numList.add(num);
		}

		for(int n : numList) {
			int num = list.checkItem(n);
		}
		System.out.println("입력하신 번호들을 삭제했습니다."); 
	}
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"+"제목 > ");
		title = sc.next();
		if( list.isDuplicate(title) ) {
			System.out.print("제목이 중복됩니다!");
			return;
		}
		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		System.out.print("장소 > ");
		String place = sc.nextLine().trim();
		
		System.out.print("준비물 > ");
		String preparement = sc.nextLine().trim();
		
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date,place,preparement );
		int n = list.addItem(t);
		if(n > 0) {
			System.out.print("추가되었습니다.");
			if(list.getCategories().contains(category) == false ) {
				list.addCate(t);
			}
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n삭제할 항목의 번호를 입력하시오 > ");
		
		int num = sc.nextInt();
		
		if(l.deleteItem(num) > 0) System.out.print("삭제되었습니다.");
	}
	
	public static void deleteItemMulti(TodoList l) {
		List<Integer> numList = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		System.out.println("[항목 삭제]");
		while(true) {
			System.out.print("삭제할 항목의 번호를 입력하시오 (종료하려면 -1 을 입력하시오) > ");	
			int num = sc.nextInt();
			if(num == -1) break;
			numList.add(num);
		}

		for(int n : numList) {
			int num = l.deleteItem(n);
		}
		System.out.println("입력하신 번호들을 삭제했습니다."); 
	}


	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();

		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		
		System.out.print("장소 > ");
		String place = sc.nextLine().trim();
		
		System.out.print("준비물 > ");
		String preparement = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		

		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date,place,preparement);
		t.setId(index);
		if(l.updateItem(t) > 0) System.out.println("수정되었습니다.");
	}

	public static void listAll(TodoList l,String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount() );
		for(TodoItem item : l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
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
	
//	public static void loadList(TodoList l, String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			String s;
//			while(true) {
//				s = br.readLine();
//				if(s == null) break;
//				StringTokenizer stk = new StringTokenizer(s,"##");
//				while(stk.hasMoreTokens()){
//					TodoItem i = new TodoItem(stk.nextToken(),stk.nextToken(),stk.nextToken(),stk.nextToken() );
//					i.setCurrent_date(stk.nextToken());
//					l.addItem(i);
//				}
//			}
//			br.close();
//		}
//		catch(IOException o) {
//			
//		}
//	}
	
	public static void findlist(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword) ) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
	}
	
	public static void findCateList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getListCategory(keyword) ) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories() ) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n",count);
	}
	
}
