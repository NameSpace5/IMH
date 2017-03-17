package com.zdh.alphathink.imh.News;

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
 * Created by Panda on 2016/12/2.
 */

public class NewsAdapter extends BaseAdapter {
    public static final int LV_NO_PIC= 0;// 3种不同的布局
    public static final int LV_LITTLE_PIC = 1;
    public static final int LV_BIG_PIC = 2;
    private LayoutInflater mInflater;
    private List<NewsStyleMessage> myList;

    public NewsAdapter(Context context,List<NewsStyleMessage> myList){
        this.myList = myList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //NoPic
    class ViewNoPic{
        private TextView tv_title;
        private TextView tv_content;
    }

    //LittlePic
    class ViewLittlePic{
        private ImageView iv_newspic;
        private TextView tv_title;
        private TextView tv_content;
    }

    //largePic
    class ViewLargePic{
        private ImageView iv_newspic;
        private TextView tv_title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsStyleMessage msg = myList.get(position);
        int type = getItemViewType(position);
        ViewNoPic holderNoPic = null;
        ViewLittlePic holderLittlePic = null;
        ViewLargePic holderLargePic = null;

        if (convertView == null){
            switch (type){
                case LV_NO_PIC:
                    holderNoPic = new ViewNoPic();
                    convertView = mInflater.inflate(R.layout.listview1,null);
                    holderNoPic.tv_title = (TextView) convertView.findViewById(R.id.lv1_title);
                    holderNoPic.tv_content = (TextView) convertView.findViewById(R.id.lv1_content);
                    holderNoPic.tv_title.setText(msg.getTitle());
                    holderNoPic.tv_content.setText(msg.getContent());
                    convertView.setTag(holderNoPic);
                    break;
                case LV_LITTLE_PIC:
                    holderLittlePic = new ViewLittlePic();
                    convertView = mInflater.inflate(R.layout.listview3,null);
                    holderLittlePic.iv_newspic = (ImageView) convertView.findViewById(R.id.lv3_newspic);
                    holderLittlePic.tv_title = (TextView) convertView.findViewById(R.id.lv3_title);
                    holderLittlePic.tv_content = (TextView) convertView.findViewById(R.id.lv3_content);
                    holderLittlePic.iv_newspic.setImageResource(msg.getPic());
                    holderLittlePic.tv_title.setText(msg.getTitle());
                    holderLittlePic.tv_content.setText(msg.getContent());
                    convertView.setTag(holderLittlePic);
                    break;
                case LV_BIG_PIC:
                    holderLargePic = new ViewLargePic();
                    convertView = mInflater.inflate(R.layout.listview2,null);
                    holderLargePic.iv_newspic = (ImageView) convertView.findViewById(R.id.lv2_newspic);
                    holderLargePic.tv_title = (TextView) convertView.findViewById(R.id.lv2_title);
                    holderLargePic.iv_newspic.setImageResource(msg.getPic());
                    holderLargePic.tv_title.setText(msg.getTitle());
                    break;
                default:
                    break;
            }
        }else{
            switch (type){
                case LV_NO_PIC:
                    holderNoPic = (ViewNoPic) convertView.getTag();
                    holderNoPic.tv_title.setText(msg.getTitle());
                    holderNoPic.tv_content.setText(msg.getContent());
                    break;
                case LV_BIG_PIC:
                    holderLargePic=(ViewLargePic)convertView.getTag();
                    holderLargePic.iv_newspic.setImageResource(msg.getPic());
                    holderLargePic.tv_title.setText(msg.getTitle());
                    break;
                case LV_LITTLE_PIC:
                    holderLittlePic=(ViewLittlePic)convertView.getTag();
                    holderLittlePic.iv_newspic.setImageResource(msg.getPic());
                    holderLittlePic.tv_title.setText(msg.getTitle());
                    holderLittlePic.tv_content.setText(msg.getContent());
                    break;

                default:
                    break;
            }
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        NewsStyleMessage msg = myList.get(position);
        int type = msg.getType();
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
