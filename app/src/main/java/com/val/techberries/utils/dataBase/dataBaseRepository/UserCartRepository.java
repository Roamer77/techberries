package com.val.techberries.utils.dataBase.dataBaseRepository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.val.techberries.entities.Item;
import com.val.techberries.entities.UserCart;
import com.val.techberries.utils.dataBase.DAO.ItemDao;
import com.val.techberries.utils.dataBase.MyDataBase;

import java.util.List;

public class UserCartRepository {

    private ItemDao itemDao;
    private LiveData<List<UserCart>> allItems;

    public UserCartRepository(Application application) {
        MyDataBase myDataBase=MyDataBase.getInstance(application);
        itemDao= myDataBase.itemDao();
        allItems=itemDao.getAllItemsInCart();
    }
}
