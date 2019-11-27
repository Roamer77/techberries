package com.val.techberries.utils.dataBase.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.val.techberries.entities.Item;
import com.val.techberries.entities.UserCart;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(UserCart item);
    @Delete
    void delete(UserCart item);

    @Query("select * from usercart ")
    LiveData<List<UserCart>> getAllItemsInCart();
}
