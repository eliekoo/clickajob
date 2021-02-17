package com.example.clickajob;

import java.util.concurrent.ExecutionException;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

public class LoginR extends Activity {

    Button bLogin;
    EditText etRCompID, etRPsw;
    TextView tvRegisterLink;
    private ProgressDialog dialog;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_r);

        etRCompID = (EditText) findViewById(R.id.etCompID);
        etRPsw = (EditText) findViewById(R.id.etPsw);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        if(pref.getString("cid", null)!= null){
			etRCompID.setText(pref.getString("cid", null));
		}
		
		if(pref.getString("cpwd", null)!= null){		
			etRPsw.setText(pref.getString("cpwd", null));
		}		
    
		bLogin.setOnClickListener(new View.OnClickListener() {
		

		public void onClick(View view) {
				if(verify(etRCompID.getText().toString(),etRPsw.getText().toString())){ 
				
			    String method = "loginr";  
//			    BackgroundTask backgroundTask = new BackgroundTask(Login.this); 
			    BackgroundTask2 backgroundTask2 = new BackgroundTask2();
//			    backgroundTask.execute(method,id.getText().toString(),pwd.getText().toString());
				try {
					String reply = backgroundTask2.execute(method,etRCompID.getText().toString(),etRPsw.getText().toString()).get();
					String x = reply.trim().substring(0, 1);
					
					dialog = new ProgressDialog(LoginR.this);
					dialog.setTitle("Please wait...");
					dialog.setMessage("Logging in...");
					dialog.setIndeterminate(false);
					dialog.setCancelable(true);
					dialog.show();
					
					if(x.equals("y")){
						Editor editor = pref.edit();
	    	        	editor.putString("cid", etRCompID.getText().toString());
	    	        	editor.putString("cpwd", etRPsw.getText().toString() );
	    	        	
	    	        	editor.commit();
	    	        	
	    	        	String cid = etRCompID.getText().toString();
	    	        	showMsg( cid + ", Welcome back!");
						Intent intent = new Intent(LoginR.this, EmployerMain.class);	
						
						intent.putExtra("cid", cid);
//						showMsg(cid);
			            startActivity(intent);
					}
					else if (x.equals("n")){
						showMsg("Login Error. Please check your ID and password.");
					}
//					else{
//						showMsg("ERROR"+x);
//					}						
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        }
		    }
		});
		

tvRegisterLink.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View view) {
		// Launching create new product activity
		Intent i = new Intent(LoginR.this, RegisterR.class);
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
Toast toast = Toast.makeText(LoginR.this, msg, Toast.LENGTH_SHORT);
toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
toast.show();
}

}