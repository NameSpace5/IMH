package com.zdh.alphathink.imh.Personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zdh.alphathink.imh.Personal.UserDao.LoginActivity;
import com.zdh.alphathink.imh.R;

public class Personal_personal_info extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout header,username,phoneNumber,address,signature,nickname;
    private TextView my_username,my_signature,my_address,my_phoneNumber,my_nickname;
    private FrameLayout back_Personal_info;
    private ImageView my_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_personal_info);
        header = (RelativeLayout) findViewById(R.id.head_personal_info);
        username = (RelativeLayout) findViewById(R.id.username_personal_info);
        phoneNumber = (RelativeLayout) findViewById(R.id.phoneNumber_personal_info);
        address = (RelativeLayout) findViewById(R.id.address_personal_info);
        signature = (RelativeLayout) findViewById(R.id.signature_personal_info);
        nickname = (RelativeLayout) findViewById(R.id.nickname_personal_info);
        my_header = (ImageView) findViewById(R.id.my_head_personal_info);
        back_Personal_info = (FrameLayout) findViewById(R.id.imgBtn_back_personal_info);
        my_username = (TextView) findViewById(R.id.my_username_personal_info);
        my_signature = (TextView) findViewById(R.id.my_signature_personal_info);
        my_address = (TextView) findViewById(R.id.my_address_personal_info);
        my_phoneNumber = (TextView) findViewById(R.id.my_phoneNumber_personal_info);
        my_nickname = (TextView) findViewById(R.id.my_nickname_personal_info);
        header.setOnClickListener(this);
        username.setOnClickListener(this);
        phoneNumber.setOnClickListener(this);
        address.setOnClickListener(this);
        signature.setOnClickListener(this);
        nickname.setOnClickListener(this);
        back_Personal_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_personal_info:

        }
    }

    public void on(View v){
        startActivity(new Intent(Personal_personal_info.this, LoginActivity.class));

    }
}
