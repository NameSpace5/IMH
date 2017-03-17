package com.zdh.alphathink.imh.Control.Box;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.zdh.alphathink.imh.Control.MyDialog;
import com.zdh.alphathink.imh.R;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Calendar;

import static com.zdh.alphathink.imh.R.id.dialog;

public class Box extends AppCompatActivity implements View.OnClickListener {
    private Socket socket = null;
    private WifiManager wifiManager;
    private Button connect_wifi;
    private Intent intent;
    private int color;
    private FrameLayout back;
    private FrameLayout add;
    private FrameLayout box_background_nowifi;
    private WifiInfo wifiInfo;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        add = (FrameLayout) findViewById(R.id.box_add);
        box_background_nowifi = (FrameLayout) findViewById(R.id.box_background_nowifi);
        connect_wifi = (Button) findViewById(R.id.box_btn_connect_wifi);
        connect_wifi.setOnClickListener(this);
        back = (FrameLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        add.setOnClickListener(this);


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
                break;
            case R.id.box_add:
                addItem();
                break;

        }
    }

    //链接TCP套接字相关配置初始化
    public void initConnect() {
        add.setVisibility(View.GONE);
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
                        if (socket == null) {
                            socket = new Socket();
                        }
                        //socket按键相关设置
                        if (!socket.isClosed() && socket.isConnected()) {
                            //连接状态
                            box_background_nowifi.setVisibility(View.GONE);
                            add.setVisibility(View.VISIBLE);
                            initListView();

                        } else {//非连接
                            box_background_nowifi.setVisibility(View.VISIBLE);
                            socket.connect(new InetSocketAddress("192.168.4.1", 8888), 1 * 1000);//超时2s
                            initConnect();
                        }

                    } catch (SocketTimeoutException e1) {
                        //超时处理
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            };
            thread.start();
        }

    }

    //初始化ListView数据
    public void initListView() {

    }

    //添加设置信息
    public void addItem() {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_dialog, (ViewGroup) findViewById(dialog));
        final EditText c_id = (EditText) layout.findViewById(R.id.c_id);
        final EditText c_name = (EditText) layout.findViewById(R.id.c_name);
        final RadioGroup c_color = (RadioGroup) layout.findViewById(R.id.c_color);
        final TextView c_time = (TextView) layout.findViewById(R.id.c_time);


        //设置时间选择器
        c_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(Box.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //初始化当前时间
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(System.currentTimeMillis());
                                //根据用户选择的时间设置时间
                                String hour;
                                String min;
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                if (hourOfDay < 10) {
                                    hour = "0" + hourOfDay;
                                } else {
                                    hour = String.valueOf(hourOfDay);
                                }
                                if (minute < 10) {
                                    min = "0" + minute;
                                } else {
                                    min = String.valueOf(minute);
                                }
                                c_time.setText(hour + ":" + min);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        });
        c_color.check(R.id.red);
        c_color.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.red:
                        color = 100;
                        break;
                    case R.id.green:
                        color = 101;
                        break;
                    case R.id.blue:
                        color = 102;
                        break;
                }
            }
        });

        builder.setTitle("添加设置项");
        builder.setContentView(layout);
        builder.setPositiveButton("确定添加",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new SocketWriteThread(socket) {
                            @Override
                            public void run() {
                                DataOutputStream dos = null;
                                BufferedReader br = null;
                                try {
                                    dos = new DataOutputStream(socket.getOutputStream());
                                    int mid = Integer.parseInt(String.valueOf(c_id.getText()));
                                    String mName = (c_name.getText().toString());
                                    String mTime = (c_time.getText().toString());

                                    IMHMsgTextCoder voteMsgTextCoder = new IMHMsgTextCoder();
                                    IMHMsg voteMsg = new IMHMsg(false, 1, mid, color, mName, mTime);
                                    byte[] bytes = new byte[20];
                                    try {
                                        bytes = voteMsgTextCoder.toWire(voteMsg);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    dos.write(bytes);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }

                            }
                        }
                        ).start();

                        dialog.dismiss();
//                        addAdapter.notifyDataSetChanged();
                    }
                });

        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.creat().show();
    }
}
