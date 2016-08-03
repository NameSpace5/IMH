package com.zdh.alphathink.imh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //判断程序首次安装，是进入引导页，否则进入主界面
        preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
        if (preferences.getBoolean("firststart",true)){
            editor = preferences.edit();
            editor.commit();
            editor.putBoolean("firststart",false);
            editor.commit();
            Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MainActivity.this,Index.class);
            startActivity(intent);
            finish();
        }
    }
    //判断是否初次加载，读取SharedPreference中guide_activity字段

}
