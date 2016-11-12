package com.zdh.alphathink.imh.News;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zdh.alphathink.imh.Bean.ACache;
import com.zdh.alphathink.imh.Bean.DBHelper;
import com.zdh.alphathink.imh.Index;
import com.zdh.alphathink.imh.InitValue;
import com.zdh.alphathink.imh.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;
import static android.os.Build.VERSION_CODES.M;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.zdh.alphathink.imh.R.id.e;

public class NewsActivity extends AppCompatActivity{
    private JSONObject mJsonObject;
    private ListView listView;
    private ACache  aCache;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initAcache();



        /*判断有无网络，无网加载本地缓存，有网络查询数据库数据是否过期
        *如果过期网络下载数据，没有过期加载本地缓存
        */
        JSONObject cacheData = aCache.getAsJSONObject("my");
        if (!Index.checkNetworkConnection(NewsActivity.this)){
           Toast.makeText(this,"没有网络啦！！！",Toast.LENGTH_SHORT).show();
        }else{
            //网络可用进行下一步
            initDate();
        }

        /////////////////////////////////
        listView = (ListView) findViewById(R.id.listView);
        SimpleAdapter listAdapter = new SimpleAdapter(this, getData(),
                R.layout.list_item, new String[]{"img", "title", "info"},
                new int[]{R.id.imageView2, R.id.title, R.id.info});
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent();
                    intent.setClass(NewsActivity.this, news_detail.class);
                    startActivity(intent);
                }
                if (position == 0){
                    Intent intent = new Intent();
                    intent.setClass(NewsActivity.this,news_detail.class);
                    startActivity(intent);
                }
            }
        });
    }
    private List<Map<String, Object>> getData() {
        String result = null;
        try {
            result = aCache.getAsJSONObject("my").getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist").toString();
        }catch(JSONException e){
            e.printStackTrace();
        }


        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "京东等大型网站的混合搜索是怎么实现的？");
        map.put("info", "10分钟前");
        list.add(map);

        String title = null ;
        try {
            title = aCache.getAsJSONObject("my").getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", title);
        map.put("info", "20分钟前");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "视频方向Direction类和视频分类Classification多对多关系，即一个视频方向对应多个视频分类，一个视频分类也可以对应多个视频方向。——classification = models.ManyToManyField('Classification')");
        map.put("info", "1天前");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "视频Video类中level_choice 与视频也是一对多关系");
        map.put("info", "1个月前");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "混合搜索url设计");
        map.put("info", "1年前");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "例如运维自动化：");
        map.put("info", "2年前");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "http://127.0.0.1:8000");
        map.put("info", "。。。");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "目的是为了将此url和用户当前url进行拼接");
        map.put("info", "。。。");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "目的是为了将此url和用户当前url进行拼接");
        map.put("info", "。。。");
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "目的是为了将此url和用户当前url进行拼接");
        map.put("info", "。。。");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.mipmap.ic_launcher);
        map.put("title", "目的是为了将此url和用户当前url进行拼接");
        map.put("info", "。。。");
        list.add(map);

        return list;


    }
    //初始化istview的数据
    public void initDate() {
        JSONObject cacheData = aCache.getAsJSONObject("my");//从缓存中读取数据
        if (cacheData != null) {
            //本地cache读取数据

        } else {//模拟网络请求数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String string = "医药";
                    String str = null;
                    try {
                        str = URLEncoder.encode(string,"utf-8");
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    String httpUrl = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
                    String httpArg = "channelId=5572a109b3cdc86cf39001db&channelName="+str+"&title=%E4%B8%8A%E5%B8%82&page=1&needContent=1&needHtml=0";
                    String jsonResult = request(httpUrl, httpArg);
                    System.out.print(jsonResult);
                    aCache.put("my",jsonResult);
                }

            }).start();
        }
    }

    public String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", "3ace713028f4abe9de414dc98ade98bf");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void initAcache(){
        aCache = ACache.get(this);//默认选择的路径是new File(context.getCacheDir(),// "ACache")

    }
    public void back(View v){
        finish();
    }

}
