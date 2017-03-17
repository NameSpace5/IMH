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

    public void insert(PersonDto tablet) {
        db.beginTransaction();  //开始事务
        try {
                db.execSQL("INSERT INTO tablet VALUES (NULL,?,?,?)", new Object[]{tablet.getName(),
                        tablet.getDescription(),tablet.getRemark()});
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    public void update(PersonDto tablet) {
        ContentValues cv = new ContentValues();
        cv.put("name", tablet.getName());
        cv.put("description",tablet.getDescription());
        cv.put("remark",tablet.getRemark());
        db.update("tablet", cv, "id = ?", new String[]{String.valueOf(tablet.get_id())});
    }

    public void delete(PersonDto tablet) {
        db.delete("tablet", "id= ?", new String[]{String.valueOf(tablet.get_id())});
    }

    public List<PersonDto> query() {
        ArrayList<PersonDto> results = new ArrayList<PersonDto>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            PersonDto tablet = new PersonDto();
            tablet.set_id(c.getInt(c.getColumnIndex("_id")));
            tablet.setName(c.getString(c.getColumnIndex("name")));
            tablet.setDescription(c.getString(c.getColumnIndex("description")));
            tablet.setRemark(c.getString(c.getColumnIndex("remark")));
            results.add(tablet);
        }
        c.close();
        return results;
    }

    private Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM tablet", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}