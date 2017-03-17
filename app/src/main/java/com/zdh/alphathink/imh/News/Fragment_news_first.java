package com.zdh.alphathink.imh.News;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.show.api.ShowApiRequest;
import com.zdh.alphathink.imh.Bean.ACache;
import com.zdh.alphathink.imh.News.Bean.Custom_ListView;
import com.zdh.alphathink.imh.News.Bean.NewsBean;
import com.zdh.alphathink.imh.News.Bean.News_First_Adapter;
import com.zdh.alphathink.imh.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;


public class Fragment_news_first extends Fragment {

    private View view;
    private ACache aCache;
    private News_First_Adapter adapter;
    private Custom_ListView listView;
    private List<NewsBean> list = new ArrayList<NewsBean>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_news_first, container, false);
        listView = (Custom_ListView) view.findViewById(R.id.listview_news_first);
        aCache = ACache.get(getActivity());
        adapter = new News_First_Adapter(getActivity(),list);
        initAllData();
        return view;
    }

    public void initAllData() {
        final JSONObject jsonObject = aCache.getAsJSONObject("NewsDatas");
        if (null == jsonObject) {
            new AsyncTask<Void, Void, Void>() {
                ProgressDialog progressDialog = null;
                @Override
                protected Void doInBackground(Void... params) {
                    refreshData();
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setTitle("请稍候...");
                    progressDialog.setMessage("正在更新数据...");
                    progressDialog.show();
                }

                @Override
                protected void onProgressUpdate(Void... values) {
                    super.onProgressUpdate(values);
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    progressDialog.dismiss();
                    initAllData();
                }
            }.execute(null, null, null);


        } else {
            try {
                initListData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listView.setAdapter(adapter);
//        Toast.makeText(getActivity(),""+list,Toast.LENGTH_LONG).show();

            /**
             * 这里是调用下拉刷新的代码，500ms延迟动画展示
             */
            listView.setonRefreshListener(new Custom_ListView.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new AsyncTask<Void, Void, Void>() {
                        protected Void doInBackground(Void... params) {
                            try {
                                refreshData();//数据在此处已刷新
                                Thread.sleep(500);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            try {
                                list.clear();
                                initListData();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();//通知界面更新，但是没有用。。。
                            listView.onRefreshComplete();
                        }
                    }.execute(null, null, null);
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                String url = null;
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(),HealthNewsDetail.class);
                    try {
                        url = jsonObject.getJSONObject("showapi_res_body").getJSONObject("pagebean")
                                .getJSONArray("contentlist").getJSONObject(position-1).getString("link");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            });
        }
    }

    public List<NewsBean> initListData() throws JSONException {
        JSONObject jsonObject = aCache.getAsJSONObject("NewsDatas");
        JSONArray array  = jsonObject.getJSONObject("showapi_res_body")
                .getJSONObject("pagebean").getJSONArray("contentlist");

        for (int i = 0;i<array.length();i++){
           JSONObject js = (JSONObject) array.get(i);
            NewsBean newsBean = new NewsBean();
            newsBean.setAllList(js.getJSONArray("allList"));
            newsBean.setPubDate(js.getString("pubDate"));
            newsBean.setHavePic(js.getBoolean("havePic"));
            newsBean.setTitle(js.getString("title"));
            newsBean.setChannelName(js.getString("channelName"));
            newsBean.setImgUrls(js.getJSONArray("imageurls"));
            newsBean.setDesc(js.getString("desc"));
            newsBean.setSource(js.getString("source"));
            newsBean.setLink(js.getString("link"));
            list.add(newsBean);
        }
        return  list;

    }

    public void refreshData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String appid = "29112";
                String secret = "a0ced64006414ff782abcdc8acfe346d";
                final String res = new ShowApiRequest("http://route.showapi.com/109-35", appid, secret)
                        .addTextPara("channelId", "")
                        .addTextPara("channelName", "")
                        .addTextPara("title", "")
                        .addTextPara("page", "")
                        .addTextPara("needContent", "")
                        .addTextPara("needHtml", "")
                        .addTextPara("needAllList", "")
                        .addTextPara("maxResult", "")
                        .post();

                System.out.print(res);
                aCache.put("NewsDatas",res);
            }

        }).start();
    }
}
