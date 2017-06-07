package com.aptech.current_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by Administrator on 24-04-2017.
 */

public class MyListAdapter extends BaseAdapter {
    Context context;

    private class Holder
    {
        TextView tvname,tvMore;
        Button btnBuy;
        ImageView img;
    }


    List<Item> names = new LinkedList<Item>();

    LayoutInflater inflater = null;

    public MyListAdapter(Context context, List names )
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
        rowView = inflater.inflate(R.layout.list_ca, null);
        holder = new Holder();
        holder.tvname = (TextView) rowView.findViewById(R.id.list_title);
        holder.tvMore = (TextView) rowView.findViewById(R.id.more);
        holder.tvname.setText(names.get(position).getTitle());



        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
final int pos = position;

                Intent intent  = new Intent(context,View_CA_Desc.class);
                Bundle b = new Bundle();
                b.putString("Title",names.get(pos).getTitle());
                b.putString("Desc",names.get(pos).getDescription());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
