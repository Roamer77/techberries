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
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4),new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4),
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4),
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4));
        return data;
    }

    public  List<Item> getDataForHomePageFirstRecyclerView(){
        List<Item> testData = new ArrayList<>();
        Collections.addAll(testData, new Item("Куртка 1", R.drawable.product_img0),
                new Item("Куртка 2", R.drawable.product_img1),
                new Item("Куртка 3", R.drawable.product_img2),
                new Item("Куртка 1", R.drawable.product_img0),
                new Item("Куртка 2", R.drawable.product_img1),
                new Item("Куртка 3", R.drawable.product_img2));

        return testData;
    }
    //реклма
    public  List<Item> getDataForHomePageAdvertisementRecyclerView(){
        List<Item> testData2 = new ArrayList<>();
        Collections.addAll(testData2, new Item("", R.drawable.prod_place1),
                new Item("", R.drawable.prod_place2),
                new Item("", R.drawable.prod_place3),
                new Item("",R.drawable.ked3));

        return testData2;
    }
    public  List<Item>  getDataForHomePageTherdRecyclerView(){
        List<Item> testData3 = new ArrayList<>();
        Collections.addAll(testData3,
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4));
        return  testData3;
    }
}
