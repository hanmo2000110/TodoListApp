package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;
	private int num;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
		num = 0;
	}

	public void addItem(TodoItem t) {
		list.add(t);
		num++;
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
		num--;
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n[전체 목록]");
		int i = 1;
		for (TodoItem myitem : list) {
//			System.out.println(myitem.getTitle() + " " + myitem.getDesc());
			System.out.println(i + ". [" + myitem.getCategory() + "]: " + myitem.getTitle() + " - " + myitem.getDesc() + " - " + myitem.getDue_date() + " - " + myitem.getCurrent_date());
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
}
