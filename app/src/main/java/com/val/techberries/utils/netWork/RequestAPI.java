package com.val.techberries.utils.netWork;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.val.techberries.entities.entitiesForNetWork.BigImageFromServer;
import com.val.techberries.entities.entitiesForNetWork.ProductDescription;
import com.val.techberries.interfacies.MyCallBack;
import com.val.techberries.interfacies.MyCallMackForProdDescription;
import com.val.techberries.utils.ImageConvertar.ConvertImageFromBase64;
import com.val.techberries.utils.netWork.netInterfaces.RequestApiInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestAPI {
    final String baseURL = "http://192.168.176.17:8080/productInfo/";
    final String imageBaseUrl = "http://192.168.176.17:8080/image/";

    private HttpLoggingInterceptor interceptor;
    private OkHttpClient.Builder client;

    //универсальный обект который будет содержать в себе инфу из реквеста
    private Object requestData;

    public RequestAPI() {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor);

    }

    public void doPostRequestForProductBigImages(String productName, MyCallBack myCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(imageBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()).build();
        RequestApiInterface requestApiInterface = retrofit.create(RequestApiInterface.class);

        Call<BigImageFromServer> imageFromServerCall = requestApiInterface.getImagesFroProduct(productName);

        imageFromServerCall.enqueue(new Callback<BigImageFromServer>() {
            @Override
            public void onResponse(Call<BigImageFromServer> call, Response<BigImageFromServer> response) {
                if (!response.isSuccessful()) {
                    Log.e("MyTag", "Ошибка " + response.code());
                }
                BigImageFromServer imageFromServer = response.body();
                String res = "";
                try {
                    int lenForBig1 = imageFromServer.getBigImage1().length();
                    int lenForBig2 = imageFromServer.getBigImage2().length();
                    int lenForBig3 = imageFromServer.getBigImage3().length();
                    Log.e("MyTag", "Длинна 1= " + lenForBig1 + "Длинна 2= " + lenForBig2 + " Длинна 3= " + lenForBig3);

                    Map<String, String> tmpData = new HashMap();
                    tmpData.put("big1", imageFromServer.getBigImage1());
                    tmpData.put("big2", imageFromServer.getBigImage2());
                    tmpData.put("big3", imageFromServer.getBigImage3());

                    if (myCallBack != null) {
                        myCallBack.onSuccess(tmpData);
                    }
                } catch (NullPointerException e) {
                    Log.e("MyTag", "Ошибка в разборе объекта " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BigImageFromServer> call, Throwable t) {

            }
        });
    }


    public void doGetRequestForProductDescriptionByName(String productName, MyCallMackForProdDescription myCallBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()).build();
        RequestApiInterface requestApiInterface = retrofit.create(RequestApiInterface.class);

        Call<ProductDescription> productDescriptionCall = requestApiInterface.getProductDescriptionByName(productName);

        productDescriptionCall.enqueue(new Callback<ProductDescription>() {
            @Override
            public void onResponse(@NonNull Call<ProductDescription> call, @NonNull Response<ProductDescription> response) {
                if (!response.isSuccessful()) {
                    Log.e("MyTag", "Ошибка " + response.code());
                }

                ProductDescription productDescription = response.body();
                String res = "";
                try {
                    //сетаем в обект инфу о продукте

                    if (myCallBack != null) {
                        myCallBack.onSuccess(productDescription);
                    }
                    //
                } catch (NullPointerException e) {
                    Log.e("MyTag", "Ошибка при разборе объекта " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ProductDescription> call, Throwable t) {

            }
        });
    }

    public void doPostRequestForProductsSmallImagesByCategory(int categoryId, MyCallBack myCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(imageBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()).build();
        RequestApiInterface requestApiInterface = retrofit.create(RequestApiInterface.class);

        Call<ResponseBody> getSmallImages = requestApiInterface.getSmallImages(categoryId);

        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Log.e("MyTag", "Ошибка " + response.code());
                }
                Gson gson = new Gson();
                String resData = "";
                try {
                    ResponseBody responseBody = response.body();
                    resData = responseBody.string();
                    Map images = gson.fromJson(resData, Map.class);
                    if (myCallBack != null) {
                        myCallBack.onSuccess(images);
                    }
                    Log.e("MyTag", "Small images key set" + images.keySet());

                } catch (IOException e) {
                    Log.e("MyTag", "Ошибка при разборе объекта " + e.getMessage());
                } catch (NullPointerException e) {
                    Log.e("MyTag", "Ошибка при разборе объекта " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (myCallBack != null) {
                    myCallBack.onError(t);
                }
            }
        };

        getSmallImages.enqueue(callback);

    }

    public void doGetRequestForListOfSmallImagesByName(String name, MyCallBack myCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(imageBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()).build();
        RequestApiInterface requestApiInterface = retrofit.create(RequestApiInterface.class);

        Call<ResponseBody> getSmallImages = requestApiInterface.getSmallImagesByName(name);
        getSmallImages.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (!response.isSuccessful()) {
                    Log.e("MyTag", "Ошибка " + response.code());
                }
                Gson gson = new Gson();
                String resData = "";
                try {
                    ResponseBody responseBody = response.body();
                    resData = responseBody.string();
                    Map images = gson.fromJson(resData, Map.class);
                    if (myCallBack != null) {
                        myCallBack.onSuccess(images);
                    }
                    Log.e("MyTag", "(byName)Small images key set" + images.keySet());

                } catch (IOException e) {
                    Log.e("MyTag", "Ошибка при разборе объекта " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
