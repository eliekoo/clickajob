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
import android.widget.RadioButton;

public class InterestSkill extends Activity {

    Button bSave,bClear;
    EditText etInterest1, etInterest2,etInterest3, etSkill,etStrength;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_skill);
        
        bSave = (Button) findViewById(R.id.bSave);
        bClear = (Button) findViewById(R.id.bClear);
        etInterest1 = (EditText) findViewById(R.id.etInterest1);
        etInterest2 = (EditText) findViewById(R.id.etInterest2);
        etInterest3 = (EditText) findViewById(R.id.etInterest3);
        etSkill = (EditText) findViewById(R.id.etSkill);
        etStrength = (EditText) findViewById(R.id.etStrength);
        
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        
        if(pref.getString("in1", null)!= null){
			etInterest1.setText(pref.getString("in1", null));
		}
        
        if(pref.getString("in2", null)!= null){
        	etInterest2.setText(pref.getString("in2", null));
		}
        if(pref.getString("in3", null)!= null){
        	etInterest3.setText(pref.getString("in3", null));
		}
        if(pref.getString("skill", null)!= null){
			etSkill.setText(pref.getString("skill", null));
		}
        if(pref.getString("strength", null)!= null){
			etStrength.setText(pref.getString("strength", null));
		}
        
bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
								
				Editor editor = pref.edit();
				editor.putString("in1", etInterest1.getText().toString());
				editor.putString("in2", etInterest2.getText().toString());
				editor.putString("in3", etInterest3.getText().toString());
				editor.putString("skill", etSkill.getText().toString());
				editor.putString("strength", etStrength.getText().toString());
				editor.commit(); 
				
							
				Intent myIntent = new Intent(InterestSkill.this, Project.class);
                startActivityForResult(myIntent, 0);
				
			}
		});
        
        bClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etInterest1.getText().clear();
				etInterest2.getText().clear();
				etInterest3.getText().clear();
				etSkill.getText().clear();
				etStrength.getText().clear();
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
