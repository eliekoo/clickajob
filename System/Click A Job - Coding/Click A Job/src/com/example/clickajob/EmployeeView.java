package com.example.clickajob;

import java.io.ByteArrayOutputStream;
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
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeView extends Activity {

	SharedPreferences pref;
	TextView textview1, textview2, textview3, textview4, textview5, textview6, textview18, textview8, textview9,textview19, textview10, textview11, textview12, textview13, textview14, textview16, textview17;
	TextView textview20, textview21, textview22, textview23,textview24,textview25,textview26, textview27, textview28,textview29, textview30, textview31, textview32, textview33;
	ImageView userpic;
	Button bshare, bcreate;
	
	String id,pic, name, phone, email, address, course, school, CGPA, year ,org, des ,dur1 ,dur2,employ,workrole;
	String in1,in2,in3 ,skill ,strength , ptitle ,pdesc ,pdate1, pdate2 ,prole, otitle,odesc ,rname ,rdes ,rorg,rphone ,remail ;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);
        
        bcreate = (Button) findViewById (R.id.bapply);
        userpic = (ImageView) findViewById (R.id.userpic);
        textview1 = (TextView) findViewById (R.id.textView1);
		textview2 = (TextView) findViewById (R.id.textView2);
		textview3 = (TextView) findViewById (R.id.textView3);
		textview4 = (TextView) findViewById (R.id.textView4);
		textview5 = (TextView) findViewById (R.id.textView5);
			textview6 = (TextView) findViewById (R.id.textView6);
			textview18 = (TextView) findViewById (R.id.textView18);
			textview8 = (TextView) findViewById (R.id.textView8);
			 textview9 = (TextView) findViewById (R.id.textView9);
				textview10 = (TextView) findViewById (R.id.textView10);
				textview11 = (TextView) findViewById (R.id.textView11);
				textview12 = (TextView) findViewById (R.id.textView12);
				textview13 = (TextView) findViewById (R.id.textView13);
				textview14 = (TextView) findViewById (R.id.textView14);
				textview16 = (TextView) findViewById (R.id.textView16);
				textview17 = (TextView) findViewById (R.id.textView17);
				textview19 = (TextView) findViewById (R.id.textView19);
				textview20 = (TextView) findViewById (R.id.textView20);
				textview21 = (TextView) findViewById (R.id.textView21);
				textview22 = (TextView) findViewById (R.id.textView22);
				textview23 = (TextView) findViewById (R.id.textView23);
				textview24 = (TextView) findViewById (R.id.textView24);
				textview25 = (TextView) findViewById (R.id.textView25);
				textview26 = (TextView) findViewById (R.id.textView26);
				textview27 = (TextView) findViewById (R.id.textView27); //other title
				textview28 = (TextView) findViewById (R.id.textView28); //other description
				textview29 = (TextView) findViewById (R.id.textView29);
				textview30 = (TextView) findViewById (R.id.textView30);
				textview31 = (TextView) findViewById (R.id.textView31);
				textview32 = (TextView) findViewById (R.id.textView32);
				textview33 = (TextView) findViewById (R.id.textView33);
		        
        
        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        
        Bitmap bm=((BitmapDrawable)userpic.getDrawable()).getBitmap();
        id = pref.getString("id", null);
        pic = pref.getString("pic", encodeTobase64(bm));
        name = pref.getString("name", null);
		phone = pref.getString("phone", null);
		email = pref.getString("email", null);
		address = pref.getString("address", null);
        course = pref.getString("course", null);
        school = pref.getString("school", null);
		CGPA = pref.getString("CGPA", null);
		year = pref.getString("year", null);
		org = pref.getString("org", null);
        des = pref.getString("des", null);
		dur1 = pref.getString("dur1", null);
		dur2 = pref.getString("dur2", null);
		employ = pref.getString("employ", null);
		workrole = pref.getString("workrole", null);
		in1 = pref.getString("in1", null);
		in2 = pref.getString("in2", null);
		in3 = pref.getString("in3", null);
		skill = pref.getString("skill", null);
		strength = pref.getString("strength", null);
		ptitle = pref.getString("ptitle", null);
		pdesc = pref.getString("pdesc", null);
		pdate1 = pref.getString("pdate1", null);
		pdate2 = pref.getString("pdate2", null);
		prole = pref.getString("prole", null);
		otitle = pref.getString("otitle", null);
		odesc = pref.getString("odesc", null);
		rname = pref.getString("rname", null);
		rdes = pref.getString("rdes", null);
		rorg = pref.getString("rorg", null);
		rphone = pref.getString("rphone", null);
		remail = pref.getString("remail", null);
		
		
		userpic.setImageBitmap(decodeBase64(pref.getString("pic", null)));
		textview1.setText(name);
		textview2.setText(phone);
		textview3.setText(email);
		textview4.setText(address);
		textview5.setText(school);
		textview6.setText(course);
		textview18.setText(CGPA);
		textview8.setText(year);
		textview9.setText(org);
		textview10.setText(des);
		textview11.setText(employ);
		textview12.setText(dur1);
		textview13.setText(dur2);
		textview19.setText(workrole);
		textview14.setText(in1);
		textview16.setText(in2);
		textview17.setText(in3);
		textview20.setText(skill);
		textview21.setText(strength);
		textview22.setText(ptitle);
		textview23.setText(pdesc);
		textview24.setText(pdate1);
		textview25.setText(pdate2);
		textview26.setText(prole);
		textview27.setText(otitle);
		textview28.setText(odesc);
		textview29.setText(rname);
		textview30.setText(rdes);
		textview31.setText(rorg);
		textview32.setText(rphone);
		textview33.setText(remail);

   

   bcreate.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			
			AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
	    	        EmployeeView.this);

	    	// Setting Dialog Title
	    	alertDialog2.setTitle("Confirm?");

	    	// Setting Dialog Message
	    	alertDialog2.setMessage("Are you sure want to create resume?");

	    	// Setting Positive "Yes" Btn
	    	alertDialog2.setPositiveButton("YES",
	    	        new DialogInterface.OnClickListener() {
	    	            public void onClick(DialogInterface dialog, int which) {
	    	            	insertToDatabase();
	    	            	
	    	            	showdialog();
	    	            	
	    	            }

						private void showdialog() {
							// TODO Auto-generated method stub
							AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
					    	        EmployeeView.this);

					    	// Setting Dialog Title
					    	alertDialog2.setTitle("Next Step");

					    	// Setting Dialog Message
					    	alertDialog2.setMessage("Share via email or Go to Apply Job?");

					    	// Setting Positive "Yes" Btn
					    	alertDialog2.setPositiveButton("Email",
					    	        new DialogInterface.OnClickListener() {
					    	            public void onClick(DialogInterface dialog, int which) {
					    	            	Intent myIntent = new Intent(EmployeeView.this, ShareEmail.class);
				    	                    myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
				    	                    startActivity(myIntent);
				    	                    finish();
					    	            }

					    	        });
					    	// Setting Negative "NO" Btn
					    	alertDialog2.setNegativeButton("Go to Apply Job",
					    	        new DialogInterface.OnClickListener() {
					    	            public void onClick(DialogInterface dialog, int which) {
					    	                // Write your code here to execute after dialog
					    	            	Intent myIntent = new Intent(EmployeeView.this, EmployeeApply.class);
				    	                    myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
				    	                    startActivity(myIntent);
				    	                    finish();
					    	            }
					    	        });

					    	// Showing Alert Dialog
					    	alertDialog2.show();
						}
	    	        });
	    	// Setting Negative "NO" Btn
	    	alertDialog2.setNegativeButton("NO",
	    	        new DialogInterface.OnClickListener() {
	    	            public void onClick(DialogInterface dialog, int which) {
	    	                // Write your code here to execute after dialog
	    	            	
	    	            }
	    	        });

	    	// Showing Alert Dialog
	    	alertDialog2.show();
			
					
		}

		
	});
    
    }
    
    private void insertToDatabase() {
		
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
      		
 
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id", id));
                nameValuePairs.add(new BasicNameValuePair("pic", pic));
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("phone", phone));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("address", address));
                nameValuePairs.add(new BasicNameValuePair("course", course));
                nameValuePairs.add(new BasicNameValuePair("school", school));
                nameValuePairs.add(new BasicNameValuePair("CGPA", CGPA));
                nameValuePairs.add(new BasicNameValuePair("year", year));
                nameValuePairs.add(new BasicNameValuePair("org", org));
                nameValuePairs.add(new BasicNameValuePair("des", des));
                nameValuePairs.add(new BasicNameValuePair("dur1", dur1));
                nameValuePairs.add(new BasicNameValuePair("dur2", dur2));
                nameValuePairs.add(new BasicNameValuePair("employ", employ));
                nameValuePairs.add(new BasicNameValuePair("workrole", workrole));
                nameValuePairs.add(new BasicNameValuePair("in1", in1));
                nameValuePairs.add(new BasicNameValuePair("in2", in2));
                nameValuePairs.add(new BasicNameValuePair("in3", in3));
                nameValuePairs.add(new BasicNameValuePair("skill", skill));
                nameValuePairs.add(new BasicNameValuePair("strength", strength));
                nameValuePairs.add(new BasicNameValuePair("ptitle", ptitle));
                nameValuePairs.add(new BasicNameValuePair("pdesc", pdesc));
                nameValuePairs.add(new BasicNameValuePair("pdate1", pdate1));
                nameValuePairs.add(new BasicNameValuePair("pdate2", pdate2));
                nameValuePairs.add(new BasicNameValuePair("prole", prole));
                nameValuePairs.add(new BasicNameValuePair("otitle", otitle));
                nameValuePairs.add(new BasicNameValuePair("odesc", odesc));
                nameValuePairs.add(new BasicNameValuePair("rname", rname));
                nameValuePairs.add(new BasicNameValuePair("rdes", rdes));
                nameValuePairs.add(new BasicNameValuePair("rorg", rorg));
                nameValuePairs.add(new BasicNameValuePair("rphone", rphone));
                nameValuePairs.add(new BasicNameValuePair("remail", remail));
 
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.43.78/clickajob/resume.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
           
                } catch (ClientProtocolException e) {
                	showMsg("ERROR");
                } catch (IOException e) {
                	showMsg("ERROR");
                }
                return "success";
            }
 
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result == "success"){
                	showMsg("Create resume successfully.");
                }
                else if(result == "failure"){
                	showMsg("Error create resume.");
                }
                	
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(); 
}

 


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    //convert bitmap into string
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        return imageEncoded;
    }

    //convert string to bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    
	private void showMsg(String msg) {
		Toast toast = Toast.makeText(EmployeeView.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}
}
