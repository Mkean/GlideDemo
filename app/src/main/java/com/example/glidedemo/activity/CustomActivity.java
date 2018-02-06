package com.example.glidedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.glidedemo.R;

/*
* Glide为我们提供了很简单的API，但是如果我们想要更改Glide的某些默认配置项又改怎么操作呢？
* 这就需要我们用到自定义模块了。
*
*自定义模块功能可以将更改Glide配置，替换Glide组件等操作独立出来，使得我们能轻松地对Glide
* 的各种配置进行自定义，并且又和Glide的图片加载逻辑没有任何交集，这也是一种低耦合编程方式的体现。
*
* 首先我们就需要一个我们自定义的类，并让它实现GlideModule接口
* */
public class CustomActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String IMG_URL = "http://ww2.sinaimg.cn/large/610dc034gw1f9kjnm8uo1j20u00u0q5q.jpg";
    public static final String IMG = "http://ww3.sinaimg.cn/large/610dc034jw1f8qd9a4fx7j20u011hq78.jpg";

    private ImageView mCacheDiskCacheFactoryImage;
    private Button mCacheDiskCache;
    private Button mOkHttp;
    private ImageView mOkHttpImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        initView();
        initData();
        setListener();
    }

    private void setListener() {
        mCacheDiskCache.setOnClickListener(this);
        mOkHttp.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        mCacheDiskCache = (Button) findViewById(R.id.CacheDiskCache);
        mOkHttp = (Button) findViewById(R.id.OkHttp);
        mCacheDiskCacheFactoryImage = (ImageView) findViewById(R.id.CacheDiskCacheFactory_image);
        mOkHttpImage = (ImageView) findViewById(R.id.OkHttp_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //更改硬盘缓存策略及缓存大小
            case R.id.CacheDiskCache:
                Glide.with(this)
                        .load(IMG_URL)
                        .into(mCacheDiskCacheFactoryImage);
                break;
            case R.id.OkHttp:
                Glide.with(this)
                        .load(IMG)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(mOkHttpImage);
                break;

        }
    }
}
