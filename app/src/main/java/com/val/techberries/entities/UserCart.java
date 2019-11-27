package com.val.techberries.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserCart {

    @PrimaryKey(autoGenerate = true)
    int id;

    Item item;

    public UserCart(Item item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
