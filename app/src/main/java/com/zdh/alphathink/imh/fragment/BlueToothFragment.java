package com.zdh.alphathink.imh.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zdh.alphathink.imh.R;
import com.zdh.alphathink.imh.WifiActivity;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Panda on 2016/7/20.
 */
public class BlueToothFragment extends Fragment {
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    private CheckBox checkBox;
    private List<WifiConfiguration>wifiConfigurations;
    private View view;
    private TextView textView;
    private TextView textView2;

    //修改1
    private Button button6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        initView1();
        return view;
    }
    //初始化界面
//    public void initView(){
//        //获得manager对象
//        wifiManager = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
//        //获得链接信息对象
//        wifiInfo = wifiManager.getConnectionInfo();
//        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
//        textView = (TextView) view.findViewById(R.id.textView8);
//        textView2 = (TextView) view.findViewById(R.id.textView9);
//
//        //修改1
//        button6 = (Button) view.findViewById(R.id.button6);
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),WifiActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        //根据wifi状态设置复选框状态
//        if (wifiManager.isWifiEnabled()){
//            checkBox.setText("WIFI已开启");
//            checkBox.setChecked(true);
//        }else{
//            checkBox.setText("WIFI已关闭");
//            checkBox.setChecked(false);
//        }
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    wifiManager.setWifiEnabled(true);
//                    textView.setText("Wifi已开启");
//                }
//// 当取消复选框选中状态时关闭WIFI
//                else {
//                    wifiManager.setWifiEnabled(false);
//                    textView.setText("Wifi已关闭");
//                }
//            }
//
//        });
//        //获得wifi信息
//        StringBuffer sb= new StringBuffer();
//        sb.append("WIFI信息\n");
//        sb.append("MAC:"+wifiInfo.getMacAddress());
//        sb.append("接入点BSSID:"+wifiInfo.getBSSID());
//        sb.append("ip1"+wifiInfo.getIpAddress());
//        sb.append("IP2"+Integer.toHexString(wifiInfo.getIpAddress()));
//        sb.append("IP3"+wifiInfo.getNetworkId());
//        textView.setText(sb.toString());
//        //得到配置好的网络
//        wifiConfigurations = wifiManager.getConfiguredNetworks();
//        textView2.setText("已连接的wifi");
//        if (wifiManager.isWifiEnabled()){
//        for (WifiConfiguration wifiConfiguration:wifiConfigurations){
//            textView2.setText(textView2.getText()+wifiConfiguration.SSID);
//        }}else{
//            textView2.setText("ＷＩＦＩ不可用！");
//        }
//    }
//    //将int类型的ip转换成字符串类型
//    private String ipIntToString(int ip) {
//        try {
//            byte[] bytes = new byte[4];
//            bytes[0] = (byte) (0xff & ip);
//            bytes[1] = (byte) ((0xff00 & ip) >> 8);
//            bytes[2] = (byte) ((0xff0000 & ip) >> 16);
//            bytes[3] = (byte) ((0xff000000 & ip) >> 24);
//            return Inet4Address.getByAddress(bytes).getHostAddress();
//        } catch (Exception e) {
//            return "";
//        }
//    }

//    public void initView() {
//        mSwitch = (Switch) view.findViewById(R.id.mswitch);
//        bName = (TextView) view.findViewById(R.id.textView6);
//        lv = (ListView) view.findViewById(R.id.listView);
//        BA = BluetoothAdapter.getDefaultAdapter();
//        Button button = (Button) view.findViewById(R.id.button2);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent getVisible = new Intent(BluetoothAdapter.
//                        ACTION_REQUEST_DISCOVERABLE);
//                startActivityForResult(getVisible, 0);
//            }
//        });
//        //判断蓝牙状态设置按钮初始状态
//        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        bName.setText(bluetoothAdapter.getName());
//        if (bluetoothAdapter.isEnabled() == true) {
//            mSwitch.setChecked(true);
//            mSwitch.setText("蓝牙已打开");
//        } else if (bluetoothAdapter.isEnabled() == false) {
//            mSwitch.setChecked(false);
//            mSwitch.setText("蓝牙已关闭");
//        } else {
//            Toast.makeText(getContext(), "本机不支持蓝牙功能！", Toast.LENGTH_SHORT).show();
//        }
//        //按钮监听控制蓝牙开关
//        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    BluetoothAdapter.getDefaultAdapter().enable();
//                    mSwitch.setText("蓝牙已打开");
//                    Toast.makeText(getContext(), "正在开启蓝牙，请等待...", Toast.LENGTH_SHORT).show();
//                } else {
//                    BluetoothAdapter.getDefaultAdapter().disable();
//                    mSwitch.setText("蓝牙已关闭");
//                    Toast.makeText(getContext(), "正在关闭蓝牙...", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
    public void initView1(){
        button6 = (Button) view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
    }
}
//        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
//        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
//        //设置广播信息过滤
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
//        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
//        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
//        //注册广播接收器，处理搜索
//        BroadcastReceiver broadcastReceiver =  new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                System.out.println("我执行了");
//                Toast.makeText(getContext(),"正在搜索中。。。",Toast.LENGTH_SHORT).show();
//            }
//        };
//        broadcastManager.registerReceiver(broadcastReceiver,intentFilter);
//        //寻找蓝牙设备，android会将查到的设备以广播形式发送出去
//        System.out.println("我执行了");
//        adapter.startDiscovery();
//        System.out.println("我执行了");
//        Intent intent = new Intent("android.intent.action.CART_BROADCAST");
//        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
//    }

