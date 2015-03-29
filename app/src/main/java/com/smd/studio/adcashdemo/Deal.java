package com.smd.studio.adcashdemo;

/**
 * Created by Doychev on 29.3.2015 г.
 */
public class Deal {

    private String title;
    private String webLink;
    private String imageLink;

    Deal(String title, String webLink, String imageLing) {
        this.title = title;
        this.webLink = webLink;
        this.imageLink = imageLing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
