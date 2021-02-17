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

public class Reference extends Activity {

    Button bSave,bClear;
    EditText etRefName, etRefDes, etRefOrg,etRefPhone,etRefEmail;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        bSave = (Button) findViewById(R.id.bSave);
        bClear = (Button) findViewById(R.id.bClear);
        etRefName = (EditText) findViewById(R.id.etRefName);
        etRefDes = (EditText) findViewById(R.id.etRefDes);
        etRefOrg = (EditText)findViewById(R.id.etRefOrg);
        etRefPhone = (EditText) findViewById(R.id.etRefPhone);
        etRefEmail = (EditText) findViewById(R.id.etRefEmail);
        
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);

        if(pref.getString("rname", null)!= null){
        	etRefName.setText(pref.getString("rname", null));
		}
        
        if(pref.getString("rdes", null)!= null){
        	etRefDes.setText(pref.getString("rdes", null));
		}
        if(pref.getString("rorg", null)!= null){
        	etRefOrg.setText(pref.getString("rorg", null));
		}
        if(pref.getString("rphone", null)!= null){
        	etRefPhone.setText(pref.getString("rphone", null));
		}
        if(pref.getString("remail", null)!= null){
        	etRefEmail.setText(pref.getString("remail", null));
		}

bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
								
				Editor editor = pref.edit();
				editor.putString("rname", etRefName.getText().toString());
				editor.putString("rdes", etRefDes.getText().toString());
				editor.putString("rorg", etRefOrg.getText().toString());
				editor.putString("rphone", etRefPhone.getText().toString());
				editor.putString("remail", etRefEmail.getText().toString());
				editor.commit(); 
				
							
				Intent myIntent = new Intent(Reference.this, Other.class);
                startActivityForResult(myIntent, 0);
				
			}
		});
        
        bClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etRefName.getText().clear();
				etRefDes.getText().clear();
				etRefOrg.getText().clear();
				etRefPhone.getText().clear();
				etRefEmail.getText().clear();
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
