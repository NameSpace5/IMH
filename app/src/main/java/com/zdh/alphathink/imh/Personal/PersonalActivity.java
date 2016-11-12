package com.zdh.alphathink.imh.Personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zdh.alphathink.imh.Index;
import com.zdh.alphathink.imh.R;

import java.io.OutputStream;
import java.net.Socket;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
    }
    public void about(View view){
        Intent intent = new Intent(PersonalActivity.this,About_us.class);
        startActivity(intent);
    }
    public void index(View view){
        finish();
    }

}
