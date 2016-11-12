package com.zdh.alphathink.imh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zdh.alphathink.imh.Personal.PersonalActivity;
import com.zdh.alphathink.imh.R;
import com.zdh.alphathink.imh.User.LoginActivity;

/**
 * Created by Panda on 2016/9/10.
 */
public class PersonalFragment extends Fragment {
    private ImageView imageView;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saceInstanceState)
    {
        view =  inflater.inflate(R.layout.activity_personal_fragment,container,false);
        initDate();
        return view;
    }
    public void initDate(){
        imageView = (ImageView) view.findViewById(R.id.my);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
