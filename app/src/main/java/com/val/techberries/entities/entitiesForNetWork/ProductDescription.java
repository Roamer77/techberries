package com.val.techberries.entities.entitiesForNetWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDescription {
    @SerializedName("id")
    @Expose
    private  int id;

    @SerializedName("soleMaterial")
    @Expose
    private String soleMaterial;
    @SerializedName("soleHeight")
    @Expose
    private int  soleHeight;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("season")
    @Expose
    private String season;

    @SerializedName("cost")
    @Expose
    private int cost;

    public ProductDescription() {
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoleMaterial() {
        return soleMaterial;
    }

    public void setSoleMaterial(String soleMaterial) {
        this.soleMaterial = soleMaterial;
    }

    public int getSoleHeight() {
        return soleHeight;
    }

    public void setSoleHeight(int soleHeight) {
        this.soleHeight = soleHeight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


    @Override
    public String toString() {
        return "ProductDescription{" +
                "id=" + id +
                ", soleMaterial='" + soleMaterial + '\'' +
                ", soleHeight=" + soleHeight +
                ", sex='" + sex + '\'' +
                ", season='" + season + '\'' +
                ", cost=" + cost +
                '}';
    }
}
