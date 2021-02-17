package com.example.clickajob;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class JobMessage {
	public String title,salary,date,location;
	public Drawable profilepic;
	

	public JobMessage( String title, String date, String location, String salary) {
		super();

		this.title=title;
		this.date=date;
		this.location = location;
		this.salary=salary;
	}
}