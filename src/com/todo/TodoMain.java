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
		String key;
		do {
			//Menu.displaymenu();
			isList = false;
			String choice = Menu.pro mpt();
			
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
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.print("\n제목순으로 정렬하였습니다.");
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				System.out.print("\n제목역순으로 정렬하였습니다.");
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.print("\n날짜순으로 정렬하였습니다.");
				l.sortByDate();
				isList = true;
				break;
				
			case "ls_date_desc":
				System.out.print("\n날짜역순으로 정렬하였습니다.");
				l.sortByDate();
				l.reverseList();
				isList = true;
				break;

			case "ls_cate":
				int n = l.sortCate();
				if(n > 1)
					System.out.print("\nthere are " + n + " categories.");
				else 
					System.out.print("\nthere is " + n + " categories.");
				break;
				
			case "find":
				l.find( key );
				break;
				
			case "find_cate":
				l.findCate( key );
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
