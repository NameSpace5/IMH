package com.zdh.alphathink.imh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;

import static com.xiaomi.push.thrift.a.R;

public class WelcomeActivity extends AppCompatActivity implements OnClickListener,OnPageChangeListener {
    private ViewPager mViewPager;
    private MyAdapter mAdapter;
    //底部小圆点图片
    private  ImageView[] points;
    //当前选中位置
    private int currentIndex;
    //定义arraylist存放view
    private ArrayList<View> views;
    private Button button;

    private static final int[] mImgIds = new int[]{R.drawable.guide_image1,
                                          R.drawable.guide_image2,
                                          R.drawable.guide_image3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        initData();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(WelcomeActivity.this,Index_Main.class);
                startActivity(go);
                WelcomeActivity.this.finish();
            }
        });
    }
    //初始化组件
    private void initView(){
        //实例化List对象
        views= new ArrayList<View>();
        //实例化ViewPager
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //实例化ViewPager适配器
        mAdapter = new MyAdapter(views);
    }
    //初始化数据
    private void initData(){
        //定义一个布局并设置参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //初始化图片列表
        for (int i = 0;i<mImgIds.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            //防止图片不全屏
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //加载图片资源
            imageView.setImageResource(mImgIds[i]);
            views.add(imageView);
        }
        //设置数据
        mViewPager.setAdapter(mAdapter);
        //设置监听
        mViewPager.setOnPageChangeListener(this);
        //初始化底部小点
        initPoint();
    }
    //初始化底部小点
    private void initPoint(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        points = new ImageView[mImgIds.length];
        //循环取得小点图片
        for (int i = 0; i<mImgIds.length;i++){
            //得到一个：linearlayout下的每一个子元素
            points[i]= (ImageView) linearLayout.getChildAt(i);
            //默认都设为灰色
            points[i].setEnabled(true);
            //给每个小点设置监听
            points[i].setOnClickListener(this);
            //设置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }
        //设置当前页面位置
        currentIndex = 0;
        //设置为白色，选中状态
        points[currentIndex].setEnabled(false);
    }
//点击调用
    @Override
    public void onClick(View v) {
      int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }
    private void setCurView(int position){
        if (position<0 ||position>=mImgIds.length){
            return;
        }
        mViewPager.setCurrentItem(position);
    }
    private void setCurDot(int position){
        if (position<0||position>mImgIds.length-1||currentIndex == position){
            return;
    }
    points[position].setEnabled(false);
    points[currentIndex].setEnabled(true);
    currentIndex = position;
    }
    //页面滑动调用
    @Override
    public void onPageScrolled(int arg0, float agr1, int arg2) {

    }
    //新页面被选中调用
    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
        if (position == 2){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.GONE);
        }
    }


//滑动状态改变调用
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

}
