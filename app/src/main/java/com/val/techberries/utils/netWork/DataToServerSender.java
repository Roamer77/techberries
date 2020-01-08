package com.val.techberries.utils.netWork;

import android.content.SharedPreferences;
import android.util.Log;

import com.val.techberries.entities.entitiesForNetWork.Order;
import com.val.techberries.entities.entitiesForNetWork.UserInfo;
import com.val.techberries.interfacies.CallBackToSaveUserInfo;
import com.val.techberries.utils.netWork.netInterfaces.RequestApiInterface;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataToServerSender {

    final String baseURL = "http://192.168.176.17:8080/registration/";
    final String authURL = "http://192.168.176.17:8080/autharization/";
    final String orderRquestURL = "http://192.168.176.17:8080/order/";
    final String logoutURL = "http://192.168.176.17:8080/logout/";
    final String findProductURL = "http://192.168.176.17:8080/productInfo/getProductsByName";
    private HttpLoggingInterceptor interceptor;

    private OkHttpClient.Builder client;
    private OkHttpClient auth;

    public DataToServerSender() {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor);


    }

    public void sendUserRegistrationInfo(UserInfo userInfo) {


        Call<UserInfo> status = retrofitConfig(baseURL).sendRegistrationInfo(userInfo);
        status.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful())
                    Log.e("MyTag", "Данные для регестравции отправлены" + response.body().toString());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
            }
        });

    }

    public void sendAuthDataToServer(OkHttpClient auth, CallBackToSaveUserInfo callBackToSaveUserInfo) {
        Call<String> status = retrofitConfigWithAuth(authURL, auth).sendAuthInfo();
        status.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.e("MyTag", "Данные для регестравции отправлены. Ответ: " + response.body());
                    callBackToSaveUserInfo.isOk(Integer.valueOf(response.body()));
                } else {
                    Log.e("MyTag", "Ошибка: " + response.code());
                    callBackToSaveUserInfo.onError("Login or password is not available");
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void makeOrderRequest(OkHttpClient auth, Order order) {
        Call<String> test = retrofitConfigWithAuth(orderRquestURL, auth).makeOrder(order);
        test.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.e("MyTag", "makeOrderRequest всё OK " + response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("MyTag", "makeOrderRequest всё ошибка  " + t.getMessage());
            }
        });
    }

    public void logoutFromAccount(CallBackToSaveUserInfo callBackToSaveUserInfo) {
        Call<String> test = retrofitConfig(logoutURL).logoutFromAccount();
        test.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.e("MyTag", "logoutFromAccount всё OK " + response.body());
                    callBackToSaveUserInfo.isOk(1);
                } else
                    callBackToSaveUserInfo.onError("Почему-то не разлогинился");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("MyTag", "logoutFromAccount " + t.getMessage());
            }
        });
    }




    private RequestApiInterface retrofitConfig(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()).build();
        RequestApiInterface requestApiInterface = retrofit.create(RequestApiInterface.class);
        return requestApiInterface;
    }

    private RequestApiInterface retrofitConfigWithAuth(String url, OkHttpClient auth) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(auth).build();
        RequestApiInterface requestApiInterface = retrofit.create(RequestApiInterface.class);
        return requestApiInterface;
    }


}
