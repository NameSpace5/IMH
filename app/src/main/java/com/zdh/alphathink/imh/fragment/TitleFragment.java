package com.zdh.alphathink.imh.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zdh.alphathink.imh.R;
import com.zdh.alphathink.imh.User.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

public class TitleFragment extends Fragment {
    private View view;
    private ImageButton imageButton;
    private SharedPreferences preference_user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saceInstanceState)
    {
        view = inflater.inflate(R.layout.title_fragment,container,false);
        preference_user = getActivity().getSharedPreferences("login_state",MODE_PRIVATE);

        initView();
        return view;
    }
    public void initView(){
        if (preference_user.getBoolean("AUTO_ISCHECK",true)){
            //自动登录直接向服务器
        }else{

        }
    }
}