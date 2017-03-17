package com.zdh.alphathink.imh.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zdh.alphathink.imh.R;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class Doctor_consult extends Fragment {
    private View view;
    private Button consult;

    public Doctor_consult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_doctor_consult, container, false);
//        CSCustomServiceInfo.Builder csBuilder= new CSCustomServiceInfo.Builder();
//        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
//        RongIM.getInstance().startCustomerServiceChat(getActivity(),"KEFU148707318428443","客服",csInfo);
//        consult = (Button) view.findViewById(R.id.mconsult);
//        consult.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////                startActivity(new Intent(getActivity(), Consult.class));
//                CSCustomServiceInfo.Builder csBuilder= new CSCustomServiceInfo.Builder();
//                CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
//                RongIM.getInstance().startCustomerServiceChat(getActivity(),"KEFU148707318428443","客服",csInfo);
//
//            }
//        });
        return view;
    }




}
