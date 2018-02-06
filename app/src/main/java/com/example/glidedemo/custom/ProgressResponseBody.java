package com.example.glidedemo.custom;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.glidedemo.utils.LoggingInterceptor;
import com.example.glidedemo.utils.ProgressListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 作者：王庆
 * 时间：2018/2/5
 */
//下载进度的具体计算类
public class ProgressResponseBody extends ResponseBody {
    private static final String TAG = "ProgressResponseBody";

    private BufferedSource bufferedSource;
    private ResponseBody responseBody;
    private ProgressListener listener;

    public ProgressResponseBody(String url, ResponseBody responseBody) {
        this.responseBody = responseBody;
        listener = LoggingInterceptor.LISTENER_MAP.get(url);
    }

    /*
    * 获取内容类型，用于定义网络文件的类型和网页的编码
    * */
    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    /*
    * 内容的长度
    * */
    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }


    /*
    * Okio库是一个由square公司开发的，其官方简介为，Okio complements java.io and java.nio to make it much easier to access, store, and process your data.。
    * 它补充了java.io和java.nio的不足以更方便的访问、存储及处理数据。
    *
    *  Okio库的核心是两个接口Sink和Source;
    *  Sink可以简单的看做OutputStream（将流写入文件），Source可以简单的看做InputStream（从文件读取至流）。
    *  而这两个接口都是支持读写超时设置的。
    *
    *  ，Sink声明了write()、flush()、close()、timeout()等方法，Source中声明了read()、close()、timeout(),
    *  这些方法包含了对文件的读写及资源的释放。它们各自有一个支持缓冲区的子类接口，BufferedSink和BufferedSource，
    *  这两个子接口有一个共同的实现类Buffer,对缓冲区操作
    *
    * */
    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(new ProgressSource(responseBody.source()));
        }
        return bufferedSource;
    }

    /*
    * ForwardingSource也是一个使用委托模式的工具，它不处理任何具体的逻辑，
    * 只是负责将传入的原始Source对象进行中转。
    *
    *
    * */
    private class ProgressSource extends ForwardingSource {
        //一共读取的长度
        long totalBytesRead = 0;

        int currentProgress;

        public ProgressSource(Source delegate) {
            super(delegate);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            //每次读取的长度
            long bytesRead = super.read(sink, byteCount);
            //文件的长度
            long fullLength = responseBody.contentLength();

            if (bytesRead == -1) {
                totalBytesRead = fullLength;
            } else {
                totalBytesRead += bytesRead;
            }
            int progress = (int) (100f * totalBytesRead / fullLength);
            Log.e(TAG, "download progress is " + progress);
            Log.w(TAG, "currentProgress: " + currentProgress);
            //如果progress==currentProgress，则说明读取完成。
            if (listener != null && progress != currentProgress) {
                listener.onProgress(progress);
            }
            if (listener != null && totalBytesRead == fullLength) {
                listener = null;
            }
            currentProgress = progress;

            Log.i(TAG, "bytesRead: " + bytesRead);
            return bytesRead;
        }
    }
}
