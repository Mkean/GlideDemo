package com.example.glidedemo.custom;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * 作者：王庆
 * 时间：2018/2/4
 */
/*
* DownloadOnly(Y target)：
*   实现顶层的Target接口，重写方法，
*   注意：这里的泛型类型必须为File对象，这个downloadOnly(Y target)方法要求的。
*
*   因为实现的是顶层的Target接口，所以重写的方法有很多，不过这些方法大多数都是Glide加载图片的生命周期，
*   我们可以不用管，其中只有两个方法是必须实现的，一个是getSize（）方法，一个是onResourceReady（）方法。
*
*   因为Glide在开始加载图片之前是先计算出图片的大小，然后回调到onSizeReady()方法当中，之后才去下载图片的；
*   所以这里的图片大小就得交给我们了，这里就指定了Target.SIZE_ORIGINAL，表示原始尺寸。
*
*   图片下载完成之后就会调用onResourceReady()方法了。
*
* */
public class DownloadImageTarget implements Target<File> {
    private filePathListener mListener;

    public void setFilePathListener(filePathListener mListener) {
        this.mListener = mListener;
    }

    public interface filePathListener {
        void getFilePath(String filePath);
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {

    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {

    }

    @Override
    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
        mListener.getFilePath(resource.getPath());
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {

    }

    @Override
    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    @Override
    public void setRequest(Request request) {

    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
