package com.zdh.alphathink.imh;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zdh.alphathink.imh.fragment.Doctor_consult;
import com.zdh.alphathink.imh.fragment.Healthy_News;
import com.zdh.alphathink.imh.fragment.NoWifiConnected;
import com.zdh.alphathink.imh.fragment.Personal;

import cn.bmob.v3.Bmob;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;



public class  Index_Main extends FragmentActivity implements View.OnClickListener{
    private LinearLayout healthy_news;
    private LinearLayout medical_control;
    private LinearLayout doctor_consult;
    private LinearLayout personal;

    private Healthy_News fragment_healthy_news;
    private Doctor_consult fragment_doctor_consult;
    private Personal fragment_personal;
    private NoWifiConnected noWifiConnected;

    private ImageButton ibtn_healthy;
    private ImageButton ibtn_medical;
    private ImageButton ibtn_doctor;
    private ImageButton ibtn_personal;

    private TextView tv_healthy;
    private TextView tv_medical;
    private TextView tv_doctor;
    private TextView tv_personal;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index__main);

        //Bmob初始化
        Bmob.initialize(this,"43dbe9d5e9502bed6fd2542b5adc1a33");

        //融云初始化test
        RongIM.init(this);
        connect("fW/KPi4BtqOCLh/3kmDtiqvbdeLwrclUaqdoEmkWHuc5sFerI17jEu7q6hYz4d7hxBZgSLrKt+YwKf+iP21+nPrWKS4cSDgI");


        //初始化控件
        healthy_news = (LinearLayout) findViewById(R.id.index_healthy_news);
        medical_control = (LinearLayout) findViewById(R.id.index_medical_control);
        doctor_consult = (LinearLayout) findViewById(R.id.index_doctor_consult);
        personal = (LinearLayout) findViewById(R.id.index_personal);

        ibtn_doctor = (ImageButton) findViewById(R.id.img_doctor);
        ibtn_healthy = (ImageButton) findViewById(R.id.img_healthy);
        ibtn_medical = (ImageButton) findViewById(R.id.img_medical);
        ibtn_personal = (ImageButton) findViewById(R.id.img_personal);

        tv_doctor = (TextView) findViewById(R.id.text_doctor);
        tv_healthy = (TextView) findViewById(R.id.text_healthy);
        tv_medical = (TextView) findViewById(R.id.text_medical);
        tv_personal = (TextView) findViewById(R.id.text_personal);
        tv_title = (TextView) findViewById(R.id.index_title);


        healthy_news.setOnClickListener(this);
        medical_control.setOnClickListener(this);
        doctor_consult.setOnClickListener(this);
        personal.setOnClickListener(this);


        //s设置默认的fragment
        String i = getIntent().getStringExtra("flag");
        if (null == i){
            setDefaultFragment(1);
        }else if ("3".equals(i)){
            setDefaultFragment(3);
        }

    }

    private void setDefaultFragment(int i){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(i) {
            case 1:
                fragment_healthy_news = new Healthy_News();
                fragmentTransaction.replace(R.id.index_content, fragment_healthy_news);
                fragmentTransaction.commit();
                ibtn_healthy.setImageResource(R.drawable.index_healthy_news_pressed);
                tv_healthy.setTextColor(getResources().getColor(R.color.index_text_checked));
                tv_title.setText("健康资讯");
                break;
            case 3:
                fragment_personal = new Personal();
                fragmentTransaction.replace(R.id.index_content, fragment_personal);
                fragmentTransaction.commit();
                ibtn_personal.setImageResource(R.drawable.index_personal_pressed);
                tv_personal.setTextColor(getResources().getColor(R.color.index_text_checked));
                tv_title.setText("个人中心");
                break;
        }
    }




    @Override
    public void onClick(View v) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(v.getId()){
            case R.id.index_healthy_news:
                initBottomStyle();
                ibtn_healthy.setImageResource( R.drawable.index_healthy_news_pressed);
                tv_healthy.setTextColor(getResources().getColor(R.color.index_text_checked));
                tv_title.setText("健康资讯");
                if (fragment_healthy_news == null){
                    fragment_healthy_news = new Healthy_News();
                }
                transaction.replace(R.id.index_content,fragment_healthy_news);
                break;
            case R.id.index_medical_control:
                initBottomStyle();
                ibtn_medical.setImageResource(R.drawable.index_medical_box_pressed);
                tv_medical.setTextColor(getResources().getColor(R.color.index_text_checked));
                tv_title.setText("药箱管控");
                    if (noWifiConnected == null){
                        noWifiConnected= new NoWifiConnected();
                    }
                    transaction.replace(R.id.index_content,noWifiConnected);
                    break;
            case R.id.index_doctor_consult:
                initBottomStyle();
                ibtn_doctor.setImageResource(R.drawable.index_doctor_consult_pressed);
                tv_doctor.setTextColor(getResources().getColor(R.color.index_text_checked));
                tv_title.setText("医生咨询");
            if (fragment_doctor_consult == null){
                fragment_doctor_consult = new Doctor_consult();
            }
            transaction.replace(R.id.index_content,fragment_doctor_consult);
            break;
            case R.id.index_personal:
                initBottomStyle();
                ibtn_personal.setImageResource(R.drawable.index_personal_pressed);
                tv_personal.setTextColor(getResources().getColor(R.color.index_text_checked));
                tv_title.setText("个人中心");
                if (fragment_personal == null){
                    fragment_personal = new Personal();
                }
                transaction.replace(R.id.index_content,fragment_personal);
                break;
        }
        transaction.commit();
    }
    public void initBottomStyle(){
        ibtn_healthy.setImageResource(R.drawable.index_healthy_news_normal);
        ibtn_doctor.setImageResource(R.drawable.index_doctor_consult_normal);
        ibtn_medical.setImageResource(R.drawable.index_medical_box_normal);
        ibtn_personal.setImageResource(R.drawable.index_personal_normal);
        tv_personal.setTextColor(getResources().getColor(R.color.index_text_normal));
        tv_medical.setTextColor(getResources().getColor(R.color.index_text_normal));
        tv_healthy.setTextColor(getResources().getColor(R.color.index_text_normal));
        tv_doctor.setTextColor(getResources().getColor(R.color.index_text_normal));
    }


    private void connect(String token) {

        if (1==1) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }
}
