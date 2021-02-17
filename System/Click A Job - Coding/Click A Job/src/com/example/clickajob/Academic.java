package com.example.clickajob;


import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;
import com.example.clickajob.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Academic extends Activity {

    Button bSave,bClear;
    EditText etCourse, etSchool,etCGPA,etYear;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);

        Intent intent = getIntent();
    	final String id = intent.getStringExtra("userid");
    	
//    	showMsg("employee main: "+id);

        bSave = (Button) findViewById(R.id.bSave);
        bClear = (Button) findViewById(R.id.bClear);
        etCourse = (EditText) findViewById(R.id.etCourse);
        etSchool = (EditText) findViewById(R.id.etSchool);
        etCGPA = (EditText) findViewById(R.id.etCGPA);
        etYear = (EditText)findViewById(R.id.etYear);
        
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        
        if(pref.getString("course", null)!= null){
			etCourse.setText(pref.getString("course", null));
		}
        
        if(pref.getString("school", null)!= null){
			etSchool.setText(pref.getString("school", null));
		}
        if(pref.getString("CGPA", null)!= null){
			etCGPA.setText(pref.getString("CGPA", null));
		}
        if(pref.getString("year", null)!= null){
			etYear.setText(pref.getString("year", null));
		}
        
 bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor editor = pref.edit();
				editor.putString("course", etCourse.getText().toString());
				editor.putString("school", etSchool.getText().toString());
				editor.putString("CGPA", etCGPA.getText().toString());
				editor.putString("year", etYear.getText().toString());
				editor.commit(); 
				
				Intent myIntent = new Intent(Academic.this, WorkExperience.class);
                startActivityForResult(myIntent, 0);
				
			}
		});
        
        bClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etCourse.getText().clear();
				etSchool.getText().clear();
				etCGPA.getText().clear();
				etYear.getText().clear();
			}
		});
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.backtomenu:
                startActivity(new Intent(this, EmployeeMain.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
