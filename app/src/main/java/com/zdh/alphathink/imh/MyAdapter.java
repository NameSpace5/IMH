package com.zdh.alphathink.imh;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
/**
 * Created by Panda on 2/16.
 */
public class MyAdapter extends PagerAdapter {
    //界面列表
    private ArrayList<View> views;

    public MyAdapter(ArrayList<View> views){
        this.views = views;
    }
    //获得当前界面数
    @Override
    public int getCount() {
        if (views!=null){
            return  views.size();
        }
        else return 0;
    }
    //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //初始化position位置界面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position),0);
        return views.get(position);
    }
    //销毁position位置界面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}