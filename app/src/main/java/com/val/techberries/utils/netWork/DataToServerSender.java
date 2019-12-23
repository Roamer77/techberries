package com.val.techberries.utils.netWork;

import android.util.Log;

import com.val.techberries.entities.entitiesForNetWork.UserInfo;
import com.val.techberries.utils.netWork.netInterfaces.RequestApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataToServerSender {

    final  String baseURL="http://192.168.176.17:8080/registration/";
    final  String authURL="http://192.168.176.17:8080/autharization/";
    private  HttpLoggingInterceptor interceptor;

    private OkHttpClient.Builder client;

    public DataToServerSender(){
        interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client=new OkHttpClient.Builder().addInterceptor(interceptor);;
    }
    public void  sendUserRegistrationInfo(UserInfo userInfo){


        Call<UserInfo> status= retrofitConfig().sendRegistrationInfo(userInfo);
        status.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.isSuccessful())
                Log.e("MyTag","Данные для регестравции отправлены"+response.body().toString());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
            }
        });

    }

    public void sendAuthDataToServer(UserInfo userInfo){
        Call<UserInfo> status=retrofitConfig().sendAuthInfo(userInfo);
        status.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.isSuccessful())
                    Log.e("MyTag","Данные для регестравции отправлены"+response.body().toString());
                else
                    Log.e("MyTag","Ошибка: "+response.code());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    private RequestApiInterface retrofitConfig(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()).build();
        RequestApiInterface requestApiInterface = retrofit.create(RequestApiInterface.class);
        return requestApiInterface;
    }
}
