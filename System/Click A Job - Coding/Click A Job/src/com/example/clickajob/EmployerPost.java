package com.example.clickajob;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;
import com.example.clickajob.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EmployerPost extends Activity {

	SharedPreferences pref;
	TextView textview1, textview2, textview3, textview4, textview5, textview6, textview7, textview8, textview9;
	Button bviewcand;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employer_post);
		
		bviewcand = (Button) findViewById (R.id.bviewcandidate);
		
		textview1 = (TextView) findViewById (R.id.textView1);
		textview2 = (TextView) findViewById (R.id.textView2);
		textview3 = (TextView) findViewById (R.id.textView3);
		textview4 = (TextView) findViewById (R.id.textView4);
		textview5 = (TextView) findViewById (R.id.textView5);
			textview6 = (TextView) findViewById (R.id.textView6);
			textview7 = (TextView) findViewById (R.id.textView7);
			textview8 = (TextView) findViewById (R.id.textView8);
			 textview9 = (TextView) findViewById (R.id.textView9);
			 
			 pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
			 
			 	String cname = pref.getString("cname", null);
			 	String cid = pref.getString("cid", null);
				String cphone = pref.getString("cpnum", null);
				String cemail = pref.getString("cemail", null);
				String jlocation = pref.getString("jlocation", null);
		        String jtitle = pref.getString("jtitle", null);
				String jreq = pref.getString("jreq", null);
				String jdesc = pref.getString("jdesc", null);
				String jdate = pref.getString("jdate", null);
				String jsalary = pref.getString("jsalary", null);
		        
				textview1.setText(jtitle);
				textview2.setText(jdate);
				textview3.setText(jlocation);
				textview4.setText(jdesc);
				textview5.setText(jreq);
				textview6.setText(jsalary);
				textview7.setText(cname);
				textview8.setText(cphone);
				textview9.setText(cemail);
				
				bviewcand.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent myIntent = new Intent(EmployerPost.this, ViewCandidate.class);
	                    startActivity(myIntent);
					}
					
				});

				
	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.employer_post, menu);
//		return true;
//	}
//
//}

//SharedPreferences pref;
//TextView textview1, textview2, textview3, textview4, textview5, textview6, textview7, textview8, textview9;
//Button bviewcand;
//String cid;
//JSONParser jsonParser = new JSONParser();
//ProgressDialog pDialog;
//
//// single product url
//private static final String url_vacancy_details = "http://192.168.43.78/clickajob/get_own_vacancy_details.php";
//
//private static final String TAG_SUCCESS = "success";
//private static final String TAG_PRODUCT = "product";
//private static final String TAG_CID = "cid";
////private static final String TAG_ID = "id";
//private static final String TAG_JOBTITLE = "jobtitle";
//private static final String TAG_DATE = "jdate";
//private static final String TAG_LOCATION = "jlocation";
//private static final String TAG_SALARY = "jsalary";
//private static final String TAG_CNAME = "cname";
//private static final String TAG_CPHONE = "cphone";
//private static final String TAG_CEMAIL = "cemail";
//private static final String TAG_JREQ = "jreq";
//private static final String TAG_JDESC = "jdesc";
//
//@Override
//protected void onCreate(Bundle savedInstanceState) {
//	super.onCreate(savedInstanceState);
//	setContentView(R.layout.activity_employer_post);
//	
//	bviewcand = (Button) findViewById (R.id.bviewcandidate);
//	
//	textview1 = (TextView) findViewById (R.id.textView1);
//	textview2 = (TextView) findViewById (R.id.textView2);
//	textview3 = (TextView) findViewById (R.id.textView3);
//	textview4 = (TextView) findViewById (R.id.textView4);
//	textview5 = (TextView) findViewById (R.id.textView5);
//		textview6 = (TextView) findViewById (R.id.textView6);
//		textview7 = (TextView) findViewById (R.id.textView7);
//		textview8 = (TextView) findViewById (R.id.textView8);
//		 textview9 = (TextView) findViewById (R.id.textView9);
////		 
////		 pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
////		 
////		 	String cname = pref.getString("cname", null);
////		 	String cid = pref.getString("cid", null);
////			String cphone = pref.getString("cpnum", null);
////			String cemail = pref.getString("cemail", null);
////			String jlocation = pref.getString("jlocation", null);
////	        String jtitle = pref.getString("jtitle", null);
////			String jreq = pref.getString("jreq", null);
////			String jdesc = pref.getString("jdesc", null);
////			String jdate = pref.getString("jdate", null);
////			String jsalary = pref.getString("jsalary", null);
////	        
////			textview1.setText(jtitle);
////			textview2.setText(jdate);
////			textview3.setText(jlocation);
////			textview4.setText(jdesc);
////			textview5.setText(jreq);
////			textview6.setText(jsalary);
////			textview7.setText(cname);
////			textview8.setText(cphone);
////			textview9.setText(cemail);
//		 
////		 Intent i = getIntent();
////			
////			// getting product id (pid) from intent
////		vid = i.getStringExtra(TAG_VID);
//		
//		 new GetVacancyDetails().execute();	
//		 
//			bviewcand.setOnClickListener(new OnClickListener(){
//
//				@Override
//				public void onClick(View arg0) {
////					Activity view;
//					// TODO Auto-generated method stub
//					Intent intent = getIntent();
//			    	cid = intent.getStringExtra("cid");
//					
////					cid = ((TextView) findViewById(R.id.cid)).getText()
////							.toString();
//					Intent myIntent = new Intent(EmployerPost.this, ViewCandidate.class);
//					myIntent.putExtra(TAG_CID, cid);
////					showMsg(cid);
//                    startActivity(myIntent);
//				}
//				
//			});
//			
//			
////			new GetProductDetailsB().execute();
//	   }
//
//	    class GetVacancyDetails extends AsyncTask<String, String, String> {
//
//			/**
//			 * Before starting background thread Show Progress Dialog
//			 * */
//			@Override
//			protected void onPreExecute() {
//				super.onPreExecute();
//				pDialog = new ProgressDialog(EmployerPost.this);
//				pDialog.setMessage("Loading vacancy details. Please wait...");
//				pDialog.setIndeterminate(false);
//				pDialog.setCancelable(true);
//				pDialog.show();
////				pDialog.dismiss();
//			}
//
//			/**
//			 * Getting product details in background thread
//			 * */
//			protected String doInBackground(String... paramss) { 
//
//				// updating UI from Background Thread
////				runOnUiThread(new Runnable() {
////					public void run() {
//						// Check for success tag
//						int success;
//						try {
//							// Building Parameters
//							List<NameValuePair> params = new ArrayList<NameValuePair>();
//							params.add(new BasicNameValuePair("cid", cid));
////							params.add(new BasicNameValuePair("id", id));
//
//							// getting product details by making HTTP request
//							// Note that product details url will use GET request
//							JSONObject json = jsonParser.makeHttpRequest(
//									url_vacancy_details, "GET", params);
//
//							// check your log for json response
//							Log.d("Single vacancy Details", json.toString());
//							
//							// json success tag
//							success = json.getInt(TAG_SUCCESS);
//							if (success == 1) {
//								// successfully received product details
//								JSONArray productObj = json
//										.getJSONArray(TAG_PRODUCT); // JSON Array
//								
//								// get first product object from JSON Array
//								final JSONObject product = productObj.getJSONObject(0);
//
//								runOnUiThread(new Runnable() {
//									public void run() {
//								// product with this pid found
//								// Edit Text
////								textview1 = (TextView) findViewById(R.id.textView1);
////								textview2 = (TextView) findViewById(R.id.textView2);
////								textview3 = (TextView) findViewById(R.id.textView3);
//
//								// display product data in EditText
//								try {
//									
//									textview1.setText(product.getString(TAG_JOBTITLE));
//									textview2.setText(product.getString(TAG_DATE));
//									textview3.setText(product.getString(TAG_LOCATION));
//									textview4.setText(product.getString(TAG_JDESC));
//									textview5.setText(product.getString(TAG_JREQ));
//									textview6.setText(product.getString(TAG_SALARY));
//									textview7.setText(product.getString(TAG_CNAME));
//									textview8.setText(product.getString(TAG_CPHONE));
//									textview9.setText(product.getString(TAG_CEMAIL));
//									
//								} catch (JSONException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								
//									}
//								});
//
//							}else{
//								// product with pid not found
//							}
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}
////					}
////				});
//						
//				return null;
//			}
//
//
//			/**
//			 * After completing background task Dismiss the progress dialog
//			 * **/
//			protected void onPostExecute(String file_url) {
//				// dismiss the dialog once got all details
//				pDialog.dismiss();
//			}
//		}

	    private void showMsg(String msg) {
			Toast toast = Toast.makeText(EmployerPost.this, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
			toast.show();
		}

	}

