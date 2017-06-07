package com.aptech.current_demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Add_Job extends AppCompatActivity {
    Spinner salary;
    Spinner experience;
    Spinner functionalarea;
    Button addjobs;
    EditText JobTitle,JobSkills,JobLocation,company,email;
    String jobtitle,jobskills,joblocation,sal,expr,farea,cdetails,mail;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/Addjob.php";
    int success;

    String []list = new String[]{"None","Rs 0/Yr","<Rs 50,000/Yr","Rs 50,000-1 lakh/Yr " ,
            "Rs 1-1.5 lakh/Yr","Rs 1.5-2 lakh/Yr", "Rs 2-2.5 lakh/Yr " , "Rs 3-3.5 lakh/Yr", "Rs 4-4.5 lakh/Yr", "Rs 5-5.5 lakh/Yr", "Rs 6-6.5 lakh/Yr ", "Rs 7-7.5 lakh/Yr ","Rs 8-8.5 lakh/Yr","Rs 9-9.5 lakh/Yr","Rs 10-10.5 lakh/Yr","Rs 11-11.5 lakh/Yr","Rs 12-12.5 lakh/Yr","Rs 13-13.5 lakh/Yr","Rs 14-14.5 lakh/Yr","Rs 15-15.5 lakh/Yr","Rs 16-16.5 lakh/Yr","Rs 17-17.5 lakh/Yr","Rs 18-18.5 lakh/Yr","Rs 19-19.5 lakh/Yr","> 20 lakh" };
    String []list1=new String[]{"Fresher","experience"};
    String[]list2=new String[]{"I.T","Teacher","Able Seamen Jobs","Agricultural Worker Jobs" ,
            "Air Crew Member Jobs" ,
            "Air Crew Officer Jobs" ,
            "Air Traffic Controller Jobs" ,
            "Biochemist Jobs" ,
            "Biological Scientist Jobs",
            "Business Development Manager Jobs","Computer Science Engineer Jobs","Database Jobs",
            "Electrical Engineer Jobs","Fashion Designer Jobs","Lawyer Jobs","Network Admin OR Computer Systems Administrator Jobs","Network Systems Analyst Jobs","New Accounts Clerk Jobs","Nursery Manager Jobs","Nutritionist Jobs","Occupational Health Safety Specialist Jobs","Office and Administrative Support Worker Jobs","Office Clerk Jobs","Online Marketing Analyst Jobs","Pharmacist Jobs","Production Control Manager Jobs","Production Manager Jobs","Professor Jobs ","Project Manager Jobs","Psychology Teacher Jobs","Rail Transportation Worker Jobs","Receptionist and Information Clerk Jobs","Staff Psychologist Jobs","State Jobs","Statistical Assistant Jobs","Supervisor Correctional Officer Jobs","Supervisor of Police Jobs","Surgeon Jobs","System Administrator Jobs","Tax Examiner Jobs","Teacher Jobs","Teacher Assistant Jobs","Technical Director Jobs","Technical Program Manager Jobs","Telephone Operator Jobs","Trainer Jobs","Training Manager OR Development Manager Jobs","Transportation Attendant Jobs","Transportation Manager Jobs","University Jobs","User Experience Manager Jobs","Veterinarian Jobs","Vice President Of Human Resources Jobs","Web Developer Jobs","Word Processors and Typist Jobs","Zoologists"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__job);
        JobTitle = (EditText) findViewById(R.id.jobtitle);
        JobSkills = (EditText) findViewById(R.id.jobskills);
        JobLocation = (EditText) findViewById(R.id.joblocation);
        company=(EditText)findViewById(R.id.companydetails);
        salary = (Spinner) findViewById(R.id.salary);
        experience = (Spinner) findViewById(R.id.experience);
        functionalarea = (Spinner) findViewById(R.id.functionalarea);
        email = (EditText) findViewById(R.id.email);
        ArrayAdapter adapter = new ArrayAdapter(Add_Job.this, android.R.layout.simple_dropdown_item_1line, list);
        ArrayAdapter adapter1 = new ArrayAdapter(Add_Job.this, android.R.layout.simple_dropdown_item_1line, list1);
        ArrayAdapter adapter2 = new ArrayAdapter(Add_Job.this, android.R.layout.simple_dropdown_item_1line, list2);

        salary.setAdapter(adapter);
        experience.setAdapter(adapter1);
        functionalarea.setAdapter(adapter2);
        addjobs = (Button) findViewById(R.id.addjobs);
        addjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobtitle = JobTitle.getText().toString();
                jobskills = JobSkills.getText().toString();
                joblocation = JobLocation.getText().toString();
                cdetails=company.getText().toString();
                sal = salary.getSelectedItem().toString();
                expr = experience.getSelectedItem().toString();
                mail = email.getText().toString();
                farea = functionalarea.getSelectedItem().toString();
                if(jobtitle.isEmpty())
                {
                    JobTitle.setError("Enter Your Jobtitle");
                }
                else if(jobskills.isEmpty())
                {
                    JobSkills.setError("Enter Your jobskills ");
                }
                else if(joblocation.isEmpty())
                {
                   JobLocation.setError("Enter Your joblocation");
                }
                else
                {
                    new AddJob().execute();
                }
            }
        });


    }
    class AddJob extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Add_Job.this);
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
                param.add(new BasicNameValuePair("Title", jobtitle));
                param.add(new BasicNameValuePair("Skills", jobskills));
                param.add(new BasicNameValuePair("Location", joblocation));
                param.add(new BasicNameValuePair("Salary", sal));
                param.add(new BasicNameValuePair("Experience", expr));
                param.add(new BasicNameValuePair("Fuctional area", farea));
                param.add(new BasicNameValuePair("company",cdetails));
                param.add(new BasicNameValuePair("email",mail));

                // getting product details by making HTTP request
                String jsonstr = jsonParser.makeHttpRequest(URL, "POST", param);
                // check your log for json response
                Log.d("Processing", jsonstr);
                JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());
                // json success tag
                success = json.getInt("success");

                if (success == 1) {
                    Log.d("Added Successfully!", json.toString());
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
                Toast.makeText(Add_Job.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Add_Job.this, Admin_Activity.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(Add_Job.this, "Error While Adding!! Try Later!!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}


