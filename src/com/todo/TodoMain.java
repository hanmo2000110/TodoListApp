package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
//		l.importData("todolistapp.txt");
		
		boolean isList = false;
		boolean quit = false;
		String key;
		do {
			//Menu.displaymenu();
			isList = false;
			String choice = Menu.prompt();
			 
			key = "";
			if( choice.contains("find ") ) { 
				String keyword[] = choice.split(" ");
				choice = keyword[0];
				key = keyword[1];
			}
			else if( choice.contains("find_cate ") ) { 
				String keyword[] = choice.split(" ");
				choice = keyword[0];
				key = keyword[1];
			}
			
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
				TodoUtil.listAll(l,"id",1);
				break;

			case "ls_name":
				System.out.print("\n제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",1);
				isList = true;
				break;

			case "ls_name_desc":
				System.out.print("\n제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",0);
				isList = true;
				break;
				
			case "ls_date":
				System.out.print("\n날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",1);
				isList = true;
				break;
				
			case "ls_date_desc":
				System.out.print("\n날짜역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",0);
				isList = true;
				break;

			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find":
				TodoUtil.findlist(l, key);
				break;
				
			case "find_cate":
				TodoUtil.findCateList(l, key);
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				TodoUtil.saveList(l, "todolistapp.txt");
				System.out.print("모든 데이터들이 저장되었습니다.");
				break;

			default:
				System.out.println(choice + " is not exist. Enter help to see menu.");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
