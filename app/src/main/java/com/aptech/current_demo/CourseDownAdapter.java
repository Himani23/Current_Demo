package com.aptech.current_demo;

/**
 * Created by Vishal singh rajput on 5/11/2017.
 */

import android.widget.ImageButton;
import android.widget.Toast;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by win 7 on 22-12-2016.
 */
public class CourseDownAdapter extends BaseAdapter {


    private String FILE_URL= "http://vishalsinghrajput.000webhostapp.com/myapp/";
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    LayoutInflater inflater;
    Context context;
    ArrayList<DownCourse> objects;

    private class ViewHolder {
        TextView textView2;
        ImageButton btnDown;
    }



    public CourseDownAdapter(Context context, ArrayList<DownCourse> objects)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.downloadlist, null);

            holder.textView2 = (TextView) convertView.findViewById(R.id.filename);
            holder.btnDown = (ImageButton) convertView.findViewById(R.id.downbtn);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final int pt =position;

        holder.textView2.setText(objects.get(position).getFilename());
        String downbtn = holder.textView2.getText().toString();

        holder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fpath = objects.get(position).getFilepath();
                String ss = objects.get(position).getSubject();
                String fname = objects.get(position).getFilename();
                Log.d("file name", fpath+"---"+fname);
                //Toast.makeText(context, fpath+"==>>"+fname, Toast.LENGTH_LONG).show();
                new DownloadFileFromURL().execute(fpath,fname);
            }
        });
        return convertView;
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new ProgressDialog(context);
            pDialog.setMessage("Downloading...");
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            if(!pDialog.isShowing()) {
                pDialog.show();
            }
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count ;
            try {
                URL url = new URL(FILE_URL+f_url[0]);
                Log.d("url",url.toString());
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),8192);
                File f = new File("/sdcard/",f_url[1]);
                Log.d("File Name",f.getAbsolutePath());
                // Output stream
                OutputStream output = new FileOutputStream(f);


                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    Log.d("Count=",count+"");
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return f_url[1];
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            //dismissDialog(0);
            pDialog.dismiss();

            Toast.makeText(context, "File Downloaded at " + file_url, Toast.LENGTH_LONG).show();
        }
    }
}

