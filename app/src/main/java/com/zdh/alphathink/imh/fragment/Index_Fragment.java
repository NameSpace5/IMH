package com.zdh.alphathink.imh.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdh.alphathink.imh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index_Fragment extends Fragment {


    public Index_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_, container, false);
    }

}
