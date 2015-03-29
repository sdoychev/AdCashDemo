package com.smd.studio.adcashdemo.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smd.studio.adcashdemo.AsyncTask.ImageDownloaderTask;
import com.smd.studio.adcashdemo.R;

public class MoreDetailsActivity extends ActionBarActivity {

    private String title, url, imgUrl;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        url = intent.getStringExtra("URL");
        imgUrl = intent.getStringExtra("IMG_URL");

        setContentView(R.layout.activity_more_details);

        TextView text = (TextView) findViewById(R.id.moreInfoTitle);
        text.setText(title);

        image = (ImageView) findViewById(R.id.moreInfoImg);
        new ImageDownloaderTask(image).execute(imgUrl);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
