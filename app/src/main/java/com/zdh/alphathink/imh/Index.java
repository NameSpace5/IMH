package com.zdh.alphathink.imh;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zdh.alphathink.imh.Bean.ACache;
import com.zdh.alphathink.imh.Bean.LineGraphicView;
import com.zdh.alphathink.imh.Control.MyDialog;
import com.zdh.alphathink.imh.Net.ConnectionChangeReceiver;
import com.zdh.alphathink.imh.Chatting.ChattingActivity;
import com.zdh.alphathink.imh.Control.ControlActivity;
import com.zdh.alphathink.imh.News.NewsActivity;
import com.zdh.alphathink.imh.Personal.PersonalActivity;
import com.zdh.alphathink.imh.User.LoginActivity;

import java.util.ArrayList;

import static android.R.attr.id;
import static com.zdh.alphathink.imh.R.id.login_auto;
import static com.zdh.alphathink.imh.R.id.psw_rem;

public class Index extends AppCompatActivity implements View.OnClickListener{
    private ImageButton button_a,button_b, button_c, button_d;
    private ConnectionChangeReceiver myReceiver;
    private SharedPreferences preference_user;
    private IntentFilter intentFilter;
    private ConnectionChangeReceiver receiver;
    private LineGraphicView tu;
    private ArrayList<Double> yList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        button_a = (ImageButton) findViewById(R.id.button_a);
        button_b = (ImageButton) findViewById(R.id.button_b);
        button_c = (ImageButton) findViewById(R.id.button_c);
        button_d = (ImageButton) findViewById(R.id.button_d);
        tu = (LineGraphicView) findViewById(R.id.line_graphic);
        button_a.setOnClickListener(this);
        button_b.setOnClickListener(this);
        button_c.setOnClickListener(this);
        button_d.setOnClickListener(this);
        initSystemBar(this);
        initState_login();

        //注册receiver
        intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new ConnectionChangeReceiver();
        this.registerReceiver(receiver, intentFilter);


//        if (!CheckNetworkState.isWifi(this)){
//            WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//            String wifi_name = wifiInfo.getSSID();
//            wifi_name = wifi_name.substring(1, 4);
////            Toast.makeText(this,wifi_name,Toast.LENGTH_SHORT).show();
//            if (wifi_name.equals("pan")) {
//                //全部链接正确，可进行相关设置
//            } else {
//
//            }
//        }



        //曲线图绘制
        yList = new ArrayList<Double>();
        yList.add(2.103);
        yList.add(4.07);
        yList.add(1.07);
        yList.add(2.07);
        yList.add(3.07);
        yList.add(6.09);
        yList.add(5.07);

        ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("05-19");
        xRawDatas.add("05-20");
        xRawDatas.add("05-21");
        xRawDatas.add("05-22");
        xRawDatas.add("05-23");
        xRawDatas.add("05-24");
        xRawDatas.add("05-28");
        xRawDatas.add("05-29");
        tu.setData(yList, xRawDatas, 8, 2);

    }



    public void initState_login(){
        preference_user = this.getSharedPreferences("login_state",MODE_PRIVATE);
        if(checkNetworkConnection(Index.this)){
            Toast.makeText(Index.this,"当前网络可用",Toast.LENGTH_SHORT).show();
                //判断自动登录状态
                if (preference_user.getBoolean("AUTO_ISCHECK",true)){
                    //login

                }
        }else{
            Toast.makeText(Index.this,"当前网络不可用",Toast.LENGTH_SHORT).show();
        }
    }
    //ActionBar

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //item点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getGroupId()){
            case R.id.a:

                break;
            case R.id.b:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_a:
                startActivity(new Intent(this, NewsActivity.class));
                break;
            case R.id.button_b:
                startActivity(new Intent(this, ChattingActivity.class));
                break;
            case R.id.button_c:
                startActivity(new Intent(this, ControlActivity.class));
                break;
            case R.id.button_d:
                startActivity(new Intent(this, PersonalActivity.class));
                break;
        }
    }
    //设置状态栏颜色与标题栏一致
    public static void initSystemBar(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            setTranslucentStatus(activity, true);

        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);

        tintManager.setStatusBarTintEnabled(true);

// 使用颜色资源

        tintManager.setStatusBarTintResource(R.drawable.bar);

    }
    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);
    }



    public static boolean checkNetworkConnection(Activity activity){
        Context context = activity.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null){
            return false;
        }else{
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null&&networkInfo.length>0){
                for (int i = 0;i<networkInfo.length;i++){
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void share(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.tencent.mobileqq");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "地瓜地瓜，我是土豆！ ");
        intent.putExtra(Intent.EXTRA_TITLE, "我是标题");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "选一个吧、"));
    }

    //广播内部类
    class ConnectionChangeReceiver extends BroadcastReceiver {
        private LinearLayout linearLayout;

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            linearLayout = (LinearLayout) findViewById(R.id.waring);
            if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                Toast.makeText(context, "网络不可以用", Toast.LENGTH_SHORT).show();
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                });
            } else {
                linearLayout.setVisibility(View.GONE);
            }
        }
    }

}
