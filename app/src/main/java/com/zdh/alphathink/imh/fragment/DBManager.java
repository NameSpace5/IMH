package com.zdh.alphathink.imh.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zdh.alphathink.imh.Bean.DBHelper;
import com.zdh.alphathink.imh.Bean.Medical_info;
import com.zdh.alphathink.imh.Bean.MyDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Panda on 2016/9/13.
 * 建立在DBHelper至上，封装常用业务方法
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DBManager(Context context){
        this.context = context;
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
    }

    /**
     * add persons
     * @param info
     */
    public void insertInfo(List<Medical_info> info) {
        db = helper.getWritableDatabase();
        db.beginTransaction();  //开始事务
        try {
            for (Medical_info date : info) {
                db.execSQL("INSERT INTO medical_info VALUES (NULL,?,?,?,?)", new Object[]{date.id,date.name,date.color,date.time});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * update person's age
     * @param  person
     */
    public void updateInfo(Medical_info person) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", person.name);
        cv.put("time",person.time);
        cv.put("color",person.color);
        db.update("medical_info", cv, "id = ?", new String[]{String.valueOf(person.id)});
    }

    /**
     * delete old person
     * @param person
     */
    public void deleteItem(Medical_info person) {
        db = helper.getWritableDatabase();
        db.delete("medical_info", "id= ?", new String[]{String.valueOf(person.id)});
    }

    /**
     * query all persons, return list
     * @return List<Person>
     */
    public List<MyDate> query() {
        db = helper.getWritableDatabase();
        ArrayList<MyDate> persons = new ArrayList<MyDate>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            MyDate person = new MyDate();
            person._id = c.getInt(c.getColumnIndex("_id"));
            person.name = c.getString(c.getColumnIndex("name"));
            person.id = c.getString(c.getColumnIndex("id"));
            person.color = c.getInt(c.getColumnIndex("color"));
            person.time = c.getString(c.getColumnIndex("time"));
            persons.add(person);
        }
        c.close();
        return persons;
    }

    /**
     * query all persons, return cursor
     * @return  Cursor
     */
    public Cursor queryTheCursor() {
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM date", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
