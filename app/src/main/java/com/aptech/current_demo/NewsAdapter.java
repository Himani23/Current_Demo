package com.aptech.current_demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 24-04-2017.
 */

public class NewsAdapter extends BaseAdapter {
    Context context;
    Bitmap bitmap;
    ProgressDialog pDialog;
    public class Holder
    {
        TextView nTitle;
        //ImageView img;
    }

    Holder holder = new Holder();
    List<News> names = new LinkedList<News>();

    LayoutInflater inflater = null;

    public NewsAdapter(Context context, List names )
    {
        this.names = names;
        this.context = context;
        inflater =LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView ;
        //if(convertView==null) {
        rowView = inflater.inflate(R.layout.view_news, null);

        holder.nTitle = (TextView) rowView.findViewById(R.id.Ntitle);
        //holder.img = (ImageView) rowView.findViewById(R.id.imgpath);
        holder.nTitle.setText(names.get(position).getTitle());
        //Log.d("Image","http://vishalsinghrajput.000webhostapp.com/myapp/"+names.get(position).getImagepath());

       //new LoadImage().execute("http://vishalsinghrajput.000webhostapp.com/myapp/"+names.get(position).getImagepath());

              return rowView;
    }





}
