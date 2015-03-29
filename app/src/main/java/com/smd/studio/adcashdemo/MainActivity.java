package com.smd.studio.adcashdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    Button downloadDealsBtn, showListBtn, exportDbBtn;
    ArrayList<Deal> deals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadDealsBtn = (Button) findViewById(R.id.dealsBtn);
        showListBtn = (Button) findViewById(R.id.listBtn);

        downloadDealsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadDeals();
                addDealsToDb();
            }
        });
    }

    private void downloadDeals() {
        DownloadTask dealsDownload = new DownloadTask();
        dealsDownload.execute(Constants.DOWNLOAD_URL);
        try {
            deals = dealsDownload.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void addDealsToDb() {
        DatabaseTask dbTask = new DatabaseTask(deals, this);
        dbTask.execute();
    }

    public void showList(View v) {
        Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
        startActivity(intent);
    }
}
