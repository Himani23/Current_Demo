package com.aptech.current_demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class TutorialList extends AppCompatActivity {

    ListView listview;
    List<DownCourse> list = new ArrayList<DownCourse>();
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String subject;
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/Tutorial_List.php";
    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_list);
        //subject = getIntent().getExtras().getString("subject");
        listview  = (ListView) findViewById(R.id.Tlist);
        Intent intent =getIntent();
        Bundle b = intent.getExtras();
        subject = b.getString("subject");
        //Toast.makeText(TutorialList.this, "Select "+ subject, Toast.LENGTH_SHORT).show();
        new getTutorials().execute();
    }

    private class getTutorials extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TutorialList.this);
            pDialog.setMessage("Fetching Files...");
            pDialog.setCancelable(false);
            pDialog.show();


        }

        @Override
        protected Void doInBackground(Void... arg0) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("subject", subject));


            String jsonstr = jsonParser.makeHttpRequest(URL, "POST", params);

            if (jsonstr != null) {
                try {

                    JSONObject json = new JSONObject(jsonstr);
                    Log.e("Response: ", "> " + json);

                    JSONArray jarray = json.getJSONArray("list");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject catObj = (JSONObject) jarray.get(i);
                        DownCourse course = new DownCourse(subject,catObj.getString("filename"),catObj.getString("filepath"));
                        list.add(course);
                        //list.add(obj);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            populateList();
        }

    }



    private void populateList() {
        ArrayList<DownCourse> lables = new ArrayList<DownCourse>();

        for (int i = 0; i < list.size(); i++) {
            lables.add(list.get(i));
        }

        // Creating adapter for spinner
        CourseDownAdapter adapter = new CourseDownAdapter(TutorialList.this,lables);

        // attaching data adapter to spinner
        listview.setAdapter(adapter);
    }

}
