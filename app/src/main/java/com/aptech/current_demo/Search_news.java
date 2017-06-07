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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_news extends AppCompatActivity {
    ListView listView;
    List<News> caList = new ArrayList<News>();
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    static News selectObj;
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/View_news.php";
    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);

        listView = (ListView) findViewById(R.id.list_news);
        new Search_news.Getnews().execute();

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Toast.makeText(Search_news.this, "Selected = "+caList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            selectObj = caList.get(position);
              startActivity(new Intent(Search_news.this,News_detail.class));

          }
      });
    }

    class Getnews extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new ProgressDialog(Search_news.this);
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
                    JSONArray categories = json.getJSONArray("news");

                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject catObj = (JSONObject) categories.get(i);
                        News n = new News(catObj.getString("title"),catObj.getString("desc"),catObj.getString("imgpath"),catObj.getString("date"),0);
                        caList.add(n);
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
                lables.add("-> "+caList.get(i).getTitle());
            }

            // Creating adapter for spinner
           ArrayAdapter spinnerAdapter = new ArrayAdapter(Search_news.this,android.R.layout.simple_spinner_item, lables);

            // Drop down layout style - list view with radio button
            //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

          //  NewsAdapter adapter = new NewsAdapter(Search_news.this,lables);

            // attaching data adapter to spinner
            listView.offsetTopAndBottom(20);
            listView.setAdapter(spinnerAdapter);
        }
    }

}
