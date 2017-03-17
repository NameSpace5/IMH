package com.zdh.alphathink.imh.Control.Box;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.zdh.alphathink.imh.R;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static android.content.Context.WIFI_SERVICE;

public class Box extends AppCompatActivity implements View.OnClickListener {
    private Socket socket = null;
    private WifiManager wifiManager;
    private Button connect_wifi;
    private Intent intent;
    private FrameLayout back;
    private FrameLayout box_background_nowifi;
    private WifiInfo wifiInfo;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:

        }
    }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        box_background_nowifi = (FrameLayout) findViewById(R.id.box_background_nowifi);
        connect_wifi = (Button) findViewById(R.id.box_btn_connect_wifi);
        connect_wifi.setOnClickListener(this);
        back = (FrameLayout) findViewById(R.id.back);
        back.setOnClickListener(this);


        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();

        initConnect();


    }

    //click事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.box_btn_connect_wifi:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

        }
    }

    //链接TCP套接字相关配置初始化
    public void initConnect() {
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            connect_wifi.setVisibility(View.GONE);
            wifiInfo = wifiManager.getConnectionInfo();
            //根据wifi ssid初步判断是否为产品wifi，是则进行套接字操作
            //判断存储，有数据则执行，否则进行存储操作

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        if (socket ==null){
                            socket = new Socket();
                        }
                        //socket按键相关设置
                        if (!socket.isClosed()&&socket.isConnected()){
                            //连接状态
                            box_background_nowifi.setVisibility(View.GONE);
                            initListView();

                        }else{//非连接
                            box_background_nowifi.setVisibility(View.VISIBLE);
                            socket.connect(new InetSocketAddress("192.168.4.1",8888),1*1000);//超时2s
                            initConnect();
                        }

                    }catch (SocketTimeoutException e1){
                        //超时处理
                    }catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            };
            thread.start();
        }

    }

    //初始化ListView数据
    public void initListView(){

    }


}
