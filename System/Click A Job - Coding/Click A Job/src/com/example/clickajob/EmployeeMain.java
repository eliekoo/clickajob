package com.example.clickajob;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;
import com.example.clickajob.R.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EmployeeMain extends Activity {

//	Intent ids;
//	private static final String TAG_ID = "id";
    ListView ListViewMain;
   
    String[] resume = {"Contact Information", "Academic details", "Work Experience", "Interest & Skill", "Project", "References", "Other", "View / Share","Apply Job", "Log out"};
    ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);

        Intent intent = getIntent();
    	final String id = intent.getStringExtra("userid");
    	
//    	showMsg("employee main: "+id);
    	
        ListViewMain = (ListView) findViewById(R.id.ListViewMain);
        adapter = new ArrayAdapter<String>(EmployeeMain.this, android.R.layout.simple_list_item_1, resume);
        ListViewMain.setAdapter(adapter);

        ListViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long ids) {
                String ListViewMain = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(EmployeeMain.this, ListViewMain, Toast.LENGTH_SHORT).show();

                if (position == 0) {
                    //code specific to list item > contact info
                    Intent myIntent = new Intent(EmployeeMain.this, ContactInfo.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    //code specific to list item > academic
                    Intent myIntent = new Intent(EmployeeMain.this, Academic.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    //code specific to list item > work experience
                    Intent myIntent = new Intent(EmployeeMain.this, WorkExperience.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 3) {
                    //code specific to list item > interest & skill
                    Intent myIntent = new Intent(EmployeeMain.this, InterestSkill.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 4) {
                    //code specific to list item > project
                    Intent myIntent = new Intent(EmployeeMain.this, Project.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }
                if (position == 5) {
                    //code specific to first list item > reference
                    Intent myIntent = new Intent(EmployeeMain.this, Reference.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }
                if (position == 6) {
                    //code specific to first list item > Other
                    Intent myIntent = new Intent(EmployeeMain.this, Other.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }
                
                if (position == 7) {
                    //code specific to first list item > Other
                    Intent myIntent = new Intent(EmployeeMain.this, EmployeeView.class);
                    myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
                }


                if (position == 8) {   	
//                	Intent intent = getIntent();
                	
                	Intent myIntent = new Intent(EmployeeMain.this, EmployeeApply.class);
                	
                	myIntent.putExtra("userid", id);
                    startActivityForResult(myIntent, 0);
//                    showMsg("click apply: " + id);
                }
                if (position == 9) {   	
                	
                	logout();
                }
            }
        });
        
    }
    
    public void onBackPressed(){
    	logout();
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
                startActivity(new Intent(this, EmployeeProfile.class));
                return true;
            case R.id.MyResume:
                //startActivity(new Intent(this, EmployeeMain.class));
                return true;
            case R.id.MyView:
                startActivity(new Intent(this, EmployeeView.class));
                return true;
                
            case R.id.LogOut:
            	logout();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
	private void logout() {
		// TODO Auto-generated method stub
		//code specific to list item > Logout
    	AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
    	        EmployeeMain.this);

    	// Setting Dialog Title
    	alertDialog2.setTitle("Confirm log out?");

    	// Setting Dialog Message
    	alertDialog2.setMessage("Are you sure want to log out?");

    	// Setting Positive "Yes" Btn
    	alertDialog2.setPositiveButton("YES",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	            	    Intent myIntent = new Intent(EmployeeMain.this, MainActivity.class);
    	                    myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
    	                    startActivity(myIntent);
    	                    finish();
    	            }
    	        });
    	// Setting Negative "NO" Btn
    	alertDialog2.setNegativeButton("NO",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	                // Write your code here to execute after dialog
    	                Toast.makeText(getApplicationContext(),
    	                        "Log out cancelled", Toast.LENGTH_SHORT)
    	                        .show();
    	                dialog.cancel();
    	            }
    	        });

    	// Showing Alert Dialog
    	alertDialog2.show();
	}
	private void showMsg(String msg) {
		Toast toast = Toast.makeText(EmployeeMain.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}
}