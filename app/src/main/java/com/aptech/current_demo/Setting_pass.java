package com.aptech.current_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Setting_pass extends AppCompatActivity {
    Button changep;
    Button feedbck;
    Button help;
    Button logout;
String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_pass);

        changep = (Button) findViewById(R.id.changeps);
        feedbck = (Button) findViewById(R.id.feedback);
        help = (Button) findViewById(R.id.help);
        logout = (Button) findViewById(R.id.logout);


        changep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(Setting_pass.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Setting_pass.this, Change_Password.class);
                startActivity(intent);


            }
        });
        feedbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting_pass.this, feedback.class);
                startActivity(intent);

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting_pass.this, HelpActivity.class);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("MY_PREF",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(Setting_pass.this,LoginActivity.class));
            }
        });
    }}
