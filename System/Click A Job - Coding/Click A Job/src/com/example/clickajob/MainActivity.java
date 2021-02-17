package com.example.clickajob;


import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ImageButton ibEmployee,ibEmployer;
	private boolean backPressToExit;
	TextView aboutus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibEmployee = (ImageButton) findViewById(R.id.ibEmployee);
        ibEmployer = (ImageButton) findViewById(R.id.ibEmployer);
        aboutus = (TextView) findViewById(R.id.tvaboutus);

            ibEmployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
            });
            
        ibEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginR.class);
                startActivity(intent);
            }
        });
        
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Aboutus.class);
                startActivity(intent);
            }
        });
    }
    

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;                       
            }
        }, 2000);
    }
    
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
// 
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        //respond to menu item selection
//        switch (item.getItemId()) {
//            case R.id.aboutus:
//                startActivity(new Intent(this, Aboutus.class));
//                return true;
////            case R.id.MyResume:
////                //startActivity(new Intent(this, EmployeeMain.class));
////                return true;
////            case R.id.MyView:
////                startActivity(new Intent(this, EmployeeView.class));
////                return true;
////                
////            case R.id.LogOut:
////            	logout();
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
