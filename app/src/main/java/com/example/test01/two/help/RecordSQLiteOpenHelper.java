package com.example.test01.two.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {
    private static String name="temp.db";
    private static Integer version=1;
    public RecordSQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    public RecordSQLiteOpenHelper(Context context, String s) {
        super(context, s+".db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
