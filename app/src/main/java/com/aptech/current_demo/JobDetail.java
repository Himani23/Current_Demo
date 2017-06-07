package com.aptech.current_demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class JobDetail extends AppCompatActivity {

    Jobs obj = Search_job.selectObj;
    Uri selectedURI;
    Button apply,selectResume;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;
    String Jobtitle,JobSkills,joblocation,sal,expr,cmpy,selectedFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        apply = (Button)  findViewById(R.id.applyJob);
        selectResume = (Button)  findViewById(R.id.resume);
        txt1  = (TextView) findViewById(R.id.Jtitle);
        txt2  = (TextView) findViewById(R.id.Jlocation);
        txt3  = (TextView) findViewById(R.id.Jskills);
        txt4  = (TextView) findViewById(R.id.Jsal);
        txt5  = (TextView) findViewById(R.id.Jprofile);
        txt6  = (TextView) findViewById(R.id.Jcompany);
        txt7 = (TextView) findViewById(R.id.rpath);
        txt1.setText(obj.getTitle());
        txt2.setText(obj.getLocation());
        txt3.setText(obj.getSkills());
        txt4.setText(obj.getSalary());
        txt5.setText(obj.getExper());
        txt6.setText(obj.getCompany());

        selectResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent .setType("vnd.android.cursor.dir/email");
                String[] TO = {obj.getEmail()};
                String[] CC = {""};
                emailIntent.setData(Uri.parse("mailto:"+TO[0]));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                String fileLocation="";
                emailIntent .putExtra(Intent.EXTRA_STREAM, selectedURI);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.d("Finished sending ", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(JobDetail.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==123 && resultCode== Activity.RESULT_OK)
        {
            selectedURI = data.getData();
            selectedFilePath = FilePath.getPath(JobDetail.this,selectedURI);
            if(selectedFilePath!=null && !selectedFilePath.equals("")) {
                txt7.setText(selectedFilePath);
            }
            else
            {
                txt7.setText("File cannot be selected");
            }
        }
    }
}
