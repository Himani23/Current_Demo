package com.aptech.current_demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Search_job extends AppCompatActivity {
     Spinner funarea;

    ListView listdata;
    Button apply;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/Fetch_FA.php";
    int success;
    String[]list=new String[]{"Select","I.T","Teacher","Able Seamen Jobs","Agricultural Worker Jobs" ,
            "Air Crew Member Jobs" ,
            "Air Crew Officer Jobs" ,
            "Air Traffic Controller Jobs" ,
            "Biochemist Jobs" ,
            "Biological Scientist Jobs" ,
            "Business Development Manager Jobs","Computer Science Engineer Jobs","Database Jobs",
   "Electrical Engineer Jobs","Fashion Designer Jobs","Lawyer Jobs","Network Admin OR Computer Systems Administrator Jobs","Network Systems Analyst Jobs","New Accounts Clerk Jobs","Nursery Manager Jobs","Nutritionist Jobs","Occupational Health Safety Specialist Jobs","Office and Administrative Support Worker Jobs","Office Clerk Jobs","Online Marketing Analyst Jobs","Pharmacist Jobs","Production Control Manager Jobs","Production Manager Jobs","Professor Jobs ","Project Manager Jobs","Receptionist and Information Clerk Jobs","Staff Psychologist Jobs","State Jobs","Statistical Assistant Jobs","Supervisor Correctional Officer Jobs","Supervisor of Police Jobs","Surgeon Jobs","System Administrator Jobs","Tax Examiner Jobs","Teacher Jobs","Teacher Assistant Jobs","Technical Director Jobs","Technical Program Manager Jobs","Telephone Operator Jobs","Trainer Jobs","Training Manager OR Development Manager Jobs","Transportation Attendant Jobs","Transportation Manager Jobs","University Jobs","User Experience Manager Jobs","Veterinarian Jobs","Vice President Of Human Resources Jobs","Web Developer Jobs","Word Processors and Typist Jobs","Zoologists"};

    List<Jobs> jobList = new LinkedList<Jobs>();
    static  Jobs selectObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_job);
        funarea= (Spinner) findViewById(R.id.functarea);
        listdata=(ListView)findViewById(R.id.lst) ;



        ArrayAdapter adapter = new ArrayAdapter(Search_job.this, android.R.layout.simple_dropdown_item_1line, list);
        funarea.setAdapter(adapter);

        funarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(!list[position].equals("Select")) {
                    //Toast.makeText(Search_job.this, "Selected =" + list[position], Toast.LENGTH_SHORT).show();
                    new SearchJob().execute(list[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        
        
        listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(Search_job.this, jobList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                selectObj = jobList.get(position);
startActivity(new Intent(Search_job.this,JobDetail.class));
            }
        });
    }

    class SearchJob extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Search_job.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            if (!pDialog.isShowing()) {
                pDialog.show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            String area = params[0];
            try {
                // Building Parameters
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                param.add(new BasicNameValuePair("Fuctional_area", area));
                // getting product details by making HTTP request
                String jsonstr = jsonParser.makeHttpRequest(URL, "POST", param);
                // check your log for json response
                Log.d("Processing", jsonstr);
                JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());
                // json success tag
                if (json != null) {
                    JSONArray categories = json.getJSONArray("Jobs");
                    jobList.clear();
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject catObj = (JSONObject) categories.get(i);
                        //public Jobs(String title, String salary, String exper, String skills, String location, String farea) {
                       Jobs item = new Jobs(catObj.getString("title"),catObj.getString("salary"),catObj.getString("experience"),catObj.getString("skills"),catObj.getString("location"),catObj.getString("Fuctional_area"),catObj.getString("company"),catObj.getString("email"));
                        //String title = catObj.getString("title");
                        jobList.add(item);
                    }
                } else {
                    Log.e("JSON Data", "Didn't receive any data from server!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog.isShowing())
                pDialog.dismiss();
            populateSpinner();

        }

        private void populateSpinner() {
            List<String> lables = new ArrayList<String>();


                for (int i = 0; i < jobList.size(); i++) {
                    lables.add(jobList.get(i).getTitle());
                }



            // Creating adapter for spinner
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Search_job.this,android.R.layout.simple_list_item_1, lables);



            // Drop down layout style - list view with radio button
            // spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
           listdata.setAdapter(spinnerAdapter);



        }
    }

}

