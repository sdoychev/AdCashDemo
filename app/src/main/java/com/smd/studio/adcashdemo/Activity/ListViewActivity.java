package com.smd.studio.adcashdemo.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smd.studio.adcashdemo.Adapter.DealListAdapter;
import com.smd.studio.adcashdemo.Constants;
import com.smd.studio.adcashdemo.Model.Deal;
import com.smd.studio.adcashdemo.R;

import java.util.ArrayList;

public class ListViewActivity extends ActionBarActivity {

    ArrayList<Deal> dealsData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Cursor cursor = getContentResolver().query(Constants.CONTENT_URI, null, null, null, null);
        if (!cursor.moveToFirst()) {
            return;
        } else {
            do {
                String dealTitle = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_TITLE));
                String dealWebLink = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_WEB_LINK));
                String dealImageLink = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_IMAGE_LINK));
                Deal deal = new Deal(dealTitle, dealWebLink, dealImageLink);
                dealsData.add(deal);
            } while (cursor.moveToNext());
        }
        final ListView dealsListView = (ListView) findViewById(R.id.dealsListView);
        dealsListView.setAdapter(new DealListAdapter(this, dealsData));

        dealsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Deal deal = (Deal) dealsData.get(position);
                Intent moreDetailsIntent = new Intent(ListViewActivity.this, MoreDetailsActivity.class);
                moreDetailsIntent.putExtra("TITLE", deal.getTitle());
                moreDetailsIntent.putExtra("URL", deal.getWebLink());
                moreDetailsIntent.putExtra("IMG_URL", deal.getImageLink());
                startActivity(moreDetailsIntent);
            }
        });
    }
}
