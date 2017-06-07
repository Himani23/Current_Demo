package com.aptech.current_demo;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home_Page extends AppCompatActivity {
    ImageButton currentaffairs;
    ImageButton Search;
    ImageButton menu;
    ImageButton news;
    ImageButton FresherCorner;
    ImageButton TutorialsPoint;
    ImageButton Settings;
    ImageButton ContactUs;
    ImageButton AboutUs;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        currentaffairs = (ImageButton) findViewById(R.id.ca);
        Search = (ImageButton) findViewById(R.id.search);
        menu = (ImageButton) findViewById(R.id.menu);
        news = (ImageButton) findViewById(R.id.news);
        FresherCorner = (ImageButton) findViewById(R.id.fc);
        TutorialsPoint = (ImageButton) findViewById(R.id.tp);
        Settings = (ImageButton) findViewById(R.id.setting);
        ContactUs = (ImageButton) findViewById(R.id.cu);
        AboutUs = (ImageButton) findViewById(R.id.about);


        currentaffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Page.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, Search_current.class);
                startActivity(intent);


            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Page.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, Search_job.class);
                startActivity(intent);


            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cricbuzz.com/"));
                startActivity(intent);



            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Page.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, Search_news.class);
                startActivity(intent);


            }
        });
        FresherCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Page.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, InternList.class);
                startActivity(intent);




            }
        });

        TutorialsPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Page.this, "Welcome ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, department.class);
                startActivity(intent);
            }
        });
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home_Page.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, Setting_pass.class);

                startActivity(intent);
            }
        });
        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home_Page.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, Contact_Us.class);
                startActivity(intent);
            }
        });
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home_Page.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Page.this, About_Us.class);
                startActivity(intent);
            }
        });
    }
}
