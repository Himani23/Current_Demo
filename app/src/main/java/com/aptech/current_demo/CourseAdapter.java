package com.aptech.current_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 25-05-2017.
 */

public class CourseAdapter extends BaseAdapter {

    Context context;

    private class Holder
    {
        TextView company,location,duration,stipend;

    }
    List<Item> names = new LinkedList<Item>();

    LayoutInflater inflater = null;

    public CourseAdapter(Context context, List names )
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

        Holder holder;
        View rowView ;
        //if(convertView==null) {
        rowView = inflater.inflate(R.layout.coursedetail, null);
        holder = new Holder();
        holder.company = (TextView) rowView.findViewById(R.id.Jcompany);
        holder.location = (TextView) rowView.findViewById(R.id.Jlocdet);
        holder.stipend = (TextView) rowView.findViewById(R.id.Jstip);
        holder.duration = (TextView) rowView.findViewById(R.id.JInterns);
        holder.company.setText(names.get(position).getTitle());
        holder.location.setText(names.get(position).getDescription());
        holder.duration.setText(names.get(position).getCdate());
        holder.stipend.setText(names.get(position).getStipend());

        return rowView;
    }
}
