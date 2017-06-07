package com.aptech.current_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Admin_Activity extends AppCompatActivity {
ImageButton AddCurrentAffairs;
    ImageButton AddJobs;
    ImageButton Addnews;
    ImageButton JobInternship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        JobInternship=(ImageButton)findViewById(R.id.jobin);
        AddCurrentAffairs=(ImageButton)findViewById(R.id.AddCurrentAffairs);
        AddJobs=(ImageButton)findViewById(R.id.AddJobs);
        Addnews=(ImageButton)findViewById(R.id.addnew);
        JobInternship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Admin_Activity.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Admin_Activity.this,JobInternship.class);
                startActivity(intent);
            }
        });

        Addnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Admin_Activity.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Admin_Activity.this,AddNews.class);
                startActivity(intent);

            }
        });

        AddJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Admin_Activity.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Admin_Activity.this,Add_Job.class);
             startActivity(intent);
            }
        });
        AddCurrentAffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Admin_Activity.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Admin_Activity.this,Add_Ca.class);
                //Intent intent1=new Intent(LoginActivity.this,Registration.class);

                startActivity(intent);

            }
        });
    }
}
