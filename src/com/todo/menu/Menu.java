package com.todo.menu;

import java.util.Scanner;

public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. add");
        System.out.println("2. del");
        System.out.println("3. edit");
        System.out.println("4. ls");
        System.out.println("5. ls_name_asc");
        System.out.println("6. ls_name_desc");
        System.out.println("7. ls_date");
        System.out.println("8. help");
        System.out.println("9. exit");
        
    }
    public static String prompt() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println();
		System.out.print("Command > ");
		String choice = sc.next();
		return choice;
    }
}
