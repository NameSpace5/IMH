package com.zdh.alphathink.imh.fragment;

import android.support.v4.app.FragmentManager;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.zdh.alphathink.imh.News.Fragment_news_first;
import com.zdh.alphathink.imh.News.Fragment_news_second;
import com.zdh.alphathink.imh.News.Fragment_news_third;
import com.zdh.alphathink.imh.R;
import java.util.ArrayList;
import java.util.List;


public class Healthy_News extends Fragment {

    private ViewPager viewPager;//页卡内容
    private ImageView imageView;// 动画图片
    private TextView textView1, textView2, textView3;
    private ArrayList<Fragment> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_news, container, false);
        InitImageView();
        InitTextView();
        InitViewPager();
        return view;
    }

    private void InitViewPager() {
        viewPager = (ViewPager) view.findViewById(R.id.vPager);
        views = new ArrayList<Fragment>();
        Fragment_news_first fragment_news_first = new Fragment_news_first();
        Fragment_news_second fragment_news_second= new Fragment_news_second();
        Fragment_news_third fragment_news_third = new Fragment_news_third();
        views.add(fragment_news_first);
        views.add(fragment_news_second);
        views.add(fragment_news_third);
        viewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager(),views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化头标
     */

    private void InitTextView() {
        textView1 = (TextView) view.findViewById(R.id.text1);
        textView2 = (TextView) view.findViewById(R.id.text2);
        textView3 = (TextView) view.findViewById(R.id.text3);

        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 2      * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     * 3
     */

    private void InitImageView() {
        imageView = (ImageView) view.findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }

    }


//    public String request(String httpUrl, String httpArg) {
//        BufferedReader reader = null;
//        String result = null;
//        StringBuffer sbf = new StringBuffer();
//        httpUrl = httpUrl + "?" + httpArg;
//        try {
//            URL url = new URL(httpUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("apikey", "3ace713028f4abe9de414dc98ade98bf");
//            connection.connect();
//            InputStream is = connection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            String strRead = null;
//            while ((strRead = reader.readLine()) != null) {
//                sbf.append(strRead);
//                sbf.append("\r\n");
//            }
//            reader.close();
//            result = sbf.toString();
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    private void initAcache(){
//        aCache = ACache.get(getActivity());//默认选择的路径是new File(context.getCacheDir(),// "ACache")
//
//    }


    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mListViews;

        public MyViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> lists){
            super(fm);
            this.mListViews = lists;
        }


        @Override
        public Fragment getItem(int position) {
            return mListViews.get(position);
        }
        @Override
        public int getCount() {
            return mListViews.size();
        }
    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量
        public void onPageScrollStateChanged(int arg0) {
        }
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        public void onPageSelected(int arg0) {
            /*两种方法，这个是一种，下面还有一种，显然这个比较麻烦
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
            }
            */
            Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);//显然这个比较简洁，只有一行代码。
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);
        }

    }
}




