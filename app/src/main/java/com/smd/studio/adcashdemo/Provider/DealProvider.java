package com.smd.studio.adcashdemo.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.smd.studio.adcashdemo.Constants;
import com.smd.studio.adcashdemo.DBHelper;

/**
 * Created by Doychev on 29.3.2015 г..
 */
public class DealProvider extends ContentProvider {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {

        Context context = getContext();
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        if (database == null)
            return false;
        else
            return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(Constants.TABLE_NAME);
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
