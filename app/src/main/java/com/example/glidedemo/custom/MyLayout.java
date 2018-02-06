package com.example.glidedemo.custom;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * 作者：王庆
 * 时间：2018/2/5
 */
/*
* ViewTarget的功能很广泛，可以作用于任何View上。
* 下面就是我们自定义的一个View了。
* */
public class MyLayout extends LinearLayout {

    private ViewTarget<MyLayout, GlideDrawable> viewTarget;

    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化
        init();
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        init();
    }

    private void init() {
        /*
        * 创建一个ViewTarget实例，并将当前类的实例this传进去，
        * 需要指定两个泛型，一个是View类型，一个是图片类型（GlideDrawable或Bitmap）；
        *然后在重写的onResourceReady（）方法中，可以通过getView（）方法获取到当前类的实例，
        * 然后我们就可以调用它的任意接口了。
        * */
        viewTarget = new ViewTarget<MyLayout, GlideDrawable>(this) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                MyLayout view = getView();
                view.setImageBackground(resource);
            }
        };
    }

    //返回Target实例
    public ViewTarget<MyLayout, GlideDrawable> getTarget() {
        return viewTarget;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setImageBackground(GlideDrawable resource) {
        setBackground(resource);
    }
}
