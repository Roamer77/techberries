package com.val.techberries.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ItemToUserCart  {

    @PrimaryKey(autoGenerate = true)
    int id;

    int productID;
    int cost;
    String itemName;
    byte[] itemImage;
    String description;

    public ItemToUserCart(int cost, String itemName, byte[] itemImage, String description,int productID) {
        this.cost = cost;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.description = description;
        this.productID=productID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
