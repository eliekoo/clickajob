package com.example.clickajob;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.clickajob.R;


import android.app.Activity;

import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EmployeeResume extends Activity {

	SharedPreferences pref;
	TextView textview1, textview2, textview3, textview4, textview5, textview6, textview18, textview8, textview9,textview19, textview10, textview11, textview12, textview13, textview14, textview16, textview17;
	TextView textview20, textview21, textview22, textview23,textview24,textview25,textview26, textview27, textview28,textview29, textview30, textview31, textview32, textview33;
	ImageView userpic;

	String pid;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	
	private static final String url_resume_details = "http://192.168.43.78/clickajob/get_resume_details.php";

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCT = "product";
	private static final String TAG_PID = "pid";
	private static final String TAG_ID = "id";
	private static final String TAG_PIC = "pic";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_EMAIL = "email";
	
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_COURSE = "course";
	private static final String TAG_SCHOOL = "school";
	private static final String TAG_CGPA = "CGPA";
	private static final String TAG_YEAR = "year";
	
	private static final String TAG_ORG = "org";
	private static final String TAG_DES = "des";
	private static final String TAG_DUR1 = "dur1";
	private static final String TAG_DUR2 = "dur2";
	private static final String TAG_EMPLOY = "employ";
	private static final String TAG_WORKROLE = "workrole";
	
	private static final String TAG_IN1 = "in1";
	private static final String TAG_IN2 = "in2";
	private static final String TAG_IN3 = "in3";
	private static final String TAG_SKILL = "skill";
	private static final String TAG_STRENGTH = "strength";
	
	private static final String TAG_PTITLE = "ptitle";
	private static final String TAG_PDESC = "pdesc";
	private static final String TAG_PDATE1 = "pdate1";
	private static final String TAG_PDATE2 = "pdate2";
	private static final String TAG_PROLE = "prole";
	
	private static final String TAG_OTITLE = "otitle";
	private static final String TAG_ODESC = "odesc";
	private static final String TAG_RNAME = "rname";
	private static final String TAG_RDES = "rdes";
	private static final String TAG_RORG = "rorg";
	private static final String TAG_RPHONE = "rphone";
	private static final String TAG_REMAIL = "remail";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_resume);

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


        Intent i = getIntent();
		
		// getting product id (pid) from intent
		pid = i.getStringExtra(TAG_PID);

		// Getting complete product details in background thread
		new GetProductDetails().execute();
//		new GetProductDetailsB().execute();
   }

    class GetProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EmployeeResume.this);
			pDialog.setMessage("Loading product details. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
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
						params.add(new BasicNameValuePair("pid", pid));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jsonParser.makeHttpRequest(
								url_resume_details, "GET", params);

						// check your log for json response
						Log.d("Single Resume Details", json.toString());
						
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
						
							// display product data in EditText
							try {
								textview1.setText(product.getString(TAG_NAME));
								textview2.setText(product.getString(TAG_PHONE));
								textview3.setText(product.getString(TAG_EMAIL));
								userpic.setImageBitmap(decodeBase64(product.getString(TAG_PIC)));
								textview4.setText(product.getString(TAG_ADDRESS));
								textview5.setText(product.getString(TAG_SCHOOL));
								textview6.setText(product.getString(TAG_COURSE));
								textview18.setText(product.getString(TAG_CGPA));
								textview8.setText(product.getString(TAG_YEAR));
								textview9.setText(product.getString(TAG_ORG));
								textview10.setText(product.getString(TAG_DES));
								textview11.setText(product.getString(TAG_EMPLOY));
								textview12.setText(product.getString(TAG_DUR1));
								textview13.setText(product.getString(TAG_DUR2));
								textview19.setText(product.getString(TAG_WORKROLE));
								textview14.setText(product.getString(TAG_IN1));
								textview16.setText(product.getString(TAG_IN2));
								textview17.setText(product.getString(TAG_IN3));
								textview20.setText(product.getString(TAG_SKILL));
								
								textview21.setText(product.getString(TAG_STRENGTH));
								textview22.setText(product.getString(TAG_PTITLE));
								textview23.setText(product.getString(TAG_PDESC));
								textview24.setText(product.getString(TAG_PDATE1));
								textview25.setText(product.getString(TAG_PDATE2));
								textview26.setText(product.getString(TAG_PROLE));
								textview27.setText(product.getString(TAG_OTITLE));
								textview28.setText(product.getString(TAG_ODESC));
								textview29.setText(product.getString(TAG_RNAME));
								textview30.setText(product.getString(TAG_RDES));
								textview31.setText(product.getString(TAG_RORG));
								textview32.setText(product.getString(TAG_RPHONE));
								textview33.setText(product.getString(TAG_REMAIL));
								
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
}
