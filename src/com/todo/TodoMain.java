package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		TodoUtil.loadList(l, "todolistapp.txt");
		boolean isList = false;
		boolean quit = false;
		do {
			//Menu.displaymenu();
			isList = false;
			String choice = Menu.prompt();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;

			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				TodoUtil.saveList(l, "todolistapp.txt");
				break;

			default:
				System.out.println(choice + " is not exist. Enter help to see menu.");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
