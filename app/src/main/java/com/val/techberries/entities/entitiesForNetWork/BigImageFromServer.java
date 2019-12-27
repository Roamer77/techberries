package com.val.techberries.entities.entitiesForNetWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BigImageFromServer {

    @Expose
    @SerializedName("big0")
    private String bigImage1;

    @Expose
    @SerializedName("big1")
    private String bigImage2;

    @Expose
    @SerializedName("big2")
    private String bigImage3;


    public BigImageFromServer() {
    }

    public String getBigImage1() {
        return bigImage1;
    }

    public void setBigImage1(String bigImage1) {
        this.bigImage1 = bigImage1;
    }

    public String getBigImage2() {
        return bigImage2;
    }

    public void setBigImage2(String bigImage2) {
        this.bigImage2 = bigImage2;
    }

    public String getBigImage3() {
        return bigImage3;
    }

    public void setBigImage3(String bigImage3) {
        this.bigImage3 = bigImage3;
    }



}
