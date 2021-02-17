package com.example.clickajob;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;
import com.example.clickajob.R.menu;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WorkExperience extends Activity {

    Button bSave,bClear;
    EditText etOrg, etDes,etDurFrom, etDurTo,etRole;
    RadioButton rb, rbPrev,rbCur;
    RadioGroup rg;
    SharedPreferences pref;
    String employ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        etOrg = (EditText) findViewById(R.id.etOrg);
        etDes = (EditText) findViewById(R.id.etDes);
        etDurFrom = (EditText) findViewById(R.id.etDurFrom);
        etDurTo = (EditText) findViewById(R.id.etDurTo);
        rbPrev = (RadioButton) findViewById(R.id.rbPrev);
        rbCur = (RadioButton) findViewById(R.id.rbCur);
        etRole = (EditText) findViewById(R.id.etRole);
        rg = (RadioGroup) findViewById(R.id.rg);
        bSave = (Button) findViewById(R.id.bSave);
        bClear = (Button) findViewById(R.id.bClear);
        
 pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        
        if(pref.getString("org", null)!= null){
			etOrg.setText(pref.getString("org", null));
		}
        
        if(pref.getString("des", null)!= null){
			etDes.setText(pref.getString("des", null));
		}
        if(pref.getString("dur1", null)!= null){
			etDurFrom.setText(pref.getString("dur1", null));
		}
        if(pref.getString("dur2", null)!= null){
			etDurTo.setText(pref.getString("dur2", null));
		}
        if(pref.getString("workrole", null)!= null){
			etRole.setText(pref.getString("workrole", null));
		}
       
        if(pref.getString("employ", null)!= null){
//			int employid = rg.getCheckedRadioButtonId();
//        	employ = ((RadioButton) this.findViewById(rg.getCheckedRadioButtonId())).getText().toString();
//        	rb.setText(pref.getString("employ", null));
		}
        	

        
bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectID = rg.getCheckedRadioButtonId();
				rb = (RadioButton)findViewById (selectID);
				employ = rb.getText().toString();
				
				if (rg.getCheckedRadioButtonId() == -1)
				{
				  // no radio buttons are checked
					showMsg("Please check your input.");
				}
//				else
//				{
//				  \\ one of the radio buttons is checked
//				}
				
				Editor editor = pref.edit();
				editor.putString("org", etOrg.getText().toString());
				editor.putString("des", etDes.getText().toString());
				editor.putString("dur1", etDurFrom.getText().toString());
				editor.putString("dur2", etDurTo.getText().toString());
				editor.putString("employ", employ);
				editor.putString("workrole", etRole.getText().toString());
				editor.commit(); 
				
							
				Intent myIntent = new Intent( WorkExperience.this, InterestSkill.class);
                startActivityForResult(myIntent, 0);
				
			}
		});
        
        bClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etOrg.getText().clear();
				etDes.getText().clear();
				etDurFrom.getText().clear();
				etDurTo.getText().clear();
				etRole.getText().clear();
			}
		});
    }

	private void showMsg(String msg) {
		Toast toast = Toast.makeText(WorkExperience.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
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
