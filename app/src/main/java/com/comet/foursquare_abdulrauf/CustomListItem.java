package com.comet.foursquare_abdulrauf;

import android.graphics.Bitmap;

/**
 * Created by abdul on 11/10/15.
 */
public class CustomListItem {

    private String title;
    private String description;
    private int rating;
    private String place;
    private Bitmap bitmap;


    public CustomListItem(String title, String description, int rating, String place, Bitmap bitmap) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.place = place;
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
