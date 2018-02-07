package com.example.glidedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.glidedemo.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class BasisActivity extends AppCompatActivity {
    public static final String IMG_URL = "https://ws1.sinaimg.cn/large/610dc034ly1fitcjyruajj20u011h412.jpg";
    public static final String ERROR_URL = "https://ws1.sinaimg.cn/large412.jpg";
    public static final String GIF_URL = "http://img03.sogoucdn.com/app/a/100520093/2c277ff7b7710556-7fec21cbadfcef1a-a541067836bacdabb538be5b07c71da6.jpg";

    private ImageView mBasis;
    private ImageView mPlaceView;
    private ImageView mFailView;
    private ImageView mGifView;
    private ImageView mAsGifView;
    private ImageView mCircleView;
    private ImageView mBlurView;
    private ImageView mBlurCircleView;
    private ImageView mCircleRoundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basis);
        initView();
        initData();
    }

    private void initData() {
        //基础用法
        Glide.with(this)
                .load(IMG_URL)
                .crossFade(3000)//淡入效果
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mBasis);

        //占位图
        Glide.with(this)
                .load(IMG_URL)
                .placeholder(R.drawable.icon_placeholder)
                .skipMemoryCache(true)//内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)//硬盘缓存，不缓存任何功能
                .into(mPlaceView);

        //失败图
        Glide.with(this)
                .load(ERROR_URL)
                .placeholder(R.drawable.icon_placeholder)
                .error(R.drawable.icon_failure)
                .into(mFailView);

        //Gif图
        Glide.with(this)
                .load(GIF_URL)
                .asGif()//设置GIF图，
                .into(mGifView);

        //Gif图第一帧
        Glide.with(this)
                .load(GIF_URL)
                .asBitmap()//获取GIF图的第一帧，强制指定为静态图
                .into(mAsGifView);

        //圆形图片
        Glide.with(this)
                .load(IMG_URL)
                .crossFade(1000)//淡入效果
                .bitmapTransform(new CropCircleTransformation(this))
                .into(mCircleView);

        //模糊效果
        Glide.with(this)
                .load(IMG_URL)
                .bitmapTransform(new BlurTransformation(this, 25))
                .into(mBlurView);

        //模糊效果+圆形
        Glide.with(this)
                .load(IMG_URL)
                .bitmapTransform(new BlurTransformation(this, 25), new CropCircleTransformation(this))
                .into(mBlurCircleView);

        //圆角
        Glide.with(this)
                .load(IMG_URL)
                .bitmapTransform(new RoundedCornersTransformation(this, 70, 0, RoundedCornersTransformation.CornerType.BOTTOM))
                .into(mCircleRoundView);

    }

    private void initView() {
        mBasis = (ImageView) findViewById(R.id.basis);
        mPlaceView = (ImageView) findViewById(R.id.placeView);
        mFailView = (ImageView) findViewById(R.id.failView);
        mGifView = (ImageView) findViewById(R.id.GifView);
        mAsGifView = (ImageView) findViewById(R.id.AsGifView);
        mCircleView = (ImageView) findViewById(R.id.circleView);
        mBlurView = (ImageView) findViewById(R.id.blurView);
        mBlurCircleView = (ImageView) findViewById(R.id.blurCircleView);
        mCircleRoundView = (ImageView) findViewById(R.id.circleRoundView);
    }
}

