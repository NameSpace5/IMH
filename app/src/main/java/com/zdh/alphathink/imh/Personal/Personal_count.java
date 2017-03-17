package com.zdh.alphathink.imh.Personal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.zdh.alphathink.imh.R;

public class Personal_count extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout imgBtn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personl_count);
        imgBtn_back = (FrameLayout) findViewById(R.id.back_personal_about_us);
        imgBtn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back_personal_about_us:
                finish();
        }
    }
}
