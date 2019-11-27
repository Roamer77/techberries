package com.val.techberries.entities;

public class Item {
    private String itemName;
    private int itemImage;
    private int id;
    private int cost;
    private  String description;


    public Item(String itemName, int itemImage) {
        this.itemName = itemName;
        this.itemImage = itemImage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemImage() {
        return itemImage;
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
}
