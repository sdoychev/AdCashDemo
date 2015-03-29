package com.smd.studio.adcashdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Doychev on 29.3.2015 г.
 */
public class DatabaseTask extends AsyncTask<String, Void, Void> {

    ArrayList<Deal> deals;
    Context context;

    DatabaseTask(ArrayList<Deal> deals, Context context) {
        this.deals = deals;
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.cleanDatabase(db);
        for (Deal deal : deals) {
            addDealToDB(deal, db);
        }
        return null;
    }

    private void addDealToDB(Deal deal, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME_TITLE, deal.getTitle());
        values.put(Constants.COLUMN_NAME_WEB_LINK, deal.getWebLink());
        values.put(Constants.COLUMN_NAME_IMAGE_LINK, deal.getImageLink());
        database.insert(Constants.TABLE_NAME, null, values);
    }
}
