package com.example.glidedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.glidedemo.R;

public class PriorityActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String HEIGHT_IMG = "https://b-ssl.duitang.com/uploads/item/201505/23/20150523091525_n2xkL.jpeg";
    public static final String MIDDLE_IMG = "https://a-ssl.duitang.com/uploads/item/201701/19/20170119220617_WwTh8.jpeg";
    public static final String LOW_IMG = "https://b-ssl.duitang.com/uploads/item/201705/14/20170514095630_L5aEZ.jpeg";

    private Button mPriorityClick;
    private ImageView mHeightImage;
    private ImageView mMiddleImage;
    private ImageView mLowImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);
        initView();
        setListener();
    }

    private void setListener() {
        mPriorityClick.setOnClickListener(this);
    }

    private void initView() {
        mPriorityClick = (Button) findViewById(R.id.priorityClick);
        mHeightImage = (ImageView) findViewById(R.id.height_image);
        mMiddleImage = (ImageView) findViewById(R.id.middle_image);
        mLowImage = (ImageView) findViewById(R.id.low_image);
    }

    //优先级，优先级高的先加载，优先级低的后加载
    @Override
    public void onClick(View v) {
        Glide.with(this)
                .load(HEIGHT_IMG)
                .priority(Priority.HIGH)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mHeightImage);

        Glide.with(this)
                .load(MIDDLE_IMG)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mMiddleImage);

        Glide.with(this)
                .load(LOW_IMG)
                .priority(Priority.LOW)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mLowImage);
    }
}
