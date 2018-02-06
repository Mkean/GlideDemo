package com.example.glidedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.glidedemo.R;
import com.example.glidedemo.custom.MyLayout;

/*
*
* 这篇主要讲的是自定义Target。我们从target的继承构造可以看出，是相当复杂的，实现Target接口的子类非常多，
* 不过我们要进行自定义的话，只需要在两个Target的基础上自定义就行了，一个是SimpleTarget,一个是ViewTarget。
*
*
*
*
* */
public class TargetActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String IMG_URL = "https://a-ssl.duitang.com/uploads/item/201701/19/20170119220617_WwTh8.jpeg";
    private Button mSimpleTarget;
    private Button mViewTarget;
    private ImageView mSimpleTargetImage;
    private MyLayout mViewTargetImage;
    private SimpleTarget<GlideDrawable> simpleTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        initView();
        initData();
        setListener();
    }

    private void setListener() {
        //监听事件
        mSimpleTarget.setOnClickListener(this);
        mViewTarget.setOnClickListener(this);
    }

    private void initData() {
        /*
        * 自定义SimpleTarget，最后会返回SimpleTarget对象
        * 里面的泛型不一定是GlideDrawable,如果你知道你加载的是一张静态图，而不是Gif的话，
        * 可以直接将泛型指定Bitmap，然后在加载图片的时候调用asBitmap（）方法强制指定这是一张静态图
        * */
        simpleTarget = new SimpleTarget<GlideDrawable>() {
            /*
            * 重写方法
            * GlideDrawable：返回回来的图片信息
            * GlideAnimation：动画
            * */
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                mSimpleTargetImage.setImageDrawable(resource);
            }
        };
    }

    private void initView() {
        //初始化组件
        mSimpleTarget = (Button) findViewById(R.id.simpleTarget);
        mViewTarget = (Button) findViewById(R.id.viewTarget);
        mViewTargetImage = (MyLayout) findViewById(R.id.viewTarget_image);
        mSimpleTargetImage = (ImageView) findViewById(R.id.simpleTarget_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simpleTarget:
                Glide.with(this).load(IMG_URL).into(simpleTarget);
                break;
            case R.id.viewTarget:
                Glide.with(this).load(IMG_URL).into(mViewTargetImage.getTarget());
                break;
        }
    }
}
