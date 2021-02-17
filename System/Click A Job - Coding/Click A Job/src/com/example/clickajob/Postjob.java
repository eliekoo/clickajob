package com.example.clickajob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;
import com.example.clickajob.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Postjob extends Activity {

	 Button bPost,bDiscard;
	
	private EditText cname, cpnum,cemail,jlocation,jobtitle, jreq, jdesc, jsalary, jdate;
	SharedPreferences pref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postjob);
		
		
		cname = (EditText) findViewById(R.id.etRName);
        cpnum = (EditText) findViewById(R.id.etRPhone);
        cemail = (EditText) findViewById(R.id.etREmail);
        jlocation = (EditText)findViewById(R.id.etCaddress);
        jobtitle = (EditText) findViewById(R.id.jobtitle);
        jreq = (EditText) findViewById(R.id.etreq);
        jdesc = (EditText) findViewById(R.id.etjdesc);
        jdate = (EditText) findViewById(R.id.etjdate);
        jsalary = (EditText) findViewById(R.id.etjsalary);
        
        bPost = (Button)findViewById(R.id.bPost);
        bDiscard= (Button)findViewById(R.id.bDiscard);

		pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
		
		if(pref.getString("cname", null)!= null){
			cname.setText(pref.getString("cname", null));
		}
        
        if(pref.getString("cpnum", null)!= null){
			cpnum.setText(pref.getString("cpnum", null));
		}
        if(pref.getString("cemail", null)!= null){
			cemail.setText(pref.getString("cemail", null));
		}
        if(pref.getString("jlocation", null)!= null){
			jlocation.setText(pref.getString("jlocation", null));
		}
        
        if(pref.getString("jtitle", null)!= null){
			jobtitle.setText(pref.getString("jtitle", null));
		}
        if(pref.getString("jreq", null)!= null){
			jreq.setText(pref.getString("jreq", null));
		}
        if(pref.getString("jdesc", null)!= null){
			jdesc.setText(pref.getString("jdesc", null));
		}
        if(pref.getString("jdate", null)!= null){
			jdate.setText(pref.getString("jdate", null));
		}
        if(pref.getString("jsalary", null)!= null){
			jsalary.setText(pref.getString("jsalary", null));
		}
        
bPost.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor editor = pref.edit();
				editor.putString("cname", cname.getText().toString());
				editor.putString("cpnum", cpnum.getText().toString());
				editor.putString("cemail", cemail.getText().toString());
				editor.putString("jlocation", jlocation.getText().toString());
				editor.putString("jtitle", jobtitle.getText().toString());
				editor.putString("jreq", jreq.getText().toString());
				editor.putString("jdesc", jdesc.getText().toString());
				editor.putString("jdate", jdate.getText().toString());
				editor.putString("jsalary", jsalary.getText().toString());
				editor.commit(); 
				
				Builder dialog = new AlertDialog.Builder(Postjob.this);

		    	// Setting Dialog Title
		    	dialog.setTitle("Post Vacancy?");

		    	// Setting Dialog Message
		    	dialog.setMessage("Are you sure want to update and post this vacancy?");

		    	dialog.setPositiveButton("YES",
		    	        new DialogInterface.OnClickListener() {
		    	            public void onClick(DialogInterface dialog, int which) {
		    	            	
		    	            	insertToDatabase();
		    	            	
		    	            	
		    	            	ProgressDialog dialog2 = new ProgressDialog(Postjob.this);
	    						dialog2.setTitle("Please wait...");
	    						dialog2.setMessage("Posting vacancy...");
	    						dialog2.setIndeterminate(false);
	    						dialog2.setCancelable(true);
	    						dialog2.show();
	    						dialog2.dismiss();
		    	            	
		    	            }

						});

		    	dialog.setNegativeButton("NO",
		    	        new DialogInterface.OnClickListener() {
		    	            public void onClick(DialogInterface dialog, int which) {
		    	                // Write your code here to execute after dialog
		    	                Toast.makeText(getApplicationContext(),
		    	                        "Post vacancy cancelled", Toast.LENGTH_SHORT)
		    	                        .show();
		    	                dialog.cancel();
			}
		});
		    	dialog.show();
			}
});

bDiscard.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		Builder dialog2 = new AlertDialog.Builder(Postjob.this);

    	// Setting Dialog Title
    	dialog2.setTitle("Discard?");

    	// Setting Dialog Message
    	dialog2.setMessage("Are you sure want to discard this message?");

    	// Setting Positive "Yes" Btn
    	dialog2.setPositiveButton("YES",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	            	Toast.makeText(getApplicationContext(),
    	                        "Message had been discard.", Toast.LENGTH_SHORT)
    	                        .show();
    	                dialog.cancel();
    	            	Intent myIntent = new Intent(Postjob.this, EmployerMain.class);
    	            	startActivity(myIntent);
    	                    
    	            }
    	        });
    	// Setting Negative "NO" Btn
    	dialog2.setNegativeButton("NO",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	                // Write your code here to execute after dialog
    	                Toast.makeText(getApplicationContext(),
    	                        "Discard cancelled", Toast.LENGTH_SHORT)
    	                        .show();
    	                dialog.cancel();
    	            }
    	        });

    	// Showing Alert Dialog
    	dialog2.show();
	}
});


	}
	
	 private void insertToDatabase() {
			
	        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
	            @Override
	            protected String doInBackground(String... params) {
	      		
	 
	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	                
//	                pic, name, phone, email, address, course, school, CGPA, year ,org, des ,dur1 ,dur2,employ,workrole;
//	            	String in1,in2,in3 ,skill ,strength , ptitle ,pdesc ,pdate1, pdate2 ,prole, otitle,odesc ,rname ,rdes ,rorg,rphone ,remail ;
	                               
	                String cid = pref.getString("cid", null);
	                String cname = pref.getString("cname", null);
					String cphone = pref.getString("cpnum", null);
					String cemail = pref.getString("cemail", null);
					String jlocation = pref.getString("jlocation", null);
			        String jtitle = pref.getString("jtitle", null);
					String jreq = pref.getString("jreq", null);
					String jdesc = pref.getString("jdesc", null);
					String jdate = pref.getString("jdate", null);
					String jsalary = pref.getString("jsalary", null);
	                
					nameValuePairs.add(new BasicNameValuePair("cid", cid));
	                nameValuePairs.add(new BasicNameValuePair("cname", cname));
	                nameValuePairs.add(new BasicNameValuePair("cpnum", cphone));
	                nameValuePairs.add(new BasicNameValuePair("cemail", cemail));
	                nameValuePairs.add(new BasicNameValuePair("jlocation", jlocation));
	                nameValuePairs.add(new BasicNameValuePair("jtitle", jtitle));
	                nameValuePairs.add(new BasicNameValuePair("jreq", jreq));
	                nameValuePairs.add(new BasicNameValuePair("jdesc", jdesc));
	                nameValuePairs.add(new BasicNameValuePair("jdate", jdate));
	                nameValuePairs.add(new BasicNameValuePair("jsalary", jsalary));

	 
	                try {
	                    HttpClient httpClient = new DefaultHttpClient();
	                    HttpPost httpPost = new HttpPost(
	                            "http://192.168.43.78/clickajob/postjob.php");
	                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	 
	                    HttpResponse response = httpClient.execute(httpPost);
	 
	                    HttpEntity entity = response.getEntity();
	 
	          
	                } catch (ClientProtocolException e) {
	 
	                } catch (IOException e) {
	 
	                }
	                return "success";
	            }
	 
	            @Override
	            protected void onPostExecute(String result) {
	                super.onPostExecute(result);
	                if(result == "success"){
	                	showMsg("Vacancy had been post successfully.");
	                	Intent myIntent = new Intent(Postjob.this, EmployerPost.class);
//	                	myIntent.putExtra("vid", vid);
	                    startActivity(myIntent);
	                }
	                else if(result == "failure"){
	                	showMsg("Error post vacancy.");
	                }
	                	
	            }
	        }
	        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
	        sendPostReqAsyncTask.execute();
	    
	}

	 private void showMsg(String msg) {
			Toast toast = Toast.makeText(Postjob.this, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
			toast.show();
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.postjob, menu);
		return true;
	}

}
