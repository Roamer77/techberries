package com.val.techberries.entities;

import android.graphics.Bitmap;

public class Advertising {
    long id;
    Bitmap image;

    public Advertising() {
    }

    public Advertising(long id, Bitmap image) {
        this.id = id;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
