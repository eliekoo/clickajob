package com.example.clickajob;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShareEmail extends Activity {
    Button bsend,bDiscard;
    EditText email, sendto, subject, pitch;
    TextView myResume;
    private ProgressDialog dialog;
    private SharedPreferences pref;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_email);
		 email = (EditText) findViewById(R.id.email);
	        sendto = (EditText) findViewById(R.id.sendto);
	        subject = (EditText) findViewById(R.id.subject);
	        pitch = (EditText) findViewById(R.id.pitch);
	        bDiscard = (Button) findViewById(R.id.bDiscard);
	        bsend = (Button) findViewById(R.id.bSend);
	        myResume = (TextView) findViewById(R.id.myResume);
	        
	        myResume.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// Launching create new product activity
					Intent i = new Intent(ShareEmail.this, EmployeeResume.class);
					startActivity(i);
					
				}
			});
	        
	        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
	        if(pref.getString("email", null)!= null){
				email.setText(pref.getString("email", null));
			}
			
			if(pref.getString("sendto", null)!= null){		
				sendto.setText(pref.getString("sendto", null));
			}	
			if(pref.getString("subject", null)!= null){
				subject.setText(pref.getString("subject", null));
			}
			
			if(pref.getString("pitch", null)!= null){		
				pitch.setText(pref.getString("pitch", null));
			}	
			
			bsend.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Editor editor = pref.edit();
					editor.putString("email", email.getText().toString());
					editor.putString("sendto", sendto.getText().toString());
					editor.putString("subject", subject.getText().toString());
					editor.putString("pitch", pitch.getText().toString());
					editor.commit(); 
					
					verify();
					

					
//					Builder dialog2 = new AlertDialog.Builder(ShareEmail.this);
//
//			    	// Setting Dialog Title
//			    	dialog2.setTitle("Successful");
//
//			    	// Setting Dialog Message
//			    	dialog2.setMessage("You mail had been sent successfully.");
//
//			    	// Setting Positive "Yes" Btn
//			    	dialog2.setNegativeButton("Okay",
//			    	        new DialogInterface.OnClickListener() {
//			    	            public void onClick(DialogInterface dialog, int which) {
//			    	                 dialog.cancel();
//			    	            }
//			    	        });
//
//
//			    	// Showing Alert Dialog
//			    	dialog2.show();
					}
			});

bDiscard.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Builder dialog2 = new AlertDialog.Builder(ShareEmail.this);

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
			    	            	Intent myIntent = new Intent(ShareEmail.this, EmployeeView.class);
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



	private void verify() {
		// TODO Auto-generated method stub
		
		if (email == null || sendto == null){
			Toast.makeText(getApplicationContext(),
                    "Please check the email address.", Toast.LENGTH_SHORT)
                    .show();
		}else{
		
    	AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
    	        ShareEmail.this);

    	// Setting Dialog Title
    	alertDialog2.setTitle("Confirmation");

    	// Setting Dialog Message
    	alertDialog2.setMessage("Are you sure want to send mail?");

    	// Setting Positive "Yes" Btn
    	alertDialog2.setPositiveButton("YES",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	            	    showDialog();
    	            		
    	            }

					private void showDialog() {
						// TODO Auto-generated method stub
						dialog = new ProgressDialog(ShareEmail.this);
						dialog.setTitle("Please wait...");
						dialog.setMessage("Sending Mail...");
						dialog.setIndeterminate(false);
						dialog.setCancelable(true);
						dialog.show();
						dialog.cancel();
						
						
						Builder dialog2 = new AlertDialog.Builder(ShareEmail.this);
	
				    	// Setting Dialog Title
				    	dialog2.setTitle("Successful");
	
				    	// Setting Dialog Message
				    	dialog2.setMessage("Your mail had been sent successfully.");
	
				    	// Setting Positive "Yes" Btn
				    	dialog2.setNegativeButton("Okay",
				    	        new DialogInterface.OnClickListener() {
				    	            public void onClick(DialogInterface dialog, int which) {
				    	                 dialog.cancel();
				    	            }
				    	        });
	
	
				    	// Showing Alert Dialog
				    	dialog2.show();
						}
				});
						
				
    	// Setting Negative "NO" Btn
    	alertDialog2.setNegativeButton("NO",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	                // Write your code here to execute after dialog
    	                Toast.makeText(getApplicationContext(),
    	                        "sending mail cancelled", Toast.LENGTH_SHORT)
    	                        .show();
    	                dialog.cancel();
    	            }
    	        });

    	// Showing Alert Dialog
    	alertDialog2.show();
		}
	}
	
	private void showMsg(String msg) {
		Toast toast = Toast.makeText(ShareEmail.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}
}
