package com.example.glidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.glidedemo.activity.BasisActivity;
import com.example.glidedemo.activity.CustomActivity;
import com.example.glidedemo.activity.DownloadActivity;
import com.example.glidedemo.activity.GlideUrlActivity;
import com.example.glidedemo.activity.ListenerAndAnimationActivity;
import com.example.glidedemo.activity.PhotoWallActivity;
import com.example.glidedemo.activity.PriorityActivity;
import com.example.glidedemo.activity.ProgressActivity;
import com.example.glidedemo.activity.TargetActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBasis;
    private Button mDownloadOnly;
    private Button mTarget;
    private Button mListener;
    private Button mPriority;
    private Button mCustom;
    private Button mProgress;
    private Button mPhotoWall;
    private Button mGlideUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {
        mBasis.setOnClickListener(this);
        mDownloadOnly.setOnClickListener(this);
        mTarget.setOnClickListener(this);
        mListener.setOnClickListener(this);
        mPriority.setOnClickListener(this);
        mCustom.setOnClickListener(this);
        mProgress.setOnClickListener(this);
        mPhotoWall.setOnClickListener(this);
        mGlideUrl.setOnClickListener(this);
    }

    private void initView() {
        mBasis = (Button) findViewById(R.id.basis);
        mDownloadOnly = (Button) findViewById(R.id.downloadOnly);
        mTarget = (Button) findViewById(R.id.target);
        mListener = (Button) findViewById(R.id.listener);
        mPriority = (Button) findViewById(R.id.priority);
        mCustom = (Button) findViewById(R.id.custom);
        mProgress = (Button) findViewById(R.id.progress);
        mPhotoWall = (Button) findViewById(R.id.photoWall);
        mGlideUrl = (Button) findViewById(R.id.glideUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //基础用法
            case R.id.basis:
                $startActivity(BasisActivity.class);
                break;
            //downloadOnly获取路径
            case R.id.downloadOnly:
                $startActivity(DownloadActivity.class);
                break;
            //自定义target
            case R.id.target:
                $startActivity(TargetActivity.class);
                break;
            case R.id.glideUrl:
                $startActivity(GlideUrlActivity.class);
                break;
            //动画及监听
            case R.id.listener:
                $startActivity(ListenerAndAnimationActivity.class);
                break;
            //优先级
            case R.id.priority:
                $startActivity(PriorityActivity.class);
                break;
            //自定义模块
            case R.id.custom:
                $startActivity(CustomActivity.class);
                break;
            case R.id.progress:
                $startActivity(ProgressActivity.class);
                break;
            case R.id.photoWall:
                $startActivity(PhotoWallActivity.class);
                break;
        }
    }

    public void $startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
