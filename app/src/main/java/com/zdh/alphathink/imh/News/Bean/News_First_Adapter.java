package com.zdh.alphathink.imh.News.Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdh.alphathink.imh.R;

import java.util.List;

import static android.R.attr.type;

/**
 * Created by Panda on 2016/12/17.
 */

public class News_First_Adapter extends BaseAdapter {
    private List<NewsBean> list;
    private LayoutInflater inflater;


    public News_First_Adapter(Context context,List<NewsBean> list){
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsBean bean = list.get(position);
        boolean type = bean.isHavePic();
        ViewNoPic holderNoPic = null;
        ViewPic holderPic = null;

        if (convertView == null){
//            if (type){
//                holderNoPic = new ViewNoPic();
//                convertView = inflater.inflate()
//            }
            holderNoPic = new ViewNoPic();
            convertView = inflater.inflate(R.layout.news_convertview_nopic,null);
            holderNoPic.channelName = (TextView) convertView.findViewById(R.id.channelName);
            holderNoPic.title = (TextView) convertView.findViewById(R.id.news_first_title);
            holderNoPic.content = (TextView) convertView.findViewById(R.id.news_first_content);
            holderNoPic.pubDate = (TextView) convertView.findViewById(R.id.news_first_pubData);
            holderNoPic.source = (TextView) convertView.findViewById(R.id.news_first_source);

            holderNoPic.channelName.setText(bean.getChannelName());
            holderNoPic.title.setText(bean.getTitle());
            holderNoPic.content.setText(bean.getContent());
            holderNoPic.pubDate.setText(bean.getPubData());
            holderNoPic.source.setText(bean.getSource());
            convertView.setTag(holderNoPic);
        }else{
            holderNoPic = (ViewNoPic) convertView.getTag();


            holderNoPic.channelName.setText(bean.getChannelName());
            holderNoPic.title.setText(bean.getTitle());
            holderNoPic.content.setText(bean.getContent());
            holderNoPic.pubDate.setText(bean.getPubData());
            holderNoPic.source.setText(bean.getSource());
        }
        return convertView;
    }

    //布局模式
    class ViewNoPic{
        private TextView content;
        private TextView channelName;
        private TextView title;
        private TextView pubDate;
        private TextView desc;
        private TextView source;
    }
    class ViewPic{
        private TextView content;
        private TextView channelName;
        private TextView title;
        private TextView pubData;
        private TextView desc;
        private TextView source;
        private ImageView Img;
    }

}
