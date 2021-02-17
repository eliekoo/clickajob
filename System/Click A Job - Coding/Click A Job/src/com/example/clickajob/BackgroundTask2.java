package com.example.clickajob;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask2 extends AsyncTask<String,Void,String> {
//  AlertDialog alertDialog;
//    Context ctx;
//    BackgroundTask(Context ctx)
//    {
//      this.ctx =ctx;
//    }
//    @Override
//    protected void onPreExecute() {
//    alertDialog = new AlertDialog.Builder(ctx).create();
//        alertDialog.setTitle("Login Information....");
//    }

    @Override
    protected String doInBackground(String... params) {
        String loginr_url = "http://192.168.43.78/clickajob/loginr.php";
        String method = params[0];
        
        if(method.equals("loginr"))
        {
           String loginname = params[1];
            String loginpass = params[2];
            try {
                URL url = new URL(loginr_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("loginname","UTF-8")+"="+URLEncoder.encode(loginname,"UTF-8")+"&"+
                    URLEncoder.encode("loginpass","UTF-8")+"="+URLEncoder.encode(loginpass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }

//    @Override
//    protected void onPostExecute(String result) {
//      if(result.equals("Registration Success..."))
//      {
//          Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
//      }
//        else
//      {
//        alertDialog.setMessage(result);
//          alertDialog.show();
//      }
//
//    }
}

