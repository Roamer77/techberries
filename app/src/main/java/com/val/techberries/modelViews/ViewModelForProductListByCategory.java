package com.val.techberries.modelViews;

import androidx.lifecycle.ViewModel;

import com.val.techberries.entities.Item;
import com.val.techberries.repositories.MainRepository;

import java.util.ArrayList;

public class ViewModelForProductListByCategory extends ViewModel {
    private MainRepository mainRepository;

    public ViewModelForProductListByCategory() {
        this.mainRepository = new MainRepository();
    }

    public ArrayList<Item> getDataForProductListByCategory(){
       return mainRepository.getProductsByVategory();
    }
}
