package com.example.glidedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.glidedemo.R;

import java.util.List;

/**
 * 作者：王庆
 * 时间：2018/2/5
 */

public class MyGridAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public MyGridAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = View.inflate(context, R.layout.gridview_layout, null);
        ImageView mGridView = view.findViewById(R.id.grid_image);
        Glide.with(context).load(list.get(position))
                .placeholder(R.drawable.default_pic)
                .override(180, 180)
                .centerCrop()
                .into(mGridView);
        return view;
    }
}
