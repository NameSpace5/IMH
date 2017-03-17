package com.zdh.alphathink.imh.Personal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.zdh.alphathink.imh.R;
import org.json.JSONException;
import org.json.JSONObject;

public class Personal_thought extends AppCompatActivity {

    private Button button_submit;
    private EditText editText_thought,editText_phoneNumber;
    private RatingBar ratingBar;
    private FrameLayout imgBtn_back;
    private TextView textView_max_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_thought);

        imgBtn_back = (FrameLayout) findViewById(R.id.imgBtn_back_thought);
        button_submit = (Button) findViewById(R.id.button_personal_submit);
        editText_phoneNumber = (EditText) findViewById(R.id.editText_personal_phoneNumber);
        editText_thought = (EditText) findViewById(R.id.editText_personal_thought);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar_personal_thought);
        textView_max_input = (TextView) findViewById(R.id.textView_personal_thought);
        imgBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editText_thought.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int number = 200 - editText_thought.getText().toString().length();
                textView_max_input.setText("您还可以输入"+number+"个字");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        button_submit.setOnClickListener(new ButtonSubmitImpl());
    }
    public class ButtonSubmitImpl implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (editText_thought.getText().toString().equals("")){
                Toast.makeText(Personal_thought.this,"请输入有效的意见或建议！",Toast.LENGTH_SHORT).show();
            }
            else if ((!editText_phoneNumber.getText().toString().equals(""))&&
                    editText_phoneNumber.getText().toString().length()<11){
                Toast.makeText(Personal_thought.this,"请输入有效的手机号码！",Toast.LENGTH_SHORT).show();
            }else{
                //一切正确，进行信息获取提交
                String content = editText_thought.getText().toString();//意见
                String phoneNumber = editText_phoneNumber.getText().toString();
                float starsNumber = ratingBar.getNumStars();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("Content",content);
                    jsonObject.put("PhoneNumber",phoneNumber);
                    jsonObject.put("StarsNumber",starsNumber);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //发送数据到指定服务器


                //成功
                AlertDialog.Builder builder = new AlertDialog.Builder(Personal_thought.this);
                builder.setTitle("反馈成功")
                        .setMessage("非常感谢您的反馈与建议，您的支持是我们进步的动力！")
                        .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
            }
        }
    }
}
