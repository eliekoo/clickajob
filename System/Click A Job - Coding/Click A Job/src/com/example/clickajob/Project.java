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

public class Project extends Activity {

    Button bSave,bClear;
    EditText etProjTitle, etDesc,etProjFrom,etProjTo,etProjRole;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        bSave = (Button) findViewById(R.id.bSave);
        bClear = (Button) findViewById(R.id.bClear);
        etProjTitle = (EditText) findViewById(R.id.etProjTitle);
        etDesc = (EditText) findViewById(R.id.etDesc);
        etProjFrom = (EditText) findViewById(R.id.etProjFrom);
        etProjTo = (EditText) findViewById(R.id.etProjTo);
        etProjRole = (EditText) findViewById(R.id.etProjRole);
        
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        
        if(pref.getString("ptitle", null)!= null){
        	etProjTitle.setText(pref.getString("ptitle", null));
		}
        
        if(pref.getString("pdesc", null)!= null){
        	etDesc.setText(pref.getString("pdesc", null));
		}
        if(pref.getString("pdate1", null)!= null){
        	etProjFrom.setText(pref.getString("pdate1", null));
		}
        if(pref.getString("pdate2", null)!= null){
        	etProjTo.setText(pref.getString("pdate2", null));
		}
        if(pref.getString("prole", null)!= null){
        	etProjRole.setText(pref.getString("prole", null));
		}
        
bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
								
				Editor editor = pref.edit();
				editor.putString("ptitle", etProjTitle.getText().toString());
				editor.putString("pdesc", etDesc.getText().toString());
				editor.putString("pdate1", etProjFrom.getText().toString());
				editor.putString("pdate2", etProjTo.getText().toString());
				editor.putString("prole", etProjRole.getText().toString());
				editor.commit(); 
				
							
				Intent myIntent = new Intent(Project.this, Reference.class);
                startActivityForResult(myIntent, 0);
				
			}
		});
        
        bClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etProjTitle.getText().clear();
				etDesc.getText().clear();
				etProjFrom.getText().clear();
				etProjTo.getText().clear();
				etProjRole.getText().clear();
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