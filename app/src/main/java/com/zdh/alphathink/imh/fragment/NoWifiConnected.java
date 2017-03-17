package com.zdh.alphathink.imh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zdh.alphathink.imh.Control.Bell.Bell;
import com.zdh.alphathink.imh.Control.Box.Box;
import com.zdh.alphathink.imh.Control.Tablet.Tablet;
import com.zdh.alphathink.imh.Control.Clock.Clock;
import com.zdh.alphathink.imh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoWifiConnected extends Fragment implements View.OnClickListener{
    private View view;
    private LinearLayout control_box;
    private LinearLayout control_tablet;
    private LinearLayout control_clock;
    private LinearLayout control_bell;


    public NoWifiConnected() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_no_wifi_connected, container, false);
        control_box = (LinearLayout) view.findViewById(R.id.control_box);
        control_tablet = (LinearLayout) view.findViewById(R.id.control_tablet);
        control_clock = (LinearLayout) view.findViewById(R.id.control_clock );
        control_bell = (LinearLayout) view.findViewById(R.id.control_bell);
        control_box.setOnClickListener(this);
        control_tablet.setOnClickListener(this);
        control_clock.setOnClickListener(this);
        control_bell.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch(v.getId()){
            case R.id.control_box:
                intent.setClass(getActivity(), Box.class);
                break;
            case R.id.control_tablet:
                intent.setClass(getActivity(), Tablet.class);
                break;
            case R.id.control_clock:
                intent.setClass(getActivity(), Clock.class);
                break;
            case R.id.control_bell:
                intent.setClass(getActivity(),Bell.class);
                break;
        }
        startActivity(intent);
    }
}
