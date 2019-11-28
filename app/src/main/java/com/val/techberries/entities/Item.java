package com.val.techberries.entities;





public class Item {
    private String itemName;
    private int itemImage;
    private int id;
    private int cost;
    private String description;
    private String category;
    private String similarityTo;


    public Item() {
    }

    public Item(String itemName, int itemImage,int cost,String description) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.cost=cost;
        this.description=description;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSimilarityTo() {
        return similarityTo;
    }

    public void setSimilarityTo(String similarityTo) {
        this.similarityTo = similarityTo;
    }
}
