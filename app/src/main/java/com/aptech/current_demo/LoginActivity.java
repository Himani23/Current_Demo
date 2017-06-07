package com.aptech.current_demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {
    Button button2,btnCancel;
    Button button1;
    EditText txtname,Passwordtext;
    ProgressDialog pDialog;
    String username,Password;
    JSONParser jsonParser = new JSONParser();
    String LOGIN_URL = "http://vishalsinghrajput.000webhostapp.com/myapp/login.php";
int success;
    static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        btnCancel = (Button) findViewById(R.id.button3);
        txtname = (EditText) findViewById(R.id.username);
        Passwordtext=(EditText)findViewById(R.id.Lpassword);

        Intent intent = new Intent(LoginActivity.this, Home_Page.class);
        SharedPreferences sp = getSharedPreferences("MY_PREF",MODE_PRIVATE);

        String n = sp.getString("username","");
        String p = sp.getString("password","");

        if(!n.isEmpty() && !p.isEmpty()) {
            startActivity(intent);

        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = txtname.getText().toString();
                Password = Passwordtext.getText().toString();
                 if(username.isEmpty())
                {
                  txtname.setError("Enter Your UserName");
                }
                else if(Password.isEmpty())
                {
                  Passwordtext.setError("Enter Your Password");
            }
            else if(username.equals("admin") && Password.equals("admin"))
                 {


                     Intent intent = new Intent(LoginActivity.this,Admin_Activity.class);
                     startActivity(intent);
                 }
            else {

                   new CheckLogin().execute();
                 }
                //Toast.makeText(LoginActivity.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(LoginActivity.this,Home_Page.class);
              // startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"register",Toast.LENGTH_SHORT).show();


                Intent intent =new Intent(LoginActivity.this,Registration.class);


                startActivity(intent);


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder d = new AlertDialog.Builder(LoginActivity.this);
                d.setTitle("Do You want to exit?");
                d.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                d.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                d.show();
            }
        });
    }


    class CheckLogin extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new ProgressDialog(LoginActivity.this);
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
                param.add(new BasicNameValuePair("email", username));
                param.add(new BasicNameValuePair("Password",Password));


                // getting product details by making HTTP request
                String jsonstr = jsonParser.makeHttpRequest(LOGIN_URL, "POST", param);
                // check your log for json response
                Log.d("Login attempt", jsonstr);
               JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());
                // json success tag
                success = json.getInt("success");

                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    String name = json.getString("username");
                    user = name;
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
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, Home_Page.class);
                SharedPreferences sp = getSharedPreferences("MY_PREF",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username",username);
                editor.putString("password",Password);
                editor.commit();

                startActivity(intent);
            }
            else
            {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
