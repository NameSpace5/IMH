package com.zdh.alphathink.imh.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Panda on 2016/9/13.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "date.db";
    public static final String DB_TABLE_NAME = "info";
    private static final int DB_VERSION = 1;
    DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    //数据首次创建调用
    @Override
    public void onCreate(SQLiteDatabase db) {
         //建表
        db.execSQL("CREATE TABLE IF NOT EXISTS medical_info" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,box_id INTEGER, box_name VARCHAR, box_time VARCHAR, box_color INTEGER)");

    }
   //版本变化后调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE medical_info ADD COLUMN other TEXT");
    }
}
