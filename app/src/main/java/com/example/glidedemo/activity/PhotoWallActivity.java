package com.example.glidedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.glidedemo.config.Config;
import com.example.glidedemo.MyApp.MyApp;
import com.example.glidedemo.R;
import com.example.glidedemo.adapter.MyGridAdapter;
import com.example.glidedemo.dialog.MyViewPagerDialog;

import java.util.Arrays;
import java.util.List;

public class PhotoWallActivity extends AppCompatActivity {

    private List<String> list;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);
        list = Arrays.asList(MyApp.images);
        mGridView = (GridView) findViewById(R.id.gridView);
        MyGridAdapter adapter = new MyGridAdapter(list, this);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                new MyViewPagerDialog(PhotoWallActivity.this, list, position).show();
            }
        });
        getWindowHeightAndWidth();
    }



    public void getWindowHeightAndWidth() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        Config.WINDOW_WIDTH = outMetrics.widthPixels;
        Config.WINDOW_HEIGHT = outMetrics.heightPixels;
    }
}
