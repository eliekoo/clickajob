package com.example.clickajob;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;
import com.example.clickajob.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmployeeProfile extends Activity  implements View.OnClickListener{

    Button bback;
    TextView etName,etPhone,etEmail, etUserID, etPassword;
    SharedPreferences pref;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        etName = (TextView) findViewById(R.id.etName);
        etPhone = (TextView) findViewById(R.id.etPhone);
        etEmail = (TextView) findViewById(R.id.etEmail);
        etUserID = (TextView) findViewById(R.id.etUserID);
        etPassword = (TextView) findViewById(R.id.etPassword);
        
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
		
		String name = pref.getString("name", null);
		String pnum = pref.getString("pnum", null);
		String email = pref.getString("email", null);
		String id = pref.getString("id", null);
				
		etName.setText(name);
		etPhone.setText(pnum);
		etEmail.setText(email);
		etUserID.setText(id);
		

        bback = (Button)findViewById(R.id.bback);
       
        bback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
									
				Intent myIntent = new Intent(EmployeeProfile.this, EmployeeMain.class);
                startActivityForResult(myIntent, 0);
				
			}
		});

        }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bback:
                Intent intent = new Intent(EmployeeProfile.this,EmployeeMain.class);
                startActivity(intent);
                break;
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.MyProfile:
                //startActivity(new Intent(this, EmployeeProfile.class));
                return true;
            case R.id.MyResume:
                startActivity(new Intent(this, EmployeeMain.class));
                return true;
            case R.id.MyView:
                startActivity(new Intent(this, View.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
