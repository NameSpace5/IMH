package com.zdh.alphathink.imh.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.zdh.alphathink.imh.MainActivity;
import com.zdh.alphathink.imh.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends AppCompatActivity {
    private EditText mLoginName, mLoginPassword;
    private Button mButtonLogin;
    private String username, password;
    private CheckBox login_auto,psw_rem;
    private SharedPreferences preference_user;
    private SharedPreferences.Editor editor;
    private View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "43dbe9d5e9502bed6fd2542b5adc1a33");// 初始化Bmob
        mLoginName = (EditText) findViewById(R.id.phoneNumber);
        mLoginPassword = (EditText) findViewById(R.id.mpassword);
        login_auto = (CheckBox) findViewById(R.id.login_auto);
        psw_rem = (CheckBox) findViewById(R.id.psw_rem);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        mLoginName.setText(username);
        preference_user = this.getSharedPreferences("login_state",MODE_PRIVATE);
        mLoginName.setText(preference_user.getString("USERNAME",""));

        //判断记住密码多选框状态
        if (preference_user.getBoolean("ISCHECK",true)){
            //记住密码
            psw_rem.setChecked(true);
            mLoginName.setText(preference_user.getString("USERNAME",""));
            mLoginPassword.setText(preference_user.getString("PASSWORD",""));
            //判断自动登录状态
            if (preference_user.getBoolean("AUTO_ISCHECK",true)){
                login_auto.setChecked(true);
                //login
               onClick11(view1);
            }
        }
        psw_rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (psw_rem.isChecked()){
                    preference_user.edit().putBoolean("ISCHECK",true).apply();
                }else{
                    preference_user.edit().putBoolean("ISCHECK",false).apply();
                }
            }
        });
        login_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (login_auto.isChecked()){
                    preference_user.edit().putBoolean("ISCHECK",true).apply();
                    preference_user.edit().putBoolean("AUTO_ISCHECK",true).apply();
                }else{
                    preference_user.edit().putBoolean("AUTO_ISCHECK",false).apply();
                }
            }
        });


    }

    public void onClick11(View view1) {
        final String username = mLoginName.getText().toString();
        final String password = mLoginPassword.getText().toString();
        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            BmobQuery<creat_user> query = new BmobQuery<creat_user>();// 查询类
            query.addWhereEqualTo("phoneNumber", username);// 查询条件
            query.findObjects(this, new FindListener<creat_user>() {

                @Override
                public void onSuccess(List<creat_user> userlist) {
                    if (userlist == null) {// 查询不到，用户名可用

                        Toast.makeText(LoginActivity.this, "用户名错误！", Toast.LENGTH_SHORT).show();

                    } else {

                        if (userlist.get(0).getPassword()
                                .equals(password)) {
                            Toast.makeText(LoginActivity.this, "登录成功！返回主界面！", Toast.LENGTH_SHORT)
                                    .show();
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            if(psw_rem.isChecked()){
                                //记住用户名、密码
                               editor=preference_user.edit();
                                editor.putString("USERNAME",username);
                                editor.putString("PASSWORD",password);
                                editor.commit();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }

                }

                @Override
                public void onError(int arg0, String arg1) {
                    // TODO Auto-generated method stub

                }
            });


        }
    }

    public void register(View view) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, register.class);
        startActivity(intent);
    }


}