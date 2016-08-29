package com.zdh.alphathink.imh.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.zdh.alphathink.imh.R;

import java.util.Locale;
/**
 * Created by Panda on 2016/7/20.
 */
public class BlueToothFragment extends Fragment {
    private Button button;
    private Switch mSwitch;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        initView();
        return view;
    }
    // 你这个switch 在fragment里面，为什么 要在在activity 里进行初始化   mswitch ，在
    // activity_index.xml里面都没有 ，肯定null
    public void initView() {
    mSwitch = (Switch) view.findViewById(R.id.mswitch);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

}
