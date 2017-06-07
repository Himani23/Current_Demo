package com.aptech.current_demo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;

public class News_detail extends AppCompatActivity {


    News obj = Search_news.selectObj;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    ImageView img;
    ProgressDialog pDialog;
    Bitmap bitmap;
    String str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        txt1 = (TextView) findViewById(R.id.Ntitle);
        txt2 =(TextView) findViewById(R.id.Ndes);
        txt3=(TextView) findViewById(R.id.Ndate);
        img = (ImageView) findViewById(R.id.Nimage);

        txt1.setText(obj.getTitle());
        txt2.setText(obj.getDesc());
        txt3.setText(obj.getDate());

        new LoadImage().execute("http://vishalsinghrajput.000webhostapp.com/myapp/"+obj.getImagepath());
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(News_detail.this);
            pDialog.setMessage("Loading ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
                Log.d("bitmap",bitmap.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                pDialog.dismiss();
               img.setImageBitmap(bitmap);
            }else{

                pDialog.dismiss();
                // Toast.makeText(ProfileActivity.this, "Image Does Not exist", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
