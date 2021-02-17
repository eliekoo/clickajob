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

public class RegisterR extends Activity {

    Button bRegister;
//    EditText etRName,etRPhone,etREmail, etCompID, etPsw;
//   TextView tvterm;
	
	private EditText cname, cpnum,cemail,caddress,cid, cpwd, cconfirmpwd;
	private TextView tvterm;
	private CheckBox cbterm;
	SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_r);

        cname = (EditText) findViewById(R.id.etRName);
        cpnum = (EditText) findViewById(R.id.etRPhone);
        cemail = (EditText) findViewById(R.id.etREmail);
        caddress = (EditText)findViewById(R.id.etCaddress);
        cid = (EditText) findViewById(R.id.etCompID);
        cpwd = (EditText) findViewById(R.id.etPsw);
//        cconfirmpwd = (EditText) findViewById(R.id.etPsw);
        tvterm = (TextView) findViewById(R.id.tvterm);
        bRegister = (Button)findViewById(R.id.bRegister);

		cconfirmpwd = (EditText)findViewById(R.id.etPsw2);

		cbterm = (CheckBox) findViewById(R.id.cbterm);
		
		pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);

		tvterm.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(RegisterR.this, term.class);		            
	            startActivity(intent);
			}
		});
				
        
        bRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				
				Editor editor = pref.edit();
		    	editor.putString("cname", cname.getText().toString());
		    	editor.putString("cpnum", cpnum.getText().toString());
		    	editor.putString("cemail", cemail.getText().toString());
		    	editor.putString("caddress", caddress.getText().toString());
		    	editor.putString("cid", cid.getText().toString());
		    	editor.putString("cpwd", cpwd.getText().toString());
		    	editor.commit(); 
		    	
				if(verify(cname.getText().toString(),cpnum.getText().toString(), cemail.getText().toString(),cid.getText().toString(),
						cpwd.getText().toString()
						, cconfirmpwd.getText().toString(), cbterm.getText().toString())){
					
					insertToDatabase();

					Intent intent = new Intent(RegisterR.this, LoginR.class);		            
		            startActivity(intent);
		        }
		    }
		});
    }
        
        private void showMsg(String msg) {
    		Toast toast = Toast.makeText(RegisterR.this, msg, Toast.LENGTH_SHORT);
    		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
    		toast.show();
    	}
    	
    	private boolean verify(String cname, String cpnum, String cemail,String caddr, String cid, String cpwd, String cconfirmpwd){
    		
    		if(cname.trim().length()<1 ||cpnum.trim().length()<1 || cemail.trim().length()<1 ||caddr.trim().length()<1 ||cid.trim().length()<1 || cpwd.trim().length()<1
    				|| caddr.trim().length()<1 ||cconfirmpwd.trim().length()<1){
    			
    			showMsg("Please complete the form.");
    			return false;
    			
    		}
    		else if(!(cpwd.equals(cconfirmpwd))){
    			showMsg("Please check your password.");
    			return false;
    			
    		}
    		
    		if(!cbterm.isChecked()){
    			showMsg("Please do agreement with our terms of service.");
    			return false;
    			
    		}else{	
    			return true;
    		}
    		
    	}
//    	 private void insertToDatabase(String names, String ids, String password, String phone, String address, String emails){
    	private void insertToDatabase(){
    	        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
    	            @Override
    	            protected String doInBackground(String... params) {
    	 
    	                String names = cname.getText().toString();
    	        		String phone = cpnum.getText().toString();
    	        		String emails = cemail.getText().toString();
    	        		String addresss = caddress.getText().toString();
    	        		String ids = cid.getText().toString();
    	        		String password = cpwd.getText().toString();
    	        		
    	 
    	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	                nameValuePairs.add(new BasicNameValuePair("cname", names));
    	                nameValuePairs.add(new BasicNameValuePair("cphone", phone));
    	                nameValuePairs.add(new BasicNameValuePair("cemail", emails));
    	                nameValuePairs.add(new BasicNameValuePair("caddress", addresss));
    	                nameValuePairs.add(new BasicNameValuePair("cid", ids));
    	                nameValuePairs.add(new BasicNameValuePair("cpassword", password));
    	 
    	                try {
    	                    HttpClient httpClient = new DefaultHttpClient();
    	                    HttpPost httpPost = new HttpPost(
    	                            "http://192.168.43.78/clickajob/registerr.php");
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