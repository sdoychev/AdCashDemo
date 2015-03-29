package com.smd.studio.adcashdemo;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Doychev on 29.3.2015 г.
 */
public class DownloadTask extends AsyncTask<String, Integer, ArrayList<Deal>> {

    @Override
    protected ArrayList<Deal> doInBackground(String... params) {

        ArrayList<Deal> dealList = new ArrayList<>();
        int itemCount = 0;
        boolean titleInfo = false;
        boolean linkInfo = false;
        boolean picInfo = false;
        String titleInfoStr = "";
        String linkInfoStr = "";
        String picInfoStr = "";

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(new URI(params[0]));
            HttpResponse httpResponse = client.execute(httpGet);
            InputStream is = httpResponse.getEntity().getContent();

            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(is));

            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT && itemCount < Constants.ITEM_LIMIT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (xmlPullParser.getName().equals("PictureURL")) {
                        picInfo = true;
                    } else if (xmlPullParser.getName().equals("Title")) {
                        titleInfo = true;
                    } else if (xmlPullParser.getName().equals("DealURL")) {
                        linkInfo = true;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xmlPullParser.getName().equals("PictureURL") ||
                            xmlPullParser.getName().equals("Title") ||
                            xmlPullParser.getName().equals("DealURL")) {
                        titleInfo = linkInfo = picInfo = false;
                    } else if (xmlPullParser.getName().equals("Item")) {
                        Deal deal = new Deal(titleInfoStr, linkInfoStr, picInfoStr);
                        dealList.add(deal);
                        itemCount++;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (picInfo) {
                        picInfoStr = xmlPullParser.getText();
                    } else if (titleInfo) {
                        titleInfoStr = xmlPullParser.getText();
                    } else if (linkInfo) {
                        linkInfoStr = xmlPullParser.getText();
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return dealList;
    }
}
