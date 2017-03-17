package com.zdh.alphathink.imh.Personal.UserDao;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.zdh.alphathink.imh.Bean.ACache;
import com.zdh.alphathink.imh.CustomWidgets.ClearWriteEditText;
import com.zdh.alphathink.imh.R;

import org.json.JSONArray;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private ClearWriteEditText nickname,phonenumber,sms_code,password;
    private Button sms_get,register;
    private TextView psd_found,login;
    private ImageView mImgBackground;
    private String mPhone,mCode,mNickName,mPassword;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView(){
        nickname = (ClearWriteEditText) findViewById(R.id.reg_username);
        phonenumber = (ClearWriteEditText) findViewById(R.id.reg_phone);
        sms_code = (ClearWriteEditText) findViewById(R.id.reg_code);
        password = (ClearWriteEditText) findViewById(R.id.reg_password);
        sms_get = (Button) findViewById(R.id.reg_getcode);
        register = (Button) findViewById(R.id.reg_button);
        psd_found = (TextView) findViewById(R.id.reg_forget);
        login = (TextView) findViewById(R.id.reg_login);

        sms_get.setOnClickListener(this);
        sms_get.setClickable(false);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        mImgBackground = (ImageView) findViewById(R.id.rg_img_backgroud);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.translate_anim);
                mImgBackground.startAnimation(animation);
            }
        }, 200);

        addEditTextListener();
    }

    private void addEditTextListener(){
        phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11){
                    BmobQuery query = new BmobQuery("table_user");
                    query.addWhereEqualTo("phoneNumber",phonenumber.getText().toString());
                    query.findObjectsByTable(new QueryListener<JSONArray>() {
                        @Override
                        public void done(JSONArray jsonArray, BmobException e) {
                            if (e == null){
                                if (jsonArray.length() == 0){//未查询到，可用
                                    toast("该手机号可用");
                                    sms_get.setClickable(true);
                                    sms_get.setBackground(getResources().getDrawable(R.drawable.rs_select_btn_blue));
                                }else{
                                    toast("该手机号已被注册");
                                    sms_get.setClickable(false);
                                    sms_get.setBackground(getResources().getDrawable(R.drawable.rs_select_btn_gray));
                                }
                            }else{
                                toast("未知错误");
                            }
                        }
                    });
                }else{
                sms_get.setClickable(false);
                    sms_get.setBackground(getResources().getDrawable(R.drawable.rs_select_btn_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.reg_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.reg_forget:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
            case R.id.reg_getcode:
                BmobSMS.requestSMSCode(phonenumber.getText().toString(), "AlphaThink", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e == null){
                            toast("短信已发送");
                        }else{
                            toast("未知错误");
                        }
                    }
                });
                break;
            case R.id.reg_button:
                mPhone = phonenumber.getText().toString().trim();
                mCode = sms_code.getText().toString().trim();
                mNickName = nickname.getText().toString().trim();
                mPassword = password.getText().toString().trim();
                if (mNickName.equals("")){
                    nickname.setSharkeAnimation();
                }else if (mPhone.equals("")){
                    phonenumber.setSharkeAnimation();
                }else if (mCode.equals("")){
                    sms_code.setSharkeAnimation();
                }else if (mPassword.equals("")){
                    password.setSharkeAnimation();
                }else{
                    BmobSMS.verifySmsCode(mPhone, mCode, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                User user = new User();
                                user.setNickname(mNickName);
                                user.setPassword(mPassword);
                                user.setPhoneNumber(mPhone);
                                user.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null){
                                          toast("注册成功");
                                            Intent intent = new Intent();
                                            intent.putExtra("phonenumber",mPhone);
                                            intent.setClass(RegisterActivity.this,LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            toast("注册失败"+e.getMessage());
                                        }
                                    }
                                });
                            }
                        }
                    });
                }

            break;
        }
    }

    private void toast(String msg){
        Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
