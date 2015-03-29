package com.smd.studio.adcashdemo.Activity;

import android.content.Intent;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smd.studio.adcashdemo.AsyncTask.ImageDownloaderTask;
import com.smd.studio.adcashdemo.R;

public class MoreDetailsActivity extends ActionBarActivity {

    private String title, url, imgUrl;
    private ImageView image;
    private Matrix matrix = new Matrix();
    private float scale = 3f;
    private ScaleGestureDetector SGD;

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
        SGD = new ScaleGestureDetector(this, new ScaleListener());
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        SGD.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            image.setImageMatrix(matrix);
            return true;
        }
    }
}
