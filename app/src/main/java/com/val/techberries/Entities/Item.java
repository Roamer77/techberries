package com.val.techberries.Entities;

public class Item {
    private String itemName;
    private int itemImage;

    private int cost;
    private  String description;
    public Item(String itemName, int itemImage) {
        this.itemName = itemName;
        this.itemImage = itemImage;
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
