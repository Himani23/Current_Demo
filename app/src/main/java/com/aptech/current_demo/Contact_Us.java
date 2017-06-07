package com.aptech.current_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
public class Contact_Us extends AppCompatActivity {


    TextView contact1,email1,contact2,email2,email3;
    String num1,num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        contact2 = (TextView) findViewById(R.id.c2);
        contact1 = (TextView) findViewById(R.id.c1);
        email1 = (TextView) findViewById(R.id.e1);
        email2=(TextView)findViewById(R.id.e2);
        email3=(TextView)findViewById(R.id.e3);
        num1 = contact1.getText().toString();
        num2 = contact2.getText().toString();
        Log.d("Number:",num1);
        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+num1));
                startActivity(intent);
            }


        });
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+num2));
                startActivity(intent);
            }


        });


        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String[] TO = {email1.getText().toString()};
                String[] CC = {""};
                emailIntent.setData(Uri.parse("mailto:"+TO[0]));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.d("Finished sending ", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Contact_Us.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        email2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String[] TO = {email2.getText().toString()};
                String[] CC = {""};
                emailIntent.setData(Uri.parse("mailto:"+TO[0]));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.d("Finished sending ", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Contact_Us.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        email3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String[] TO = {email3.getText().toString()};
                String[] CC = {""};
                emailIntent.setData(Uri.parse("mailto:"+TO[0]));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.d("Finished sending ", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Contact_Us.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
