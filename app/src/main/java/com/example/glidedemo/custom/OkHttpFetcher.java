package com.example.glidedemo.custom;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者：王庆
 * 时间：2018/2/5
 */

public class OkHttpFetcher implements DataFetcher<InputStream> {
    private OkHttpClient mClient;
    private GlideUrl glideUrl;
    private InputStream stream;
    private ResponseBody responseBody;
    private boolean isCancelled;

    public OkHttpFetcher(OkHttpClient mClient, GlideUrl glideUrl) {
        this.mClient = mClient;
        this.glideUrl = glideUrl;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {

        Request.Builder builder = new Request.Builder().get().url(glideUrl.toStringUrl());
        Request build = builder.build();
        if (isCancelled) {
            return null;
        }
        Response response = mClient.newCall(build).execute();
        responseBody = response.body();
        if (!response.isSuccessful() || responseBody == null) {
            throw new IOException("Request failed with code: " + response.code());
        }
        stream = ContentLengthInputStream.obtain(responseBody.byteStream(),
                responseBody.contentLength());

        return stream;
    }

    @Override
    public void cleanup() {
        try {
            if (stream != null) {
                stream.close();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getId() {
        return glideUrl.getCacheKey();
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }
}
