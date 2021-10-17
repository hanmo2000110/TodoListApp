package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
    private String title;
    private String desc;
    private String current_date;
    private SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String category;
    private String due_date;
    private String complete_check;
    private String place;
    private String preparement;

    public TodoItem(String title, String desc, String category, String due_date,String place,String preparement){
        this.title=title;
        this.desc=desc;
        this.current_date=f.format( new Date() );
        this.category = category;
        this.due_date = due_date;
        this.complete_check = "0";
        this.place = place;
        this.preparement = preparement;
    }
    public TodoItem(String title, String desc, String category, String due_date,String createdate,String place,String preparement){
        this.title=title;
        this.desc=desc;
        this.current_date=createdate;
        this.category = category;
        this.due_date = due_date;
        this.complete_check = "0";
        this.place = place;
        this.preparement = preparement;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String toSaveString() {
        return title + "##" + desc + "##" + category + "##" + due_date + "##" + current_date + "\n";
    }
    
    public String toString() {
    	String check;
    	if(complete_check.equals("1")) {
    		check = "Y";
    	}
    	else {
    		check = "N";
    	}
    	return id + ". [" + getCategory() + "]: " + getTitle() + " - " + getDesc() + " - " + getPlace() + " - " + getPreparement() + " - " + getDue_date() + " - " + getCurrent_date() + " - " + check;
    }
    
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
    
	public boolean iscontain(String keyword) {
		boolean returnValue = false;
		if(title.contains(keyword)) returnValue = true;
		else if(desc.contains(keyword)) returnValue = true;
		
		return returnValue;
	}
	
	public boolean iscontainCate(String keyword) {
		boolean returnValue = false;
		if(category.contains(keyword)) returnValue = true;
		
		return returnValue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String isComplete_check() {
		return complete_check;
	}
	public void setComplete_check(String complete_check) {
		this.complete_check = complete_check;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPreparement() {
		return preparement;
	}
	public void setPreparement(String preparement) {
		this.preparement = preparement;
	}
	public String getComplete_check() {
		return complete_check;
	}
}
