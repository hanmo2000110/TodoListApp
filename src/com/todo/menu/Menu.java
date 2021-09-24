package com.todo.menu;

import java.util.Scanner;

public class Menu {

    public static void displaymenu()
    {
        System.out.println("\n<ToDoList 관리 명령어 사용법>");
        System.out.println("1. add - 아이템 추가");
        System.out.println("2. del - 아이템 삭제");
        System.out.println("3. edit - 아이템 수정");
        System.out.println("4. ls - 전체 목록");
        System.out.println("5. ls_name_asc - 제목순 정렬");
        System.out.println("6. ls_name_desc - 제목역순 정렬");
        System.out.println("7. ls_date- 날짜순 정렬");
        System.out.println("8. ls_date- 날짜역순 정렬");
        System.out.println("9. find <key word> - 키워드로 타이틀과 설명에서 검색");
        System.out.println("10. find_cate <key word> - 키워드를 카테고리에서 검색");
        System.out.println("11. exit - 종료");
        
    }
    public static String prompt() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println();
		System.out.print("Command > ");
		String choice = sc.nextLine();
		return choice;
    }
}
