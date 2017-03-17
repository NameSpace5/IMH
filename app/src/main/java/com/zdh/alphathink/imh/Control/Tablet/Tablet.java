package com.zdh.alphathink.imh.Control.Tablet;

import android.app.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zdh.alphathink.imh.Bean.PersonDto;
import com.zdh.alphathink.imh.Control.MyDialog;
import com.zdh.alphathink.imh.CustomWidgets.CharacterParser;
import com.zdh.alphathink.imh.CustomWidgets.SideBar;
import com.zdh.alphathink.imh.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Tablet extends Activity implements SideBar.OnTouchingLetterChangedListener, TextWatcher {
    private SideBar mSideBar;
    private TextView mDialog;
    private ListView mListView;
    private EditText bell_name,bell_desc,bell_remark;
    private DBManager dbManager;
    private ImageButton ad,bell_back;
    private EditText mSearchInput;
    private CharacterParser characterParser;// 汉字转拼音
    private PinyinComparator pinyinComparator;// 根据拼音来排列ListView里面的数据类
    private List<PersonDto> sortDataList = new ArrayList<PersonDto>();
    private SchoolFriendMemberListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet);
        mListView = (ListView) findViewById(R.id.school_friend_member);
        mSideBar = (SideBar) findViewById(R.id.school_friend_sidrbar);
        mDialog = (TextView) findViewById(R.id.school_friend_dialog);
        mSearchInput = (EditText) findViewById(R.id.school_friend_member_search_input);
        bell_back  = (ImageButton) findViewById(R.id.bell_back);
        bell_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ad = (ImageButton) findViewById(R.id.ad);
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        mSideBar.setTextView(mDialog);
        mSideBar.setOnTouchingLetterChangedListener(this);
        initData();
    }

    public void add(){
        MyDialog.Builder builder = new MyDialog.Builder(Tablet.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.add_medical, (ViewGroup) findViewById(dialog));
        bell_name = (EditText) layout.findViewById(R.id.bell_add);
        bell_desc = (EditText) layout.findViewById(R.id.bell_des);
        bell_remark = (EditText) layout.findViewById(R.id.bell_remark);
        builder.setContentView(layout);
        builder.setTitle("添加药品");
        builder.setPositiveButton("确认添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //药品添加到数据库，并改变notify
                if (!bell_name.getText().toString().equals("")){
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("name",bell_name.getText().toString());
                    map.put("description",bell_desc.getText().toString());
                    map.put("remark", bell_remark.getText().toString());
                    dbManager.insert_BellInfo(map);
                    Toast.makeText(Tablet.this,"已存入数据库！",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    initData();
                    dbManager.closeDB();
                }else{
                    Toast.makeText(Tablet.this,"药品名称不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.creat().show();
    }
    /**
     * 初始化数据
     */

    private void initData() {
// 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        dbManager = new DBManager(this);
        sortDataList = dbManager.queryBellInfo();
        fillData(sortDataList);
// 根据a-z进行排序源数据
        Collections.sort(sortDataList, pinyinComparator);
        mAdapter = new SchoolFriendMemberListAdapter(this, sortDataList);
        mListView.setAdapter(mAdapter);
        mSearchInput.addTextChangedListener(this);
    }
    @Override
    public void onTouchingLetterChanged(String s) {
        int position = 0;
// 该字母首次出现的位置
        if(mAdapter != null){
            position = mAdapter.getPositionForSection(s.charAt(0));
        }
        if (position != -1) {
            mListView.setSelection(position);
        }
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
        filterData(s.toString(), sortDataList);
    }
    @Override
    public void afterTextChanged(Editable s) {
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr, List<PersonDto> list) {
        List<PersonDto> filterDateList = new ArrayList<PersonDto>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = list;
        } else {
            filterDateList.clear();
            for (PersonDto sortModel : list) {
                String name = sortModel.getName();
                String suoxie = sortModel.getSuoxie();
                if (name.indexOf(filterStr.toString()) != -1 || suoxie.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }
// 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        mAdapter.updateListView(filterDateList);
    }
    /**
     * 填充数据
     *
     * @param list
     */
    private void fillData(List<PersonDto> list) {
        for (PersonDto cUserInfoDto : list) {
            if (cUserInfoDto != null && cUserInfoDto.getName() != null) {
                String pinyin = characterParser.getSelling(cUserInfoDto.getName());
                String suoxie = CharacterParser.getFirstSpell(cUserInfoDto.getName());
                cUserInfoDto.setSuoxie(suoxie);
                String sortString = pinyin.substring(0, 1).toUpperCase();
                if ("1".equals(cUserInfoDto.getUtype())) {// 判断是否是管理员
                    cUserInfoDto.setSortLetters("☆");
                } else if (sortString.matches("[A-Z]")) {// 正则表达式，判断首字母是否是英文字母
                    cUserInfoDto.setSortLetters(sortString);
                } else {
                    cUserInfoDto.setSortLetters("#");
                }
            }
        }
    }
}
