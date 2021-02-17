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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity{
//
//    Button bRegister;
//    EditText etName,etPhone,etEmail, etUserID, etPassword, etPassword2;
//    TextView tvterm;
	private EditText name, pnum,email,id, pwd, confirmpwd;
	private Button confirm;
	private TextView tvterm;
	private CheckBox cbterm;
	SharedPreferences pref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		name = (EditText)findViewById(R.id.name);
		id = (EditText)findViewById(R.id.id);
		pwd = (EditText)findViewById(R.id.pwd);
		confirmpwd = (EditText)findViewById(R.id.confirmpwd);
		pnum = (EditText)findViewById(R.id.pnum);
		cbterm = (CheckBox) findViewById(R.id.cbterm);
		
		email = (EditText)findViewById(R.id.email);
		confirm = (Button)findViewById(R.id.bRegister);
		
		tvterm = (TextView)findViewById(R.id.tvterm);
		
		pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
		
		//go main page after login
		confirm.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				
				Editor editor = pref.edit();
		    	editor.putString("name", name.getText().toString());
		    	editor.putString("pnum", pnum.getText().toString());
		    	editor.putString("email", email.getText().toString());
		    	editor.putString("id", id.getText().toString());
		    	editor.putString("pwd", pwd.getText().toString());
		    	editor.commit(); 
		    	
				if(verify(name.getText().toString(),pnum.getText().toString(), email.getText().toString(),id.getText().toString(),pwd.getText().toString()
						, confirmpwd.getText().toString())){
					
					insertToDatabase();

					Intent intent = new Intent(Register.this, Login.class);		            
		            startActivity(intent);
		        }
		    }
		});
		
		//go main page after login
		tvterm.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(Register.this, term.class);		            
	            startActivity(intent);
		    }
		});
				
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // define the action bar items
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	finish();               
	            return true;
     
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void showMsg(String msg) {
		Toast toast = Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}
	
	private boolean verify(String name, String pnum,String email, String id, String pwd, String confirmpwd
			  ){
		
		if(name.trim().length()<1 ||pnum.trim().length()<1 || email.trim().length()<1 ||id.trim().length()<1 || pwd.trim().length()<1
				|| confirmpwd.trim().length()<1){
			
			showMsg("Please complete the form.");
			return false;
			
		}
		else if(!(pwd.equals(confirmpwd))){
			showMsg("Please check your password.");
			return false;
			
		}
		
		else if(!cbterm.isChecked()){
			showMsg("Please do agreement with our terms of service.");
			return false;
			
		}else{	
			return true;
		}
		
	}
//	 private void insertToDatabase(String names, String ids, String password, String phone, String address, String emails){
	private void insertToDatabase(){
	        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
	            @Override
	            protected String doInBackground(String... params) {
	 
	                String names = name.getText().toString();
	        		String phone = pnum.getText().toString();
	        		String emails = email.getText().toString();
	        		String ids = id.getText().toString();
	        		String password = pwd.getText().toString();
	        		
	 
	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	                nameValuePairs.add(new BasicNameValuePair("name", names));
	                nameValuePairs.add(new BasicNameValuePair("phone", phone));
	                nameValuePairs.add(new BasicNameValuePair("email", emails));
	                nameValuePairs.add(new BasicNameValuePair("id", ids));
	                nameValuePairs.add(new BasicNameValuePair("password", password));
	 
	                try {
	                    HttpClient httpClient = new DefaultHttpClient();
	                    HttpPost httpPost = new HttpPost(
	                            "http://192.168.43.78/clickajob/register.php");
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
	                	showMsg("Register successfully.");
	                }
	                else if(result == "failure"){
	                	showMsg("Error register.");
	                }
	                	
	            }
	        }
	        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
	        sendPostReqAsyncTask.execute();
	    
    }
 
}