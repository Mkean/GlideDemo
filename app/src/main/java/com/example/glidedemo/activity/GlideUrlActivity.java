package com.example.glidedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.glidedemo.R;
import com.example.glidedemo.custom.MyGlideUrl;

public class GlideUrlActivity extends AppCompatActivity {
    public static final String IMG_URL = "http://ww3.sinaimg.cn/large/610dc034jw1f837uocox8j20f00mggoo.jpg";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_url);
        mImageView = (ImageView) findViewById(R.id.image_View);
    }

    public void onClick(View view) {
        Glide.with(this)
                .load(new MyGlideUrl(IMG_URL))
                .into(mImageView);
    }
}
