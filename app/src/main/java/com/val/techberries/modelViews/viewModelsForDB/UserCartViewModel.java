package com.val.techberries.modelViews.viewModelsForDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.val.techberries.entities.ItemToUserCart;
import com.val.techberries.utils.dataBase.dataBaseRepository.UserCartRepository;

import java.util.List;

public class UserCartViewModel extends AndroidViewModel {

    private UserCartRepository userCart;
    private LiveData<List<ItemToUserCart>> allItems;

    public UserCartViewModel(@NonNull Application application) {
        super(application);
        userCart = new UserCartRepository(application);
        allItems=userCart.getAllItems();
    }

    public void insert(ItemToUserCart item) {
        userCart.insertItemToCart(item);
    }
    public   void delete(ItemToUserCart item){
        userCart.deleteItemFromCart(item);
    }

    public LiveData<List<ItemToUserCart>> getAllItems() {
        return allItems;
    }
}
