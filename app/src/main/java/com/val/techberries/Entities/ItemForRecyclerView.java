package com.val.techberries.Entities;

public class ItemForRecyclerView {
    private String itemName;
    private int itemImage;

    public ItemForRecyclerView(String itemName, int itemImage) {
        this.itemName = itemName;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemImage() {
        return itemImage;
    }
}
