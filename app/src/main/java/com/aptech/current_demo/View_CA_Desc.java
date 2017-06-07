package com.aptech.current_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class View_CA_Desc extends AppCompatActivity {

    TextView txt1,txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__ca__desc);

        txt1 = (TextView) findViewById(R.id.CATitle);
        txt2 = (TextView) findViewById(R.id.CADesc);
        txt1.setText(getIntent().getExtras().getString("Title"));
        txt2.setText(getIntent().getExtras().getString("Desc"));
    }
}
