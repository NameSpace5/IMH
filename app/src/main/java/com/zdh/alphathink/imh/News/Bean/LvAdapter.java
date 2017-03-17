package com.zdh.alphathink.imh.News.Bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Panda on 2016/12/19.
 */

public class LvAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public LvAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
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
        TextView tv = new TextView(context.getApplicationContext());
        tv.setText(list.get(position));
        return tv;
    }

}


