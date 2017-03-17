package com.zdh.alphathink.imh.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DrawableUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zdh.alphathink.imh.Bean.ACache;
import com.zdh.alphathink.imh.Personal.Personal_about_us;
import com.zdh.alphathink.imh.Personal.Personal_count;
import com.zdh.alphathink.imh.Personal.Personal_personal_info;
import com.zdh.alphathink.imh.Personal.Personal_thought;
import com.zdh.alphathink.imh.Personal.UserDao.LoginActivity;
import com.zdh.alphathink.imh.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Personal extends Fragment {
    private View view;
    private LinearLayout about_us,my_count,my_view,my_version,personal_title;
    private Intent intent = new Intent();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ACache aCache;
    private TextView login_count;
    private ImageView login_head;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal, container, false);

        login_head = (ImageView) view.findViewById(R.id.alphathink);
        login_count = (TextView) view.findViewById(R.id.login_count);
        about_us = (LinearLayout) view.findViewById(R.id.about_us);
        my_count = (LinearLayout) view.findViewById(R.id.my_count);
        my_version = (LinearLayout) view.findViewById(R.id.my_version);
        my_view = (LinearLayout) view.findViewById(R.id.my_view);
        personal_title = (LinearLayout) view.findViewById(R.id.personal_title);
        initLoginState();
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), Personal_about_us.class);
                startActivity(intent);
            }
        });
        my_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        my_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), Personal_thought.class);
                startActivity(intent);
            }
        });
        personal_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), Personal_personal_info.class);
                startActivity(intent);
            }
        });

        initLoginState();
        return  view;
    }

    public void initLoginState(){
        //判断是否存储有账号信息
        aCache = ACache.get(getActivity());
        if (null != aCache.getAsString("AutoLogin")&&aCache.getAsString("AutoLogin").equals("true")){
            //有登陆数据

            if (null != aCache.getAsDrawable("head")){
                login_head.setBackground(aCache.getAsDrawable("head"));
            }
            if (null != aCache.getAsString("username")) {
                login_count.setText(aCache.getAsString("username"));
            }
            my_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(getActivity(), Personal_count.class);
                    startActivity(intent);
                }
            });
        }else{
            my_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

}
