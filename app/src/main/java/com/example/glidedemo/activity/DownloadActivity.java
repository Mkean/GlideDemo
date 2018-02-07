package com.example.glidedemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.example.glidedemo.R;
import com.example.glidedemo.custom.DownloadImageTarget;

import java.io.File;
import java.util.concurrent.ExecutionException;

/*
* 讲述获取缓存图片的路径。
*downloadOnly()方法，可以替换into()方法的，
* 不过downloadOnly()方法表示只会下载图片，但不会对图片进行加载。
*
*
* downloadOnly(int width, int height)：接收图片的宽度
*    用于在子线程中下载图片,downloadOnly(int width, int height)因为受RequestFutureTarget限制，必须在子线程运行。
*    调用了downloadOnly(int width, int height)方法或会立即返回一个FutureTarget对象，然后Glide会在后台开始下载图片文件。
*    接下来我们FutureTarget的get（）方法就可以获取到下载好的图片路径了。
*
*downloadOnly(Y target)：接收一个泛型对象
*    用于在主线程中下载图片
*   downloadOnly(Y target)的用法比较复杂一些，因为需要我们自己创建一个Target，不受RequestFutureTarget限制，而且必须实现顶层的Target接口，
*   所以可以直接执行在主线程了。
* */

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String url = "https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg";
    public static final String IMG_URL = "https://ws1.sinaimg.cn/large/610dc034ly1fitcjyruajj20u011h412.jpg";
    private ImageView mDownloadTargetImage;
    private ImageView mDownloadSizeImage;
    private Button mDownloadTarget;
    private Button mDownloadSize;
    private TextView mDownloadTargetText;
    private TextView mDownloadSizeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        initData();
        setListener();
    }

    private void setListener() {
        mDownloadSize.setOnClickListener(this);
        mDownloadTarget.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        mDownloadSize = (Button) findViewById(R.id.downloadSize);
        mDownloadTarget = (Button) findViewById(R.id.downloadTarget);
        mDownloadSizeImage = (ImageView) findViewById(R.id.downloadSize_image);
        mDownloadTargetImage = (ImageView) findViewById(R.id.downloadTarget_image);
        mDownloadSizeText = (TextView) findViewById(R.id.downloadSize_text);
        mDownloadTargetText = (TextView) findViewById(R.id.downloadTarget_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.downloadSize:
                //downloadOnly(int width, int height)方法必须要用在子线程当中，因此这里的第一步就是new了一个Thread
                //注意的是，这里的Context不能在传当前Activity的Context了，需要传入Application的Context，
                //因为会有activity销毁而子线程还没有执行完的可能出现。
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Context context = getApplicationContext();
                            FutureTarget<File> target = Glide.with(context)
                                    .load(url)
                                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                            //调用get（）方法获取路径
                            final File file = target.get();
                            //切换为主线程，打印路径
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mDownloadSizeText.setText(file.getPath());
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                //注意这里必须将硬盘缓存策略指定成DiskCacheStrategy.SOURCE或者DiskCacheStrategy.ALL，
                // 否则Glide将无法使用我们刚才下载好的图片缓存文件
                Glide.with(this)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(mDownloadSizeImage);
                break;
            case R.id.downloadTarget:
                DownloadImageTarget target = new DownloadImageTarget();
                Glide.with(this)
                        .load(IMG_URL)
                        .downloadOnly(target);

                target.setFilePathListener(new DownloadImageTarget.filePathListener() {
                    @Override
                    public void getFilePath(String filePath) {
                        mDownloadTargetText.setText(filePath);
                    }
                });
                Glide.with(this)
                        .load(IMG_URL)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(mDownloadTargetImage);
                break;

}
    }
}
