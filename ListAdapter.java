package com.example.dailynews;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ListAdapter extends ArrayAdapter<News> {

    private int resourceLayout;
    private Context mContext;
    ArrayList<News> list;

    public ListAdapter(Context context, int resource, ArrayList<News> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.list = items;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position ,View convertView, ViewGroup parent){

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        News news = getItem(position);


        TextView urlTextView = v.findViewById(R.id.url);
        TextView headingTextView = v.findViewById(R.id.heading);
        TextView descriptionTextView = v.findViewById(R.id.desc);
        ImageView img=v.findViewById(R.id.img_view);


        urlTextView.setText("URL : " + news.getUrl());
        headingTextView.setText(news.getHeading());
        descriptionTextView.setText("Description : " + news.getDescription());
        Glide.with(mContext).load(news.getUrl()).into(img);

        return v;
    }

}
