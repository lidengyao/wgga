package com.bcxd.wgga.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lidengyao on 2017-02-17 0017.
 */

public class DBHelper extends SQLiteOpenHelper {
    // 数据库名称
    private static final String DB_NAME = "LDY.db";
    // 声明SQLite对象
    private SQLiteDatabase db;

    private static final String CWTable = "create table LdyTable(Key text ,Value text)";

    public DBHelper(Context mcontext) {
        super(mcontext, DB_NAME, null, 2);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        this.db = db;
        db.execSQL(CWTable);
    }

    /**
     * 插入数据
     */
    public boolean SetData(String Key, String Value) {
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.execSQL("delete from LdyTable where Key='" + Key + "'");
            db.execSQL("insert into LdyTable(Key,Value) values ('" + Key + "','"
                    + Value + "')");

            db.close();
            return true;
        } catch (Exception e) {
            db.close();
            return false;

        }
        finally {
            db.close();
        }
    }

    /*
     * 读取数据
     */
    public String GetValue(String Key) {
        String resultString = "";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query("LdyTable", null, null, null, null, null, null);
        for (int i = 0; i < c.getCount(); i++) {
            if (c.moveToFirst()) {
                c.move(i);
                String keyNameString = c.getString(0);
                String valueString = c.getString(1);

                if (keyNameString.equals(Key)) {
                    resultString = valueString;
                    break;
                }
            }

        }
        c.close();
//        db.close();
        return resultString;
    }

    // 关闭数据库
    public void close() {
        if (db != null)
            db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
