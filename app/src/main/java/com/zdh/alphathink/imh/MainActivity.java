package com.zdh.alphathink.imh;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.antfortune.freeline.FreelineCore;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FreelineCore.init(getApplication());
        setContentView(R.layout.activity_main);
        //判断程序首次安装，是进入引导页，否则进入主界面
        preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
        if (preferences.getBoolean("firststart",true)){
            editor = preferences.edit();
            editor.putBoolean("firststart",false);
            editor.commit();
            Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MainActivity.this,Index_Main.class);
            startActivity(intent);
            finish();
        }
        //注册BroadcastReceiver
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
