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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmployerJobView extends Activity {
	SharedPreferences pref;
	TextView textview1, textview2, textview3, textview4, textview5, textview6, textview7, textview8, textview9;
	Button bapply;
	private ProgressDialog pDialog;
	
	String vid;
	String id;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// single product url
	private static final String url_vacancy_details = "http://192.168.43.78/clickajob/get_vacancy_details.php";
//	private static final String url_update_product = "http://192.168.43.78/android_connect/update_product.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCT = "product";
	private static final String TAG_VID = "vid";
	private static final String TAG_ID = "id";
	private static final String TAG_JOBTITLE = "jobtitle";
	private static final String TAG_DATE = "jdate";
	private static final String TAG_LOCATION = "jlocation";
	private static final String TAG_SALARY = "jsalary";
	private static final String TAG_CNAME = "cname";
	private static final String TAG_CPHONE = "cphone";
	private static final String TAG_CEMAIL = "cemail";	private static final String TAG_JREQ = "jreq";
	private static final String TAG_JDESC = "jdesc";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employer_job_view);
		
		textview1 = (TextView) findViewById (R.id.textView1);
		textview2 = (TextView) findViewById (R.id.textView2);
		textview3 = (TextView) findViewById (R.id.textView3);
		textview4 = (TextView) findViewById (R.id.textView4);
		textview5 = (TextView) findViewById (R.id.textView5);
			textview6 = (TextView) findViewById (R.id.textView6);
			textview7 = (TextView) findViewById (R.id.textView7);
			textview8 = (TextView) findViewById (R.id.textView8);
		  textview9 = (TextView) findViewById (R.id.textView9);
		
		bapply = (Button) findViewById(R.id.bApply);
		
		Intent intent = getIntent();
		id = intent.getStringExtra("userid");
//    	showMsg("view job : "+id);
		
 
		bapply.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showdialoggg();
//				showMsg("vid is " + vid + "id : " + id);
				
//				new SaveProductDetails().execute();
			}

			
		});
		
		
		
		Intent i = getIntent();
		
		// getting product id (pid) from intent
		vid = i.getStringExtra(TAG_VID);
		id = i.getStringExtra("userid");
		
		// Getting complete product details in background thread
		new GetVacancyDetails().execute();
//		new GetProductDetailsB().execute();
   }

    class GetVacancyDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EmployerJobView.this);
			pDialog.setMessage("Loading vacancy details. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
//			pDialog.dismiss();
		}

		/**
		 * Getting product details in background thread
		 * */
		protected String doInBackground(String... paramss) { 

			// updating UI from Background Thread
//			runOnUiThread(new Runnable() {
//				public void run() {
					// Check for success tag
					int success;
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("vid", vid));
//						params.add(new BasicNameValuePair("id", id));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jsonParser.makeHttpRequest(
								url_vacancy_details, "GET", params);

						// check your log for json response
						Log.d("Single vacancy Details", json.toString());
						
						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray productObj = json
									.getJSONArray(TAG_PRODUCT); // JSON Array
							
							// get first product object from JSON Array
							final JSONObject product = productObj.getJSONObject(0);

							runOnUiThread(new Runnable() {
								public void run() {
							// product with this pid found
							// Edit Text
//							textview1 = (TextView) findViewById(R.id.textView1);
//							textview2 = (TextView) findViewById(R.id.textView2);
//							textview3 = (TextView) findViewById(R.id.textView3);

							// display product data in EditText
							try {
								textview1.setText(product.getString(TAG_JOBTITLE));
								textview2.setText(product.getString(TAG_DATE));
								textview3.setText(product.getString(TAG_LOCATION));
								textview4.setText(product.getString(TAG_JDESC));
								textview5.setText(product.getString(TAG_JREQ));
								textview6.setText(product.getString(TAG_SALARY));
								textview7.setText(product.getString(TAG_CNAME));
								textview8.setText(product.getString(TAG_CPHONE));
								textview9.setText(product.getString(TAG_CEMAIL));
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
								}
							});

						}else{
							// product with pid not found
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
//				}
//			});
					
			return null;
		}


		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}
    private void showdialoggg() {
				// TODO Auto-generated method stub
		// starting background task to update product
		Builder dialog = new AlertDialog.Builder(EmployerJobView.this);

    	// Setting Dialog Title
    	dialog.setTitle("Confirm Apply?");

    	// Setting Dialog Message
    	dialog.setMessage("Are you sure want to send your application?\nOnce apply cannot be undone. ");

    	dialog.setPositiveButton("YES",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	            		            	
    	            	ProgressDialog dialog2 = new ProgressDialog(EmployerJobView.this);
						dialog2.setTitle("Please wait...");
						dialog2.setMessage("Applying Job...");
						dialog2.setIndeterminate(false);
						dialog2.setCancelable(true);
						dialog2.show();
						
    	            	insertToDatabase();
    	            	
    	            	dialog2.dismiss();
    	            }

				});

    	dialog.setNegativeButton("NO",
    	        new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	                // Write your code here to execute after dialog
    	                Toast.makeText(getApplicationContext(),
    	                        "Apply job cancelled", Toast.LENGTH_SHORT)
    	                        .show();
    	                dialog.cancel();
    	            }
    		});
    	dialog.show();
		}
    
    private void insertToDatabase() {
		
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
      		
 
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("vid", vid));
                nameValuePairs.add(new BasicNameValuePair("id", id));
                
//                pic, name, phone, email, address, course, school, CGPA, year ,org, des ,dur1 ,dur2,employ,workrole;
//            	String in1,in2,in3 ,skill ,strength , ptitle ,pdesc ,pdate1, pdate2 ,prole, otitle,odesc ,rname ,rdes ,rorg,rphone ,remail ;
                               
//                String cname = pref.getString("cname", null);
//				String cphone = pref.getString("cpnum", null);
//				String cemail = pref.getString("cemail", null);
//				String jlocation = pref.getString("jlocation", null);
//		        String jtitle = pref.getString("jtitle", null);
//				String jreq = pref.getString("jreq", null);
//				String jdesc = pref.getString("jdesc", null);
//				String jdate = pref.getString("jdate", null);
//				String jsalary = pref.getString("jsalary", null);
//                
//                nameValuePairs.add(new BasicNameValuePair("cname", cname));
//                nameValuePairs.add(new BasicNameValuePair("cpnum", cphone));
//                nameValuePairs.add(new BasicNameValuePair("cemail", cemail));
//                nameValuePairs.add(new BasicNameValuePair("jlocation", jlocation));
//                nameValuePairs.add(new BasicNameValuePair("jtitle", jtitle));
//                nameValuePairs.add(new BasicNameValuePair("jreq", jreq));
//                nameValuePairs.add(new BasicNameValuePair("jdesc", jdesc));
//                nameValuePairs.add(new BasicNameValuePair("jdate", jdate));
//                nameValuePairs.add(new BasicNameValuePair("jsalary", jsalary));

 
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.43.78/clickajob/employee_application.php");
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
                	showMsg("Your application had been sent successfully.");
//                	Intent myIntent = new Intent(EmployerJobView.this, EmployerPost.class);
//                    startActivity(myIntent);
                }
                else if(result == "failure"){
                	showMsg("Error apply job.");
                }
                	
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
    
}
    
    private void showMsg(String msg) {
		Toast toast = Toast.makeText(EmployerJobView.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}

}
