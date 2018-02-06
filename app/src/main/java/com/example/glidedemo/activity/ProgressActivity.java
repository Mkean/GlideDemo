package com.example.glidedemo.activity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.glidedemo.R;
import com.example.glidedemo.utils.LoggingInterceptor;
import com.example.glidedemo.utils.ProgressListener;

import static com.example.glidedemo.R.id.progressDialog;

public class ProgressActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String IMG_URL = "http://ww3.sinaimg.cn/large/610dc034jw1f837uocox8j20f00mggoo.jpg";
    public static final String IMG = "http://guolin.tech/book.png";

    private ImageView mProgressLogImage;
    private ImageView mProgressDialogImage;
    private Button mProgressLog;
    private Button mProgressDialog;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        initView();
        initData();
        setListener();
    }


    private void setListener() {
        mProgressDialog.setOnClickListener(this);
        mProgressLog.setOnClickListener(this);
    }

    private void initData() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("加载中");
    }

    private void initView() {
        mProgressDialog = (Button) findViewById(progressDialog);
        mProgressLog = (Button) findViewById(R.id.progressLog);
        mProgressDialogImage = (ImageView) findViewById(R.id.progressDialog_image);
        mProgressLogImage = (ImageView) findViewById(R.id.progressLog_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case progressDialog:
                LoggingInterceptor.addListener(IMG, new ProgressListener() {
                    @Override
                    public void onProgress(int progress) {
                        dialog.setProgress(progress);
                    }
                });

                Glide.with(this)
                        .load(IMG)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .into(new GlideDrawableImageViewTarget(mProgressDialogImage) {
                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                                dialog.show();
                            }

                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                super.onResourceReady(resource, animation);
                                dialog.dismiss();
                                LoggingInterceptor.removeListener(IMG);

                            }
                        });


                break;
            case R.id.progressLog:
                Glide.with(this)
                        .load(IMG_URL)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(mProgressLogImage);
                break;

        }
    }
}
