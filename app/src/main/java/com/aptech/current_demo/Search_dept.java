package com.aptech.current_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Search_dept extends AppCompatActivity {
    Spinner item;
    Button choosefile;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dept);
        item = (Spinner) findViewById(R.id.item);
        choosefile = (Button) findViewById(R.id.filechoose);
        submit=(Button)findViewById(R.id.subdep);
        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
