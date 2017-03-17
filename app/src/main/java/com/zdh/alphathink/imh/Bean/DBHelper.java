package com.zdh.alphathink.imh.Bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "tablet.db";
    private static final int DB_VERSION = 1;
    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    //数据首次创建调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        db.execSQL("CREATE TABLE IF NOT EXISTS tablet" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING, description STRING, remark STRING)");

    }
    //版本变化后调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}