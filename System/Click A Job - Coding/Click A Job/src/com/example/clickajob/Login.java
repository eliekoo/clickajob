package com.example.clickajob;

import java.util.concurrent.ExecutionException;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity{

    private Button bLogin;
    String ids;
    private EditText userid, pwd;
    private TextView tvRegisterLink;
    private ProgressDialog dialog;
    private SharedPreferences pref;
    
//    private static final String TAG_ID = "ids";

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
        userid = (EditText) findViewById(R.id.userid);
        pwd = (EditText) findViewById(R.id.pwd);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        if(pref.getString("userid", null)!= null){
			userid.setText(pref.getString("userid", null));
		}
		
		if(pref.getString("pwd", null)!= null){		
			pwd.setText(pref.getString("pwd", null));
		}		
		
     // view products click event
     		bLogin.setOnClickListener(new View.OnClickListener() {
     			
     			@Override
     			public void onClick(View view) {
     				if(verify(userid.getText().toString(),pwd.getText().toString())){ 
    					
    				    String method = "login";  
//    				    BackgroundTask backgroundTask = new BackgroundTask(Login.this); 
    				    BackgroundTask backgroundTask = new BackgroundTask();
//    				    backgroundTask.execute(method,id.getText().toString(),pwd.getText().toString());
    					try {
    						String reply = backgroundTask.execute(method,userid.getText().toString(),pwd.getText().toString()).get();
    						String x = reply.trim().substring(0, 1);
    						
    						dialog = new ProgressDialog(Login.this);
    						dialog.setTitle("Please wait...");
    						dialog.setMessage("Logging in...");
    						dialog.setIndeterminate(false);
    						dialog.setCancelable(true);
    						dialog.show();
    						
    						if(x.equals("y")){
    							Editor editor = pref.edit();
    		    	        	editor.putString("userid", userid.getText().toString());
    		    	        	editor.putString("pwd", pwd.getText().toString() );
    		    	        	
    		    	        	editor.commit();
   		    	        	
    		    	        	String id = userid.getText().toString();
    		    	        	showMsg( id + ", Welcome back!");
    							Intent intent = new Intent(Login.this, EmployeeMain.class);	
    							intent.putExtra("userid", id);
//    							showMsg(id);
    				            startActivity(intent);
    						}
    						else if (x.equals("n")){
    							showMsg("Login Error. Please check your ID and password.");
    						}
						
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (ExecutionException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}catch (Exception e){
    						showMsg("Connection Error.");
    					}
    		        }
    		    }
    		});

   
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching create new product activity
				Intent i = new Intent(Login.this, Register.class);
				startActivity(i);
				
			}
		});
	}


    private boolean verify(String id, String pwd){
		if(id.trim().length()<1){
			showMsg("Please check your User ID.");
			return false;
		}
		else if(pwd.trim().length()<1){
			showMsg("Empty password is not acceptable.");
			return false;
		}
		else{
			return true;
		}
	}
	
	private void showMsg(String msg) {
		Toast toast = Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}

}