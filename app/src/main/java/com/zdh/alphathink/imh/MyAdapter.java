package com.zdh.alphathink.imh;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Panda on 2/16.
 */
public class MyAdapter extends PagerAdapter {

    //界面列表
    private List<View> views;

    public MyAdapter(List<View> views){
        this.views = views;
    }
    //销毁位置界面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(views.get(position));
    }
    //获得当前界面数
    @Override
    public int getCount() {
        if (views!=null){
            return  views.size();
        }
        return 0;
    }
    //初始化界面


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
