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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter new item's name. > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("it is already exist.");
			return;
		}
		
		System.out.print("Enter new item's description. > ");
		sc.nextLine();
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the name of item which will be deleted. > ");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name of item which will be modified. > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
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
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("The item is modified.");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "]: " + item.getDesc() + " (" + item.getCurrent_date() + ")");
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
					TodoItem i = new TodoItem(stk.nextToken(),stk.nextToken());
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
