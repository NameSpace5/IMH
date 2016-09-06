package com.zdh.alphathink.imh;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import com.zdh.alphathink.imh.fragment.BlueToothFragment;
import com.zdh.alphathink.imh.fragment.ChattingFragment;
import com.zdh.alphathink.imh.fragment.MoreFragment;
import com.zdh.alphathink.imh.fragment.NewsFragment;

public class Index extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mNews;
    private LinearLayout mBlueTooth;
    private LinearLayout mChatting;
    private LinearLayout mMore;
    private NewsFragment news;
    private BlueToothFragment bluetooh;
    private ChattingFragment chatting;
    private MoreFragment more;
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mNews = (LinearLayout) findViewById(R.id.mNews);
        mBlueTooth = (LinearLayout) findViewById(R.id.mBlueTooth);
        mChatting = (LinearLayout) findViewById(R.id.mChatting);
        mMore = (LinearLayout) findViewById(R.id.mMore);
        mNews.setOnClickListener(this);
        mBlueTooth.setOnClickListener(this);
        mChatting.setOnClickListener(this);
        mMore.setOnClickListener(this);
        //设置默认的FragMent
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        news = new NewsFragment();
        transaction.replace(R.id.content,news);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(v.getId()){
            case R.id.mNews:
                if (news == null){
                    news = new NewsFragment();
                }
                transaction.replace(R.id.content,news);
                break;
            case R.id.mBlueTooth:
                if (bluetooh == null){
                    bluetooh = new BlueToothFragment();
                }
                transaction.replace(R.id.content,bluetooh);
                break;
            case R.id.mChatting:
                if (chatting == null){
                    chatting = new ChattingFragment();
                }
                transaction.replace(R.id.content,chatting);
                break;
            case R.id.mMore:
                if (more == null){
                    more = new MoreFragment();
                }
                transaction.replace(R.id.content,more);
                break;
        }
        transaction.commit();
    }
}
