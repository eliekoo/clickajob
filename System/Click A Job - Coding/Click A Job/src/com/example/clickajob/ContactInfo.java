package com.example.clickajob;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.URI;

import com.example.clickajob.R;
import com.example.clickajob.R.id;
import com.example.clickajob.R.layout;
import com.example.clickajob.R.menu;

public class ContactInfo extends Activity {

    private static final int SELECT_PICTURE = 1;
    private static final int CAMERA_REQUEST = 1888;
    String selectedImagePath;

    ImageButton cam, gallery;
    ImageView img;
    SharedPreferences pref;
    Button bSave,bClear;
    EditText etName, etPhone,etEmail,etAddress;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        cam = (ImageButton)findViewById(R.id.camera);
        gallery = (ImageButton)findViewById(R.id.file);
        img = (ImageView)findViewById(R.id.profilePic);
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAddress = (EditText) findViewById(R.id.etAddress);
        bSave = (Button) findViewById(R.id.bSave);
        bClear = (Button) findViewById(R.id.bClear);


        pref = getApplicationContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        
        if(pref.getString("name", null)!= null){
			etName.setText(pref.getString("name", null));
		}
        
        if(pref.getString("phone", null)!= null){
			etPhone.setText(pref.getString("phone", null));
		}
        if(pref.getString("email", null)!= null){
			etEmail.setText(pref.getString("email", null));
		}
        if(pref.getString("address", null)!= null){
			etAddress.setText(pref.getString("address", null));
		}
		
		//show the user profile pic
		if(pref.getString("pic", null)!= null){		
			img.setImageBitmap(decodeBase64(pref.getString("pic", null)));
		}
        //open camera to take photo
        cam.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // in onCreate or any event where your want the user to
                // select a file
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        //open gallery to select photo
        gallery.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // in onCreate or any event where your want the user to
                // take a photo
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
        
        

        //remove the recent profile pic
//       cancel.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                pref.edit().remove("pic").commit();
//
//                Context mContext = getApplicationContext();
//                Drawable d = ContextCompat.getDrawable(mContext,R.drawable.user2);
//                img.setImageDrawable(d);
//
//                showMsg("Image deleted");
//            }
//        });
        

        
        bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor editor = pref.edit();
				Bitmap bm =((BitmapDrawable)img.getDrawable()).getBitmap();
				editor.putString("pic", encodeTobase64(bm) );
				editor.putString("name", etName.getText().toString());
				editor.putString("phone", etPhone.getText().toString());
				editor.putString("email", etEmail.getText().toString());
				editor.putString("address", etAddress.getText().toString());
				editor.commit(); 
				
				Intent myIntent = new Intent(ContactInfo.this, Academic.class);
                startActivityForResult(myIntent, 0);
				
			}
		});
        
        bClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etName.getText().clear();
				etPhone.getText().clear();
				etEmail.getText().clear();
				etAddress.getText().clear();
//				img.setImageDrawable(null);
			}
		});
    }

  //return result from camera or gallery
  	public void onActivityResult(int requestCode, int resultCode, Intent data) {
          if (resultCode == RESULT_OK) {
              if (requestCode == SELECT_PICTURE) {
                  Uri selectedImageUri = data.getData();
                  
                  img.setImageURI(selectedImageUri);
              }
              
              else if (requestCode == CAMERA_REQUEST) {  
                  Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                  img.setImageBitmap(photo);
              }  
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


    private void showMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
        toast.show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.backtomenu:
                startActivity(new Intent(this, EmployeeMain.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
