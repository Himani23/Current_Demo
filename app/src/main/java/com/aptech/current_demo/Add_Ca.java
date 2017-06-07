package com.aptech.current_demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Add_Ca extends AppCompatActivity {
    Button add;
    EditText Title,Description;
    DatePicker dp;
    String title,description,date;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/Addca.php";
int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__ca);
        Title = (EditText) findViewById(R.id.title);
        Description=(EditText) findViewById(R.id.description);
        dp = (DatePicker) findViewById(R.id.caDate);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = Title.getText().toString();
                description = Description.getText().toString();
                date = dp.getDayOfMonth()+"-"+(dp.getMonth()+1)+"-"+dp.getYear();
               if(title.isEmpty())
                {

                }else if(description.isEmpty())
                {

                }
                else
                {
                    new Add_ca().execute();
                }

            }
        });
    }

    class Add_ca extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Add_Ca.this);
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
                param.add(new BasicNameValuePair("Title", title));
                param.add(new BasicNameValuePair("Description", description));
                param.add(new BasicNameValuePair("Date", date));

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
                Toast.makeText(Add_Ca.this, s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Add_Ca.this, Admin_Activity.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(Add_Ca.this, s, Toast.LENGTH_SHORT).show();
            }

        }
    }
}


