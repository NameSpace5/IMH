//package com.zdh.alphathink.imh.Control;
//
//import android.app.Activity;
//import android.app.Application;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.provider.Settings;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.EditText;
//import android.widget.ImageButton;
//
//import com.zdh.alphathink.imh.R;
//
//import java.util.ArrayList;
//
///**
// * Created by Panda on 2016/10/18.
// */
//
//public class AddAdapter extends BaseAdapter {
//    private LayoutInflater mInflater;
//    private ArrayList<String> text;
//    public Context mContext;
//
//    public AddAdapter(Context context){
//        text = new ArrayList<String>();
//        text.add("第一项");//默认加载第一个item
//        this.mInflater = LayoutInflater.from(context);
//    }
//    @Override
//    public int getCount() {
//        return text.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return text.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = new ViewHolder();
//        if (convertView == null){
//            convertView = mInflater.inflate(R.layout.addnumber,null);
////            holder.btnOpen = (ImageButton) convertView.findViewById(R.id.ibtnAddNumber);
////            holder.editText = (EditText) convertView.findViewById(R.id.editNumber);
//            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
//        switch (position){
//            case 0:
//                holder.btnOpen.setBackgroundResource(R.drawable.addnumber);
//                break;
//            default:
//                holder.btnOpen.setBackgroundResource(R.drawable.deletenumber);
//                break;
//        }
//        holder.btnOpen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (position == 0){
//                    text.add("");//添加控件
//                }else if (position>0){//留一项不能删
//
//
//                    text.remove(text.size()-1);//删除按钮
//                }
//                notifyDataSetChanged();
//            }
//        });
//        return convertView;
//    }
//    public final class ViewHolder{
//        public EditText editText;
//        public ImageButton btnOpen;
//    }
//}
