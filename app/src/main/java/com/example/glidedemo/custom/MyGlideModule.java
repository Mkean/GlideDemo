package com.example.glidedemo.custom;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.example.glidedemo.utils.OkHttpUtils;

import java.io.InputStream;

/**
 * 作者：王庆
 * 时间：2018/2/5
 */
/*
* 自定义一个类实现GlideModule接口，重写applyOptions（）方法和registerComponents（）方法，
* 这两个方法就是分别用来更改Glide配置以及替换Glide组件的。
*
* 不过在我们我们生成自定义模块时，需要在AndroidManifest.xml文件当中加入如下配置方可：
*       <meta-data
            android:name="包名.类名"
            android:value="GlideModule" //这是个固定值
            />
*
* */

public class MyGlideModule implements GlideModule {
    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;
    public static final int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存


    /*
    * 更改Glide配置
    *setMemoryCache()：用于配置Glide的内存缓存策略，默认配置是LruResourceCache。
    *setBitmapPool()： 用于配置Glide的Bitmap缓存池，默认配置是LruBitmapPool。
    *setDiskCache()：用于配置Glide的硬盘缓存策略，默认配置是InternalCacheDiskCacheFactory。
    *setDiskCacheService()：用于配置Glide读取缓存中图片的异步执行器，默认配置是FifoPriorityThreadPoolExecutor，也就是先入先出原则
    * setResizeService()：用于配置Glide读取非缓存中图片的异步执行器，默认配置也是FifoPriorityThreadPoolExecutor。
    * setDecodeFormat()：用于配置Glide加载图片的解码模式，默认配置是RGB_565。
    * */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));//更改硬盘缓存策略及缓存大小

        // 定义图片格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);//设置加载图片的格式
//        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565); // 默认


        // 默认内存和图片池大小
//         MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//         int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); // 默认内存大小
//         int defaultBitmapPoolSize = calculator.getBitmapPoolSize(); // 默认图片池大小
//         builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize));
//         builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));

//        builder.setMemoryCache(new LruResourceCache(memorySize)); // 自定义内存大小
//         builder.setBitmapPool(new LruBitmapPool(memorySize)); // 自定义图片池大小
    }

    /*
    *
    * 替换Glide组件
    * Glide中的组件非常繁多，也非常复杂，但其实大多数情况下并不需要我们去做什么替换。
    * 不过，有一个组件却有着比较大的替换需求，那就是Glide的HTTP通讯组件。
    *
    * 默认情况下，Glide使用的是基于原生HttpURLConnection进行订制的HTTP通讯组件，
    * 但是现在大多数的Android开发者都更喜欢使用OkHttp，因此将Glide中的HTTP通讯组件修改成OkHttp的这个需求比较常见
    *
    * register()方法中传入的参数表示Glide支持使用哪种参数类型来加载图片。
    *
    * HttpUrlGlideUrlLoader.Factory则是要负责处理具体的网络通讯逻辑。如果我们想要将Glide的HTTP通讯组件替换成OkHttp的话，
    * 那么只需要在自定义模块当中重新注册一个GlideUrl类型的组件就行了
    *
    * */
    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(OkHttpUtils.getInstance()));
    }
}
