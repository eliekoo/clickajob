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

public class Other extends Activity {
	
	EditText ettitle, etoDesc;
	SharedPreferences pref;
	Button bSave,bClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        
        bSave = (Button) findViewById(R.id.bSave);
        bClear = (Button) findViewById(R.id.bClear);
        ettitle = (EditText) findViewById(R.id.ettitle);
        etoDesc = (EditText) findViewById(R.id.etoDesc);
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        
        if(pref.getString("otitle", null)!= null){
        	ettitle.setText(pref.getString("otitle", null));
		}
        
        if(pref.getString("odesc", null)!= null){
        	etoDesc.setText(pref.getString("odesc", null));
		}
        
bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
								
				Editor editor = pref.edit();
				editor.putString("otitle", ettitle.getText().toString());
				editor.putString("odesc", etoDesc.getText().toString());

				editor.commit(); 
				
							
				Intent myIntent = new Intent(Other.this, EmployeeView.class);
                startActivityForResult(myIntent, 0);
				
			}
		});
        
        bClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ettitle.getText().clear();
				etoDesc.getText().clear();

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
