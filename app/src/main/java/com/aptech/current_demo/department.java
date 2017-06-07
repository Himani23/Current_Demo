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
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class department extends AppCompatActivity {
    ListView tech;
    String[]list=new String[]{"Select","CSE/IT","Mechanical Engineering","Civil Engineering ","Electrical Engineering","Law","Business Management and Commerce","Medical/Non-Medical"};
  // List<String> techList = new LinkedList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        tech = (ListView) findViewById(R.id.Tech);
        ArrayAdapter adapter = new ArrayAdapter(department.this, android.R.layout.simple_list_item_1, list);
        tech.setAdapter(adapter);
        tech.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!list[position].equals("Select")) {
                    Toast.makeText(department.this, "Selected =" + list[position], Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(department.this, TutorialList.class);

                    Bundle b=new Bundle();
                    b.putString("subject",list[position]);
                    intent.putExtras(b);
                    startActivity(intent);
                    //new department().dep.execute(list[position]);
                }
            }

        });

    //});
    }

        }
