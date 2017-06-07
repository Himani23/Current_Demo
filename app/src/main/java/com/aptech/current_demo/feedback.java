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

public class feedback extends AppCompatActivity {
    Button feedback;
    EditText name,sugg;
    String str1;
    String str2;
    int success;
    ProgressDialog pDialog;
    JSONParser jsonParser=new JSONParser();
    String URL="http://vishalsinghrajput.000webhostapp.com/myapp/Feedback.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedback=(Button)findViewById(R.id.feedback);
        sugg=(EditText)findViewById(R.id.sugg);
        name=(EditText)findViewById(R.id.name);
                feedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str1 = name.getText().toString();
                        str2 = sugg.getText().toString();
                        if (str1.isEmpty()) {
                            name.setError("Please enter your name");
                        } else if (str2.isEmpty()) {
                            sugg.setError("Please enter the feedback");
                        } else {
                            new Feed().execute();

                        }
                    }

                });

                }


        class Feed extends AsyncTask<String, String, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(feedback.this);
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
                    param.add(new BasicNameValuePair("name", str1));
                    param.add(new BasicNameValuePair("feedback", str2));
                    param.add(new BasicNameValuePair("email", LoginActivity.user));

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
                    Toast.makeText(feedback.this, s, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(feedback.this, Admin_Activity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(feedback.this, s, Toast.LENGTH_SHORT).show();
                }

            }
        }
    }



