package com.example.clickajob;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EmployerMain extends Activity {

	ListView ListViewMain;
	   
    String[] employer = {"Post Job Vacancy", "View Candidate", "Logout"};
    ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_main);
        
        Intent intent = getIntent();
    	final String cid = intent.getStringExtra("cid");

        ListViewMain = (ListView) findViewById(R.id.ListViewMain);
        adapter = new ArrayAdapter<String>(EmployerMain.this, android.R.layout.simple_list_item_1, employer);
        ListViewMain.setAdapter(adapter);

        ListViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ListViewMain = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(EmployerMain.this, ListViewMain, Toast.LENGTH_SHORT).show();

                if (position == 0) {
                    //code specific to list item > postjob
                    Intent myIntent = new Intent(EmployerMain.this, Postjob.class);
                    myIntent.putExtra("cid", cid);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    //code specific to list item > viewcandidate
                    Intent myIntent = new Intent(EmployerMain.this, EmployerPost.class);
                    myIntent.putExtra("cid", cid);
                    startActivityForResult(myIntent, 0);
                }
                
                if (position == 2) {
                    //code specific to list item > Logout
                	logout();
                }
            }
        });
        
    }
        
	  
	 public void onBackPressed(){
	    	logout();
	    }
	 
		private void logout() {
			// TODO Auto-generated method stub
			//code specific to list item > Logout
	    	AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
	    	        EmployerMain.this);

	    	// Setting Dialog Title
	    	alertDialog2.setTitle("Employer");

	    	// Setting Dialog Message
	    	alertDialog2.setMessage("Are you sure want to log out?");

	    	// Setting Positive "Yes" Btn
	    	alertDialog2.setPositiveButton("YES",
	    	        new DialogInterface.OnClickListener() {
	    	            public void onClick(DialogInterface dialog, int which) {
	    	            	    Intent myIntent = new Intent(EmployerMain.this, MainActivity.class);
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
        }
	
        
