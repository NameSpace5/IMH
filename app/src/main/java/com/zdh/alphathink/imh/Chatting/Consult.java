package com.zdh.alphathink.imh.Chatting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zdh.alphathink.imh.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.RongListWrap;

public class Consult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CSCustomServiceInfo.Builder csBuilder= new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
        RongIM.getInstance().startCustomerServiceChat(this,"KEFU148707318428443","客服",csInfo);
    }



}
