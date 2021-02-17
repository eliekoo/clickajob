package com.example.clickajob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeApply extends ListActivity{
//	private JobArrayAdapter jobArrayAdapter;
	
	// Progress Dialog
		private ProgressDialog pDialog;

		// Creating JSON Parser object
		JSONParser jParser = new JSONParser();

		ArrayList<HashMap<String, String>> jobList;

		// url to get all products list
		private static String url_all_jobs = "http://192.168.43.78/clickajob/getjoblist.php";

		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_PRODUCTS = "jobss";
		private static final String TAG_VID = "vid";
//		private static final String userid = "id";
		private static final String TAG_JOBTITLE = "jobtitle";
		private static final String TAG_DATE = "jdate";
		private static final String TAG_LOCATION = "jlocation";
		private static final String TAG_SALARY = "jsalary";
			
		
		// products JSONArray
		JSONArray jobss = null;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_employee_apply);

			// Hashmap for ListView
			jobList = new ArrayList<HashMap<String, String>>();

			// Loading products in Background Thread
			new LoadAllProducts().execute();

			// Get listview
			ListView lv = getListView();
			
			Intent intent = getIntent();
	    	final String id = intent.getStringExtra("userid");
	    	
//	    	showMsg("on apply : "+id);
	    	
			// on seleting single product
			// launching Edit Product Screen
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long ids) {
					// getting values from selected ListItem
					String vid = ((TextView) view.findViewById(R.id.vid)).getText()
							.toString();
//					String ids = ((TextView) view.findViewById(R.id.id)).getText()
//							.toString();

					// Starting new intent
					Intent in = new Intent(getApplicationContext(),
							EmployerJobView.class);
					// sending pid to next activity
					in.putExtra(TAG_VID, vid);
					in.putExtra("userid", id);

					
					// starting new activity and expecting some response back
					startActivityForResult(in, 100);
//					showMsg("userid: "+ id);
				}
			});

		}

		// Response from Edit Product Activity
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			// if result code 100
			if (resultCode == 100) {
				// if result code 100 is received 
				// means user edited/deleted product
				// reload this screen again
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}

		}

		/**
		 * Background Async Task to Load all product by making HTTP Request
		 * */
		class LoadAllProducts extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(EmployeeApply.this);
				pDialog.setMessage("Loading jobs... Please wait...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
			}

			/**
			 * getting All products from url
			 * */
			protected String doInBackground(String... args) {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// getting JSON string from URL
				JSONObject json = jParser.makeHttpRequest(url_all_jobs, "GET", params);
				
				// Check your log cat for JSON reponse
				Log.d("All Jobs: ", json.toString());

				try {
					// Checking for SUCCESS TAG
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {
						// products found
						// Getting Array of Products
						jobss = json.getJSONArray(TAG_PRODUCTS);

						// looping through All Products
						for (int i = 0; i < jobss.length(); i++) {
							JSONObject c = jobss.getJSONObject(i);

							// Storing each json item in variable
							String vid = c.getString(TAG_VID);
							String jobtitle = c.getString(TAG_JOBTITLE);
							String jdate = c.getString(TAG_DATE);
							String jlocation = c.getString(TAG_LOCATION);
							String jsalary = c.getString(TAG_SALARY);
							
							// creating new HashMap
							HashMap<String, String> map = new HashMap<String, String>();

							// adding each child node to HashMap key => value
							map.put(TAG_VID, vid);
							map.put(TAG_JOBTITLE, jobtitle);
							map.put(TAG_DATE, jdate);
							map.put(TAG_LOCATION, jlocation);
							map.put(TAG_SALARY, jsalary);
							
							// adding HashList to ArrayList
							jobList.add(map);
							
						
						}
					} else {
						// no products found
						// Launch Add New product Activity
//						Intent i = new Intent(getApplicationContext(),
//								EmployeeApply.class);
//						// Closing all previous activities
//						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						startActivity(i);
						showMsg("No job found");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) {
				// dismiss the dialog after getting all products
				pDialog.dismiss();
				// updating UI from Background Thread
				runOnUiThread(new Runnable() {
					public void run() {
						/**
						 * Updating parsed JSON data into ListView
						 * */
						ListAdapter adapter = new SimpleAdapter(
								EmployeeApply.this, jobList,
								R.layout.list_item, new String[] { TAG_VID,
										TAG_JOBTITLE, TAG_DATE, TAG_LOCATION, TAG_SALARY},
								new int[] { R.id.vid, R.id.jobtitle , R.id.date,R.id.location,R.id.salary});
						// updating listview
						EmployeeApply.this.setListAdapter(adapter);
//						showMsg("id: " + TAG_ID);
						
						
					}
				});

				
			}
			
			 
		}
		private void showMsg(String msg) {
			Toast toast = Toast.makeText(EmployeeApply.this, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
			toast.show();
		}
	}