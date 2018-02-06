package com.example.glidedemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：王庆
 * 时间：2018/2/5
 */

public class MyViewPagerAdapter extends PagerAdapter {

    //这其实是我们的photoView的集合
    private List<View> mImgs;

    public MyViewPagerAdapter(List<View> imgs) {
        this.mImgs = imgs;
    }

    @Override
    public int getCount() {
        return mImgs == null ? 0 : mImgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImgs.get(position));
        return mImgs.get(position);
    }
}
