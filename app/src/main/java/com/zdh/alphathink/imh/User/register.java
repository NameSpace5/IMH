package com.zdh.alphathink.imh.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zdh.alphathink.imh.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

public class register extends AppCompatActivity {
    private Button btn_register;
    private EditText m_phone;
    private EditText m_password, m_smscode,phoneNumber,mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //bmob sdk相关，连接应用id
        Bmob.initialize(this, "43dbe9d5e9502bed6fd2542b5adc1a33");
        m_phone = (EditText) findViewById(R.id.m_phone);
        m_password = (EditText) findViewById(R.id.password);
        m_smscode = (EditText) findViewById(R.id.smscode);


        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本框输入值
                String phone = m_phone.getText().toString();
                String password = m_password.getText().toString();
                //简单的非空验证
                if (phone.equals("") || phone.length() != 11) {
                    Toast.makeText(register.this, "手机号格式不正确！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //短信发送代码
                BmobSMS.requestSMSCode(register.this, phone, "模板名称", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        // TODO Auto-generated method stub
                        if (ex == null) {//验证码发送成功
                            Log.i("bmob", "短信id：" + smsId);//用于查询本次短信发送详情
                        }
                    }
                });
                Toast.makeText(register.this, "短信已发送，请留意验证码！", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void submit(View view) {
        String phone = m_phone.getText().toString();
        String password = m_password.getText().toString();
        String smscode = m_smscode.getText().toString();
        BmobSMS.verifySmsCode(this, phone, smscode, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.e("MESSAGE", "7");

                    String phone = m_phone.getText().toString();
                    String password = m_password.getText().toString();

                    creat_user creatObj = new creat_user();
                    creatObj.setPhoneNumber(phone);
                    creatObj.setPassword(password);
                    creatObj.save(register.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(register.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(register.this,LoginActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(register.this, "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Log.e("MESSAGE:", "8");
                    Toast.makeText(register.this, "未输入数据或验证码错误", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

    public void login(View view){
        Intent intent = new Intent();
        intent.setClass(register.this,LoginActivity.class);
        startActivity(intent);
    }

}
