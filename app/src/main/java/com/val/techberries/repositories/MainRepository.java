package com.val.techberries.repositories;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.val.techberries.R;
import com.val.techberries.entities.DTO.ProductEntityFromServer;
import com.val.techberries.entities.Item;
import com.val.techberries.entities.entitiesForNetWork.ProductDescription;
import com.val.techberries.interfacies.MyCallBack;
import com.val.techberries.interfacies.MyCallBackToRepo;
import com.val.techberries.interfacies.MyCallMackForProdDescription;
import com.val.techberries.utils.DbBitMapUtility.DbBitmapUtility;
import com.val.techberries.utils.ImageConvertar.ConvertImageFromBase64;
import com.val.techberries.utils.netWork.RequestAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRepository {


    public void getProductsByVategory(int categoryId,MyCallBackToRepo<Item> repoCallBack){
        new GetImagesAndNames(categoryId).execute(repoCallBack);
    }

    public  void getDataForHomePageFirstRecyclerView(MyCallBackToRepo<Item> repoCallBack){
        new GetImagesAndNames(1).execute(repoCallBack);
    }

    public  void getSimilarProducts(int categoryId,MyCallBackToRepo<Item> repoCallBack){
        new GetImagesAndNames(categoryId).execute(repoCallBack);
    }

    //реклма
    public  List<Item> getDataForHomePageAdvertisementRecyclerView(){
        List<Item> testData2 = new ArrayList<>();
        DbBitmapUtility dbBitmapUtility=new DbBitmapUtility();

        Collections.addAll(testData2, new Item("", BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.prod_place1),112,"qtyfdfs"),
                new Item("",BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.prod_place2),345,"rwerwer"),
                new Item("", BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.prod_place3),12,"qwe32tf"),
                new Item("",BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.ked3),14,"qwr23twe"));

        return testData2;
    }

    public  void getBigImagesForCurrentProduct(String name,MyCallBackToRepo<Bitmap> repoCallBack){
                new GetBigImagesFromServer(name).execute(repoCallBack);
    }

    public  void getDataForHomePageTherdRecyclerView(MyCallBackToRepo<Item> repoCallBack){

        new GetImagesAndNames(2).execute(repoCallBack);


    }

    public void getDescriptionAboutProduct(String name,MyCallBackToRepo<ProductDescription> repoCallBack){

        new GetDescriptionOfProduct(name).execute(repoCallBack);
    }


    private class GetImagesAndNames extends AsyncTask<MyCallBackToRepo<Item>,Void,List<Item>>{
        RequestAPI requestAPI=new RequestAPI();
        List<Item> productEntities=new ArrayList<>();
        private int  categoryId;

        public GetImagesAndNames(int categoryId) {
            this.categoryId = categoryId;
        }

        @Override
        protected List<Item> doInBackground(MyCallBackToRepo... voids) {


            requestAPI.doPostRequestForProductsSmallImagesByCategory(categoryId, new MyCallBack() {
                @Override
                public void onSuccess(Map nameImagesData) {

                    ConvertImageFromBase64 convertImageFromBase64=new ConvertImageFromBase64();



                    Object[] names=nameImagesData.keySet().toArray();
                    Object[] images=nameImagesData.values().toArray();

                    for (int i=0;i<nameImagesData.size();i++){
                        Item tmpItem=new Item((String) names[i],convertImageFromBase64.convertFromBase64toImage((String) images[i]),124,"myDiscription");
                        productEntities.add(tmpItem);
                    }

                    voids[0].onOk(productEntities);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

            return productEntities;
        }

    }




    private class GetBigImagesFromServer extends  AsyncTask<MyCallBackToRepo<Bitmap>,Void,Void>{
        RequestAPI requestAPI=new RequestAPI();
        List<Bitmap> bigImages=new ArrayList<>();

        private String productName;

        public GetBigImagesFromServer(String productName) {
            this.productName = productName;
        }

        @Override
        protected Void doInBackground(MyCallBackToRepo<Bitmap>... args) {
            requestAPI.doPostRequestForProductBigImages(productName, new MyCallBack() {
                @Override
                public void onSuccess(Map nameImagesData) {
                    ConvertImageFromBase64 convertImageFromBase64=new ConvertImageFromBase64();

                    Object[] images=nameImagesData.values().toArray();
                    for(int i=0;i<images.length;i++){
                        bigImages.add(convertImageFromBase64.convertFromBase64toImage((String) images[i]));
                    }
                    args[0].onOk(bigImages);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });


            return null;
        }
    }

    private  class GetDescriptionOfProduct extends AsyncTask<MyCallBackToRepo<ProductDescription>,Void,Void>{
        RequestAPI requestAPI=new RequestAPI();

        private  String productName;

        public GetDescriptionOfProduct(String productName) {
            this.productName = productName;
        }

        @Override
        protected Void doInBackground(MyCallBackToRepo<ProductDescription>... args) {
            requestAPI.doGetRequestForProductDescriptionByName(productName, new MyCallMackForProdDescription() {
                @Override
                public void onSuccess(ProductDescription description) {
                    // что бы не писать отдельный интерфейс запихну просто в лист
                    List<ProductDescription> tmpList=new ArrayList<>();
                    tmpList.add(description);

                    args[0].onOk(tmpList);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
            return null;
        }
    }

}
