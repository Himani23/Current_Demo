package com.aptech.current_demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JobInternship extends AppCompatActivity {
    Button add;
    EditText coursetext,companytext,locationtext,indurtext,stipend;
    String Course,Company, Location, InternshipDuration, Stipend;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/AddInter.php";

    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_internship);
        coursetext = (EditText) findViewById(R.id.course);
        companytext=(EditText) findViewById(R.id.comp);
        locationtext=(EditText)findViewById(R.id.loc);
        indurtext=(EditText)findViewById(R.id.Inter);
        stipend=(EditText)findViewById(R.id.sti);
        add = (Button) findViewById(R.id.addin);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course = coursetext.getText().toString();
                Company = companytext.getText().toString();
                Location = locationtext.getText().toString();
                InternshipDuration = indurtext.getText().toString();
                Stipend = stipend.getText().toString();

                if (Course.isEmpty()) {
                    coursetext.setError("Enter Your Course");
                } else if (Company.isEmpty()) {
                    companytext.setError("Enter Your Company");
                } else if (Location.isEmpty()) {
                    locationtext.setError("Enter Your Location");
                } else if (InternshipDuration.isEmpty()) {
                    indurtext.setError("Enter Your Duration");

                } else if (Stipend.isEmpty()) {
                    stipend.setError("Enter Your stipend");
                } else {
                    new Job().execute();
                }


            }
        });

    }
    class Job extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(com.aptech.current_demo.JobInternship.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            if (!pDialog.isShowing()) {
                pDialog.show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                // Building Parameters
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                param.add(new BasicNameValuePair("Course",Course));
                param.add(new BasicNameValuePair("Company",Company));
                param.add(new BasicNameValuePair("Location",Location));
                param.add(new BasicNameValuePair("Duration",InternshipDuration ));

                param.add(new BasicNameValuePair("Stipend",Stipend));



                // getting product details by making HTTP request
                String jsonstr = jsonParser.makeHttpRequest(URL, "POST", param);
                // check your log for json response
                Log.d("Processing", jsonstr);
                JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());
                // json success tag
                success = json.getInt("success");

                if (success == 1) {
                    Log.d("Register Successful!", json.toString());
                    String name = json.getString("message");
                    return name;
                } else {
                    Log.d("Error!", json.getString("message"));
                    return json.getString("message");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();

            if(success==1) {
                Toast.makeText(com.aptech.current_demo.JobInternship.this, s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(com.aptech.current_demo.JobInternship.this, Admin_Activity.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(com.aptech.current_demo.JobInternship.this, s, Toast.LENGTH_SHORT).show();
            }

        }
    }
}


