package com.val.techberries.entities;


import android.graphics.Bitmap;

public class Item  {
    private String itemName;
    private Bitmap itemImage;
    private int id;
    private int cost;
    private String description;
    private int category;
    private String similarityTo;


    public Item() {
    }

    public Item(String itemName, Bitmap itemImage,int cost,String description) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.cost=cost;
        this.description=description;
    }
    public Item(String itemName, Bitmap itemImage,int cost,String description,int categoryId) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.cost=cost;
        this.description=description;
        this.category=categoryId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getItemImage() {
        return itemImage;
    }

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getSimilarityTo() {
        return similarityTo;
    }

    public void setSimilarityTo(String similarityTo) {
        this.similarityTo = similarityTo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemImage=" + itemImage +
                ", id=" + id +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", similarityTo='" + similarityTo + '\'' +
                '}';
    }
}
