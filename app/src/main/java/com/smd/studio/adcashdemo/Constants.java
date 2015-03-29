package com.smd.studio.adcashdemo;

/**
 * Created by Doychev on 29.3.2015 г.
 */
public interface Constants {

    public static final String DOWNLOAD_URL = "http://deals.ebay.com/feeds/xml";
    public static final int ITEM_LIMIT = 30;

    //DATABASE CONSTANTS
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "deal";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_WEB_LINK = "webLink";
    public static final String COLUMN_NAME_IMAGE_LINK = "imageLink";
}
