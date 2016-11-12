package com.zdh.alphathink.imh.Control;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zdh.alphathink.imh.Bean.ACache;
import com.zdh.alphathink.imh.CheckNetworkState;
import com.zdh.alphathink.imh.R;
import com.zdh.alphathink.imh.util.GetIpAddress;
import com.zdh.alphathink.imh.util.VoteMsg;
import com.zdh.alphathink.imh.util.VoteMsgTextCoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;;
import static android.R.attr.id;
import static android.R.attr.queryActionMsg;
import static android.content.Context.WIFI_SERVICE;
import static com.zdh.alphathink.imh.R.id.dialog;
import static com.zdh.alphathink.imh.R.id.e;


public class ControlActivity extends AppCompatActivity {
    private OutputStream outputStream = null;
    private Socket socket = null;
    private String data;
    private boolean socketStatus = false;
    private ListView mList;
    private AddAdapter addAdapter;
    private ACache aCache;
    private InputStream inputStream;
    private ServerSocket serverSocket = null;
    private int color = 100;
    private StringBuffer stringBuffer = new StringBuffer();
    private Button bu1, bu2, bu3, bu4, bu5, bu6, bu7, bu8, bu9;
    private TextView tx_used;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    break;
                case 2:
                    Toast.makeText(ControlActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    stringBuffer.setLength(0);
                    break;
                case 3:
                    Toast.makeText(ControlActivity.this, "没有可用服务器", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);


//        DBManager mg = new DBManager(ControlActivity.this);
//        List<MyDate> persons = mg.query();
//        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        for (MyDate person : persons) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("id", person.id);
//            map.put("name", person.name);
//            map.put("color", String.valueOf(person.color));
//            map.put("time", person.time);
//            list.add(map);
//        }



        bu1 = (Button) findViewById(R.id.mb1);
        bu2 = (Button) findViewById(R.id.b2);
        bu3 = (Button) findViewById(R.id.b3);
        bu4 = (Button) findViewById(R.id.b4);
        bu5 = (Button) findViewById(R.id.b5);
        bu6 = (Button) findViewById(R.id.b6);
        bu7 = (Button) findViewById(R.id.b7);
        bu8 = (Button) findViewById(R.id.b8);
        bu9 = (Button) findViewById(R.id.b9);
        tx_used = (TextView) findViewById(R.id.used);

        //listview适配
        mList = (ListView) findViewById(R.id.listview1);
        addAdapter = new AddAdapter(this);
        mList.setAdapter(addAdapter);


        ImageButton imageButton = (ImageButton) findViewById(R.id.ibtnAddNumber);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.Builder builder = new MyDialog.Builder(ControlActivity.this);
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
                        new TimePickerDialog(ControlActivity.this,
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
                                        if (hourOfDay<10){
                                            hour = "0"+hourOfDay;
                                        }else {
                                            hour = String.valueOf(hourOfDay);
                                        }
                                        if (minute<10){
                                            min = "0"+minute;
                                        }
                                        else{
                                            min = String.valueOf(minute);
                                        }
                                        c_time.setText(hour + ":" + min);
                                        //设置何时启动Activity
                                        //send
//                        data = hourOfDay + ":" + minute;
//                        if (data == null) {
//                            Toast.makeText(ControlActivity.this, "请输入数据！", Toast.LENGTH_SHORT).show();
//                        } else {
//                            data = data + '\0';
//                        }
//                        Thread thread = new Thread() {
//                            @Override
//                            public void run() {
//                                super.run();
//                                if (socketStatus) {
//                                    try {
//                                        outputStream.write(data.getBytes());
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        };
//                        thread.start();

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
                                ACache aCache = ACache.get(ControlActivity.this);
                                JSONObject jsonObject;
                                JSONArray jsonArray = null;
                                if (aCache.getAsJSONObject("date") != null) {
                                    jsonObject = aCache.getAsJSONObject("date");
                                    try {
                                        jsonArray = jsonObject.getJSONArray("info");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    jsonObject = new JSONObject();
                                    jsonArray = new JSONArray();
                                }
                                JSONObject jsonObject1 = new JSONObject();


                                //判断目标药箱是否已设置
                                int i;
                                JSONObject js = null;
                                for (i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        js = (JSONObject) jsonArray.get(i);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    if (aCache.getAsJSONObject("date") != null) {
                                        if (js != null && c_id.getText().toString().equals(js.get("id"))) {
                                            Toast.makeText(ControlActivity.this, "药箱编号已设置请检查！", Toast.LENGTH_SHORT).show();
                                        } else {
                                            try {
                                                jsonObject1.put("id", c_id.getText().toString());
                                                jsonObject1.put("name", c_name.getText().toString());
                                                jsonObject1.put("color", color);
                                                jsonObject1.put("time", c_time.getText().toString());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject1);

                                            try {
                                                jsonObject.put("info", jsonArray);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            aCache.put("date", jsonObject);

                                            //socket通讯
//                                            new Thread(new WriteThread(socket){
//                                                @Override
//                                                public void run() {
//                                                    DataOutputStream dos = null;
//                                                    BufferedReader br = null;
//                                                    try {  dos = new DataOutputStream(client.getOutputStream());
//                                                        int mid = Integer.parseInt(String.valueOf(c_id.getText()));
//                                                        String mName = (c_name.getText().toString());
//                                                        String mTime = (c_time.getText().toString());
//                                                        String mmTime = mTime.substring(0,2)+mTime.substring(3,5);
//
//                                                        VoteMsgTextCoder voteMsgTextCoder = new VoteMsgTextCoder();
//                                                        VoteMsg voteMsg = new VoteMsg(1,mid,color,mName,mmTime);
//                                                        byte[] bytes = new byte[20];
//                                                        try {
//                                                            bytes = voteMsgTextCoder.toWire(voteMsg);
//                                                        } catch (IOException e1) {
//                                                            e1.printStackTrace();
//                                                        }
//                                                        dos.write(bytes);
//                                                    } catch (IOException e1) {
//                                                        e1.printStackTrace();
//                                                    }
//
//                                                }
//                                            }
//                                            ).start();

                                            dialog.dismiss();
                                            initState();
                                            addAdapter.notifyDataSetChanged();
                                        }
                                    } else {
                                        try {
                                            jsonObject1.put("id", c_id.getText().toString());
                                            jsonObject1.put("name", c_name.getText().toString());
                                            jsonObject1.put("color", color);
                                            jsonObject1.put("time", c_time.getText().toString());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject1);

                                        try {
                                            jsonObject.put("info", jsonArray);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        aCache.put("date", jsonObject);
                                        dialog.dismiss();
                                        addAdapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //打开或创建sqllite
//                                SQLiteDatabase db = openOrCreateDatabase("date.db",Context.MODE_PRIVATE,null);
//                                db.execSQL("create table if not exists date(_id INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR,name VARCHAR, color VARCHAR,time VARCHAR)");
//                                //创建表date
////                                db.execSQL("CREATE TABLE date (_id INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR,name VARCHAR, color VARCHAR,time VARCHAR)");
//                                MyDate date = new MyDate();
//                                date.id = c_id.getText().toString();
//                                date.name = c_name.getText().toString();
//                                date.color = color;
//                                date.time = c_time.getText().toString();
                                //插入数据
//                                db.execSQL("INSERT INTO date VALUES (NULL,?,?,?,?)", new Object[]{date.id,date.name,date.color,date.time});
//                                db.close();
//                                dialog.dismiss();
//                                addAdapter.notifyDataSetChanged();
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
        });

        imageButton = (ImageButton) findViewById(R.id.goIndex);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ControlActivity.this, Index.class);
//                startActivity(intent)
                try {
                    if (socketStatus) {
                        socket.close();
                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();

            }
        });
        initServer();
        initState();
    }

    public void initState() {
        aCache = ACache.get(ControlActivity.this);
        JSONObject js = aCache.getAsJSONObject("date");
        if (js != null) {
            try {
                bu1.setBackgroundResource(R.color.list_bg);
                bu2.setBackgroundResource(R.color.list_bg);
                bu3.setBackgroundResource(R.color.list_bg);
                bu4.setBackgroundResource(R.color.list_bg);
                bu5.setBackgroundResource(R.color.list_bg);
                bu6.setBackgroundResource(R.color.list_bg);
                bu7.setBackgroundResource(R.color.list_bg);
                bu8.setBackgroundResource(R.color.list_bg);
                bu9.setBackgroundResource(R.color.list_bg);
                JSONArray jsonArray = js.getJSONArray("info");
                if (jsonArray != null) {
                    String used = " ";
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int mid = jsonObject.getInt("id");
                        used = used + mid+",";
                        int color = jsonObject.getInt("color");
                        if (color == 100) {
                            color = Color.rgb(255, 0, 0);
                        } else if (color == 101) {
                            color = Color.rgb(0, 255, 255);
                        } else {
                            color = Color.rgb(0, 0, 255);
                        }
                        switch (mid) {
                            case 1:
                                bu1.setBackgroundColor(color);
                                break;
                            case 2:
                                bu2.setBackgroundColor(color);
                                break;
                            case 3:
                                bu3.setBackgroundColor(color);
                                break;
                            case 4:
                                bu4.setBackgroundColor(color);
                                break;
                            case 5:
                                bu5.setBackgroundColor(color);
                                break;
                            case 6:
                                bu6.setBackgroundColor(color);
                                break;
                            case 7:
                                bu7.setBackgroundColor(color);
                                break;
                            case 8:
                                bu8.setBackgroundColor(color);
                                break;
                            case 9:
                                bu9.setBackgroundColor(color);
                                break;
                            default:
                                bu1.setText("ddd");

                        }
                            tx_used.setText(used);
                    }
                }else{
                    tx_used.setText("当前未设置！");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void initServer() {
        if (!CheckNetworkState.isWifi(this)) {
            MyDialog.Builder builder = new MyDialog.Builder(this);
            builder.setMessage("请链接以IMH开头的WIFI！");
            builder.setTitle("请链接WIFI后重试！");
            builder.setPositiveButton("连接", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消",
                    new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.creat().show();

            //如果连接后判断是否正确
        } else if (CheckNetworkState.isWifi(this)) {
            WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String wifi_name = wifiInfo.getSSID();
            wifi_name = wifi_name.substring(1, 4);
//            Toast.makeText(this,wifi_name,Toast.LENGTH_SHORT).show();
            if (wifi_name.equals("IMH")) {
                //全部链接正确，可进行相关设置
                connect();
            } else {
                MyDialog.Builder builder = new MyDialog.Builder(this);
                builder.setMessage("您连接的WIFI非IMH产品WIFI，请重新连接！");
                builder.setTitle("连接对象出错！");
                builder.setPositiveButton("连接", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.creat().show();
            }
        }
    }

    public void connect() {
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        final String ip = GetIpAddress.getIpAddress(wifiInfo);
        final String mip = "192.168.4.1";

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    socket = new Socket(mip, 8080);
                    new Thread(new ReadThread(socket)).start();
                    socketStatus = true;

                } catch (
                        IOException e
                        ) {
                    e.printStackTrace();
                }

            }

            ;
        };
        thread.start();

    }

    /*
    socket
     */

    //处理读操作
    class ReadThread implements Runnable {
        private Socket client;

        public ReadThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            DataInputStream dis = null;
            try {
                while (true) {
                    //读取客户端数据
                    dis = new DataInputStream(client.getInputStream());
                    String receiver = dis.readUTF();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (dis != null) {
                        dis.close();
                    }
                    if (client != null) {
                        client = null;
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    //处理写操作
    class WriteThread implements Runnable {
        Socket client;

        public WriteThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            DataOutputStream dos = null;
            BufferedReader br = null;
            try {
                dos = new DataOutputStream(client.getOutputStream());
                VoteMsgTextCoder voteMsgTextCoder = new VoteMsgTextCoder();
                VoteMsg voteMsg = new VoteMsg(2, 2, 200, "阿莫西林", "1250");
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
//            finally {
//                try {
//                    if (dos != null) {
//                        dos.close();
//                    }
//                    if (br != null) {
//                        br.close();
//                    }
//                    if (client != null) {
//                        client = null;
//                    }
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
        }
    }

    /*
    客户端返回时关闭socket 释放资源
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            if (socketStatus) {
                socket.close();
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    public void setLight(View view) {
//        int id = radioGroup.getCheckedRadioButtonId();
//        switch (id) {
//            case R.id.l1:
//                data = "red";
//                break;
//            case R.id.l2:
//                data = "yellow";
//                break;
//            case R.id.l3:
//                data = "blue";
//                break;
//        }
//        if (data == null) {
//            Toast.makeText(ControlActivity.this, "请输入数据！", Toast.LENGTH_SHORT).show();
//        } else {
//            data = data + '\0';
//        }
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                if (socketStatus) {
//                    try {
//                        outputStream.write(data.getBytes());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread.start();
//    }
//内部类 listview适配相关
    class AddAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private ArrayList<String> text;
        public Context mContext;
        private ACache aCache;

        public AddAdapter(Context context) {
            text = new ArrayList<String>();
            this.mInflater = LayoutInflater.from(context);
            aCache = ACache.get(ControlActivity.this);
            JSONObject jsonObject = aCache.getAsJSONObject("date");
        }

        @Override
        public int getCount() {
            aCache = ACache.get(ControlActivity.this);
            JSONObject jsonObject = aCache.getAsJSONObject("date");
            if (jsonObject == null) {
                return 0;
            } else {
                JSONArray jsonArray = null;
                try {
                    jsonArray = jsonObject.getJSONArray("info");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return (jsonArray == null) ? 0 : jsonArray.length();
            }
        }

        @Override
        public Object getItem(int position) {
            return text.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            ACache aCache = ACache.get(ControlActivity.this);
            JSONObject jsonObject = aCache.getAsJSONObject("date");
            JSONArray jsonArray = null;
            JSONObject json = null;

            if (jsonObject != null) {
                try {
                    jsonArray = jsonObject.getJSONArray("info");
                    json = jsonArray.getJSONObject(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.addnumber, null);
                holder.btnOpen = (ImageView) convertView.findViewById(R.id.ibtnAddNumber);
                holder.mid = (TextView) convertView.findViewById(R.id.v_id);
                holder.mname = (TextView) convertView.findViewById(R.id.v_name);
                holder.mtime = (TextView) convertView.findViewById(R.id.v_time);
                holder.mcolor = (TextView) convertView.findViewById(R.id.v_color);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (json != null) {
                try {
                    holder.mid.setText(json.getString("id"));
                    holder.mname.setText(json.getString("name"));
                    holder.mtime.setText(json.getString("time"));
                    switch (json.getString("color")) {
                        case "100":
                            holder.mcolor.setText("红色");
                            break;
                        case "101":
                            holder.mcolor.setText("绿色");
                            break;
                        case "102":
                            holder.mcolor.setText("蓝色");
                            break;
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            } else {

            }

            switch (position) {
                case 0:
//                    holder.editText.setText(aCache.getAsString("date"));
                    holder.btnOpen.setBackgroundResource(R.drawable.deletenumber);
                    break;
                default:
                    holder.btnOpen.setBackgroundResource(R.drawable.deletenumber);
                    break;
            }
            holder.btnOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (position == 0){
//                        MyDialog.Builder builder = new MyDialog.Builder(ControlActivity.this);
//                        builder.setTitle("添加设置项");
//                        builder.setMessage("ddd");
//                        builder.setPositiveButton("确定添加",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        text.add("");
//                                        dialog.dismiss();
//                                        notifyDataSetChanged();
//                                     }
//                                });
//                        builder.creat().show();
//                    }else
                    if (position >=0) {//留一项不能删
                        MyDialog.Builder builder = new MyDialog.Builder(ControlActivity.this);
                        builder.setMessage("您确定要删除此项吗？");
                        builder.setTitle("删除确认");
                        builder.setPositiveButton("确认删除",
                                new android.content.DialogInterface.OnClickListener() {
                                    @TargetApi(Build.VERSION_CODES.KITKAT)
                                    public void onClick(DialogInterface dialog, int which) {
                                        ACache aCache = ACache.get(ControlActivity.this);
                                        JSONObject jsonObject = aCache.getAsJSONObject("date");
                                        JSONArray jsonArray = null;
                                        try {
                                            jsonArray = jsonObject.getJSONArray("info");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.remove(position);
                                        try {
                                            jsonObject.put("info", jsonArray);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        aCache.put("date", jsonObject);
                                        dialog.dismiss();
                                        initState();
                                        notifyDataSetChanged();

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
            });
            return convertView;
        }


        public final class ViewHolder {
            public ImageView btnOpen;
            public TextView mid, mname, mcolor, mtime;

        }
    }

    class ServerThread extends Thread {
        private Socket socket;
        private InputStream inputStream;
        private StringBuffer stringBuffer = ControlActivity.this.stringBuffer;

        public ServerThread(Socket socket, InputStream inputStream) {
            this.socket = socket;
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            int len;
            byte[] bytes = new byte[20];
            boolean isString = false;
            try {
                while ((len = inputStream.read(bytes)) != -1) {
                    for (int i = 0; i < len; i++) {
                        if (bytes[i] != '\0') {
                            stringBuffer.append((char) bytes[i]);
                        } else {
                            isString = true;
                            break;
                        }
                    }
                    if (isString) {
                        Message message_2 = handler.obtainMessage();
                        message_2.what = 2;
                        message_2.obj = stringBuffer;
                        handler.sendMessage(message_2);
                        isString = false;
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                try {
                    Message message = handler.obtainMessage();
                    message.what = 3;
                    handler.sendMessage(message);
                    inputStream.close();
                    socket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            super.run();
        }
    }

    public void did(View view) {
//        VoteMsgTextCoder voteMsgTextCoder = new VoteMsgTextCoder();
//        VoteMsg voteMsg = new VoteMsg(true,true,2,2);
//        byte[] bytes;
//        try {
//            bytes = voteMsgTextCoder.toWire(voteMsg);
//            Toast.makeText(ControlActivity.this,bytes.toString(),Toast.LENGTH_SHORT).show();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        final String ip = GetIpAddress.getIpAddress(wifiInfo);

        Toast.makeText(ControlActivity.this, ip, Toast.LENGTH_SHORT).show();

    }
}
