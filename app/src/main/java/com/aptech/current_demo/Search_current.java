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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_current extends AppCompatActivity {

    Spinner sp;
    ListView listView;
    String cadate;
    List<String> caList = new ArrayList<String>();
    List<Item> caList1 = new ArrayList<Item>();
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/CADates.php";
    String URL1 = "http://vishalsinghrajput.000webhostapp.com/myapp/Ca_Date.php";
    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_current);

        sp = (Spinner) findViewById(R.id.date1);
        listView = (ListView) findViewById(R.id.list_Curr_Affairs);

        new GetCA().execute();


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    cadate = sp.getSelectedItem().toString();
               // Toast.makeText(Search_current.this, "Date="+cadate, Toast.LENGTH_SHORT).show();
                    new GetCADate().execute();

                //listView.setAdapter(new MyListAdapter(Search_current.this,caList));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class GetCA extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new ProgressDialog(Search_current.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            if(!pDialog.isShowing()) {
                pDialog.show();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            String jsonstr = jsonParser.makeHttpRequest(URL, "POST", param);
            try {
                JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());

                if (json != null) {
                    JSONArray categories = json.getJSONArray("CA");

                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject catObj = (JSONObject) categories.get(i);
                      // Item item = new Item(catObj.getString("title"),catObj.getString("desc"),catObj.getString("date"));
                       caList.add(catObj.getString("cadate"));
                    }
                } else {
                    Log.e("JSON Data", "Didn't receive any data from server!");
                }
            }catch (JSONException e) {
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

            for (int i = 0; i < caList.size(); i++) {
                lables.add(caList.get(i));
            }

            // Creating adapter for spinner
          ArrayAdapter<String> adapter = new ArrayAdapter<String>(Search_current.this,android.R.layout.simple_spinner_item, lables);

           // MyListAdapter adapter = new MyListAdapter(Search_current.this,lables);
            // Drop down layout style - list view with radio button
           // spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching dat
            // a adapter to spinner
            sp.setAdapter(adapter);
        }
    }

    class GetCADate extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new ProgressDialog(Search_current.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            if(!pDialog.isShowing()) {
                pDialog.show();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("cadate", cadate));
            String jsonstr = jsonParser.makeHttpRequest(URL1, "POST", param);
            try {
                JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());

                if (json != null) {
                    JSONArray categories = json.getJSONArray("CA");

                    caList1.clear();

                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject catObj = (JSONObject) categories.get(i);
                        Item item = new Item(catObj.getString("Title"),catObj.getString("Description"),catObj.getString("cadate"));
                        caList1.add(item);
                    }
                } else {
                    Log.e("JSON Data", "Didn't receive any data from server!");
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog.isShowing())
                pDialog.dismiss();
            populateSpinner1();

        }

        private void populateSpinner() {
            List<String> lables = new ArrayList<String>();
lables.add("Select");
            for (int i = 0; i < caList.size(); i++) {
                lables.add(caList.get(i));
            }

            // Creating adapter for spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Search_current.this,android.R.layout.simple_spinner_item, lables);

            // MyListAdapter adapter = new MyListAdapter(Search_current.this,lables);
            // Drop down layout style - list view with radio button
            // spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            sp.setAdapter(adapter);
        }
        private void populateSpinner1() {
            List<Item> lables = new ArrayList<Item>();
            //lables.clear();
            for (int i = 0; i < caList1.size(); i++) {
                lables.add(caList1.get(i));
            }


            // Creating adapter for spinner
          //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(Search_current.this,android.R.layout.simple_list_item_1, lables);

            MyListAdapter adapter = new MyListAdapter(Search_current.this,lables);
            // Drop down layout style - list view with radio button
            // spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            listView.setAdapter(adapter);
        }
    }
}
