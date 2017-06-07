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

public class Change_Password extends AppCompatActivity {
    EditText newpass;
    EditText newpass2;
    Button changepassword;
    ProgressDialog pDialog;
    String pass,pass2;
    JSONParser jsonParser = new JSONParser();
    String URL = "http://vishalsinghrajput.000webhostapp.com/myapp/Change_Password.php";
    int success;
    String n = LoginActivity.user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__password);
        newpass = (EditText) findViewById(R.id.newpass);
        newpass2 = (EditText) findViewById(R.id.newpass1);

        changepassword = (Button) findViewById(R.id.Changepswd);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = newpass.getText().toString();
                pass2 = newpass2.getText().toString();
                if (pass.equals((pass2))) {

                    new GetCp().execute();
                } else
                    Toast.makeText(Change_Password.this, "Password does 'nt Match", Toast.LENGTH_SHORT).show();
            }
        });

    }
    class GetCp extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new ProgressDialog(Change_Password.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            if(!pDialog.isShowing()) {
                pDialog.show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                // Building Parameters
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                param.add(new BasicNameValuePair("email", n));
                param.add(new BasicNameValuePair("Password",pass));


                // getting product details by making HTTP request
                String jsonstr = jsonParser.makeHttpRequest(URL, "POST", param);
                // check your log for json response
                Log.d("Login attempt", jsonstr);
                JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());
                // json success tag
                success = json.getInt("success");

                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    String name = json.getString("username");
                    return name;
                } else {
                    Log.d("Login Failure!", json.getString("message"));
                    return "error";

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
                Toast.makeText(Change_Password.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Change_Password.this, Home_Page.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(Change_Password.this, s, Toast.LENGTH_SHORT).show();
            }

        }
    }
}




