package com.zdh.alphathink.imh.Personal.UserDao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.zdh.alphathink.imh.Bean.ACache;
import com.zdh.alphathink.imh.Index_Main;
import com.zdh.alphathink.imh.R;
import org.json.JSONArray;
import org.json.JSONException;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText username,password;
    private TextView register;
    private FrameLayout back;
    private Button submit;
    private CheckBox checkBox;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkBox = (CheckBox) findViewById(R.id.checkbox_auto_login);
        username = (EditText) findViewById(R.id.editText_personal_login_username);
        password = (EditText) findViewById(R.id.editText_personal_login_password);
        register = (TextView) findViewById(R.id.textView_personal_register);
        back = (FrameLayout) findViewById(R.id.back_personal_login);
        submit = (Button) findViewById(R.id.button_personal_login_submit);
        register.setOnClickListener(this);
        submit.setOnClickListener(this);
        checkBox.setOnClickListener(this);
        initView();

    }

    private void initView(){
        //取得最后一次登录账号
        aCache = ACache.get(this);
        String user = aCache.getAsString("username");
        username.setText(user);
    }
    //查询bmob端是否存在数据
    public void login_in(View v){
        BmobQuery query = new BmobQuery("table_user");
        query.addWhereEqualTo("phoneNumber",username.getText().toString());
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if (e==null){
                        //查询存在
                    try {
                        if (password.getText().toString().equals(jsonArray.getJSONObject(0).getString("password"))){
                           //登陆成功
                            startActivity(new Intent(LoginActivity.this, Index_Main.class).
                                    putExtra("flag","3"));
                            toast("登陆成功！");
                            if (checkBox.isChecked()){
                                aCache.put("AutoLogin","true");
                                aCache.put("password",password.getText().toString());
                            }
                            aCache.put("username", username.getText().toString());
                            finish();
                        }else{
                            toast("密码错误！");
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                        toast("您输入的用户名不存在");
                    }
                }else{
                        toast("发生未知错误，程序员正在抢修！");
                }
            }
        });

    }
    private void toast(String msg){
        Toast.makeText(getApplicationContext(),""+msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_personal_login_submit:
                String name = username.getText().toString();
                String pass = password.getText().toString();
                if (name.equals("")||pass.equals("")){
                    toast("请检查您的输入是否有误！");
                }else{
                    //网络条件下查询
                    login_in(v);
                }
                break;
            case R.id.textView_personal_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        }

    }
}
