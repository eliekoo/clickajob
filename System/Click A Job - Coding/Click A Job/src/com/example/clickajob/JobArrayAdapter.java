package com.example.clickajob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JobArrayAdapter extends ArrayAdapter {


	private TextView salary,title,date,location;
	private List jobMessageList = new ArrayList();
	private RelativeLayout singleMessageContainer;
	private String postids;

	public void add(JobMessage object) {
		jobMessageList.add(object);
		super.add(object);
	}

	public JobArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.jobMessageList.size();
	}

	public JobMessage getItem(int index) {
		return (JobMessage) this.jobMessageList.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.singlemsg, parent, false);
		}
		

		salary = (TextView) row.findViewById(R.id.salary);
		title = (TextView) row.findViewById(R.id.jobtitle);
		location = (TextView) row.findViewById(R.id.location);
		date = (TextView) row.findViewById(R.id.date);
		singleMessageContainer = (RelativeLayout) row.findViewById(R.id.container);
		
		JobMessage chatMessageObj = getItem(position);	
		
		title.setText(chatMessageObj.title);
		date.setText(chatMessageObj.date);
		location.setText(chatMessageObj.location);
		salary.setText(chatMessageObj.salary);


		singleMessageContainer.setGravity(Gravity.LEFT);
		return row;
	}


}