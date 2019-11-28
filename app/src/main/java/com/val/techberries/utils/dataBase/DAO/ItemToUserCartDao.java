package com.val.techberries.utils.dataBase.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.val.techberries.entities.ItemToUserCart;

import java.util.List;

@Dao
public interface ItemToUserCartDao {

    @Insert
    void insert(ItemToUserCart item);
    @Delete
    void delete(ItemToUserCart item);

    @Query("select * from ItemToUserCart ")
    LiveData<List<ItemToUserCart>> getAllItemsInCart();
}
