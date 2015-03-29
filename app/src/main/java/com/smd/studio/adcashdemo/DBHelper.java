package com.smd.studio.adcashdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Doychev on 29.3.2015 г.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_DB = " CREATE TABLE " + Constants.TABLE_NAME +
            " (" +
            "_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
            Constants.COLUMN_NAME_WEB_LINK + " TEXT NOT NULL, " +
            Constants.COLUMN_NAME_IMAGE_LINK + " TEXT NOT NULL " +
            " );";

    private static final String SQL_DELETE_DB = " DROP TABLE IF EXISTS " + Constants.TABLE_NAME;

    private static final String SQL_DELETE_RECORDS = " DELETE FROM " + Constants.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, Constants.TABLE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DB);
        onCreate(db);
    }

    public void cleanDatabase(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_RECORDS);
    }
}