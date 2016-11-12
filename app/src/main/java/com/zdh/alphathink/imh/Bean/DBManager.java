package com.zdh.alphathink.imh.Bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Panda on 2016/9/13.
 * 建立在DBHelper至上，封装常用业务方法
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * add persons
     * @param persons
     */
    public void add(List<MyDate> persons) {
        db.beginTransaction();  //开始事务
        try {
            for (MyDate date : persons) {
                db.execSQL("INSERT INTO date VALUES (NULL,?,?,?,?)", new Object[]{date.id,date.name,date.color,date.time});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * update person's age
     * @param person
     */
    public void updateAge(MyDate person) {
        ContentValues cv = new ContentValues();
        cv.put("id",person.id);
        cv.put("age", person.name);
        cv.put("color",person.color);
        cv.put("time",person.time);
        db.update("date", cv, "id = ?", new String[]{person.id});
    }

    /**
     * delete old person
     * @param person
     */
    public void deleteOldPerson(MyDate person) {
        db.delete("date", "id= ?", new String[]{String.valueOf(person.id)});
    }

    /**
     * query all persons, return list
     * @return List<Person>
     */
    public List<MyDate> query() {
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
