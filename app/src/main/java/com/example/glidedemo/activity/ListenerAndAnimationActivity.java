package com.example.glidedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.glidedemo.R;

public class ListenerAndAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String IMG_URL = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-12-18380140_455327614813449_854681840315793408_n.jpg";

    private ImageView mListenerImage;
    private ImageView mAnimationImage;
    private Button mAnimation;
    private Button mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_and_animation);
        initView();
        initData();
        setListener();
    }

    private void setListener() {
        mListener.setOnClickListener(this);
        mAnimation.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        mAnimationImage = (ImageView) findViewById(R.id.animation_image);
        mListenerImage = (ImageView) findViewById(R.id.listener_image);
        mListener = (Button) findViewById(R.id.listener);
        mAnimation = (Button) findViewById(R.id.animation);
    }

    /*
    *
    * listener：
    *       监听Glide下载图片的状态。调用listener（）方法，实现RequestListener接口，重写连个方法。
    *          onResourceReady（）成功方法，onException（）失败方法。
    *        listener()方法就这么简单，不过onException（）方法和onResourceReady（）方法都有一个布尔值的返回值，
    *        返回false表示这个事件没有被消费，还会继续向下 传递，返回true，表示这个事件被消费掉，不会再向下传递。
    * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listener:
                Glide.with(this)
                        .load(IMG_URL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                Toast.makeText(ListenerAndAnimationActivity.this, "图片下载错误", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                Toast.makeText(ListenerAndAnimationActivity.this, "图片下载成功", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        })
                        .into(mListenerImage);
                break;
            case R.id.animation:
//                ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
//                    @Override
//                    public void animate(View view) {
//                        view.setAlpha( 0f );
//                        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat( view, "alpha", 0f, 1f );
//                        fadeAnim.setDuration( 2500 );
//                        fadeAnim.start();
//                    }
//                };
//                Glide.with(this)
//                        .load(IMG_URL)
//                        //自定义动画
//                        .animate(animationObject)
//                        .into(mAnimationImage);

                Glide.with(this)
                        .load(IMG_URL)
                        //动画
                        .animate(R.anim.animation)
                        .into(mAnimationImage);
                break;
        }
    }
}
