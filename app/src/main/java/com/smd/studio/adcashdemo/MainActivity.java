package com.smd.studio.adcashdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        exportDbBtn = (Button) findViewById(R.id.exportDbBtn);

        downloadDealsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadDeals();
            }
        });
    }

    private void downloadDeals() {

        DownloadTask dealsDownload = new DownloadTask();
        dealsDownload.execute("http://deals.ebay.com/feeds/xml");
        try {
            deals = dealsDownload.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int i = 0;
        for (Deal deal : deals) {
            i++;
            Log.e("DEAL", "" + i);
        }
    }

    public void showList(View v) {
        Log.e("Test", "show list works");
    }

    public void exportDb(View v) {
        Log.e("Test", "export db works");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
