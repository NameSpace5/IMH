package com.zdh.alphathink.imh.Bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.zdh.alphathink.imh.News.NewsActivity;

/**
 * Created by Panda on 2016/9/13.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "date.db";
    public static final String DB_TABLE_NAME = "info";
    private static final int DB_VERSION = 1;
    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    //数据首次创建调用
    @Override
    public void onCreate(SQLiteDatabase db) {
         //建表
        db.execSQL("CREATE TABLE IF NOT EXISTS info" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age INTEGER, website STRING,weibo STRING)");

    }
   //版本变化后调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
