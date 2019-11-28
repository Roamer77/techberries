package com.val.techberries.repositories;

import androidx.lifecycle.LiveData;

import com.val.techberries.R;
import com.val.techberries.entities.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainRepository {

    public ArrayList<Item> getProductsByVategory(){
        ArrayList<Item> data = new ArrayList<>();
        Collections.addAll(data,
                new Item("Кеды 1", R.drawable.ked1,14,"fjrjfsd"),
                new Item("Кеды 2", R.drawable.ked2,123,"werfoswo"),
                new Item("Кеды 3", R.drawable.ked3,123,"werfoswo"),
                new Item("Кеды 4", R.drawable.ked4,123,"werfoswo"),
                new Item("Кеды 1", R.drawable.ked1,123,"werfoswo"),
                new Item("Кеды 2", R.drawable.ked2,123,"werfoswo"),
                new Item("Кеды 3", R.drawable.ked3,123,"werfoswo"),
                new Item("Кеды 4", R.drawable.ked4,123,"werfoswo"),
                new Item("Кеды 1", R.drawable.ked1,123,"werfoswo"),
                new Item("Кеды 2", R.drawable.ked2,123,"werfoswo"),
                new Item("Кеды 3", R.drawable.ked3,123,"werfoswo"),
                new Item("Кеды 4", R.drawable.ked4,123,"werfoswo"),
                new Item("Кеды 1", R.drawable.ked1,123,"werfoswo"),
                new Item("Кеды 2", R.drawable.ked2,123,"werfoswo"),
                new Item("Кеды 3", R.drawable.ked3,123,"werfoswo"),
                new Item("Кеды 4", R.drawable.ked4,123,"werfoswo"));
        return data;
    }

    public  List<Item> getDataForHomePageFirstRecyclerView(){
        List<Item> testData = new ArrayList<>();
        Collections.addAll(testData, new Item("Куртка 1", R.drawable.product_img0,12,"qwqerrt"),
                new Item("Куртка 2", R.drawable.product_img1,13,"qrwsdfew1123"),
                new Item("Куртка 3", R.drawable.product_img2,14,"qdfhfgh"),
                new Item("Куртка 1", R.drawable.product_img0,15,"btrydf"),
                new Item("Куртка 2", R.drawable.product_img1,16,"|ewrwerq"),
                new Item("Куртка 3", R.drawable.product_img2,17,"qwto2"));

        return testData;
    }
    //реклма
    public  List<Item> getDataForHomePageAdvertisementRecyclerView(){
        List<Item> testData2 = new ArrayList<>();
        Collections.addAll(testData2, new Item("", R.drawable.prod_place1,112,"qtyfdfs"),
                new Item("", R.drawable.prod_place2,345,"rwerwer"),
                new Item("", R.drawable.prod_place3,12,"qwe32tf"),
                new Item("",R.drawable.ked3,14,"qwr23twe"));

        return testData2;
    }
    public  List<Item>  getDataForHomePageTherdRecyclerView(){
        List<Item> testData3 = new ArrayList<>();
        Collections.addAll(testData3,
                new Item("Кеды 1", R.drawable.ked1,123,"erewrrwer"),
                new Item("Кеды 2", R.drawable.ked2,5435,"rwerwerwe"),
                new Item("Кеды 3", R.drawable.ked3,231,"ewrwer"),
                new Item("Кеды 4", R.drawable.ked4,453,"qwrwerw"));
        return  testData3;
    }
}
