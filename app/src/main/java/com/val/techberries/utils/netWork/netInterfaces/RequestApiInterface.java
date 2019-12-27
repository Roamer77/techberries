package com.val.techberries.utils.netWork.netInterfaces;

import com.val.techberries.entities.entitiesForNetWork.UserInfo;
import com.val.techberries.entities.entitiesForNetWork.BigImageFromServer;
import com.val.techberries.entities.entitiesForNetWork.ProductDescription;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RequestApiInterface {

    @GET("/productInfo/productDescription")
    Call<ProductDescription> getProductDescriptionByName(@Query("productName")String productName);

    @POST("/image/getListOfBigImage")
    Call<BigImageFromServer> getImagesFroProduct(@Query("productName") String productName);

    @GET("/image/getListOfSmallImages")
    Call<ResponseBody> getSmallImages(@Query("categoryId") int categoryId );

    @POST("/registration/register")
    Call<UserInfo> sendRegistrationInfo( @Body UserInfo userInfo);


    @POST("/autharization/auth")
    Call<String> sendAuthInfo();

    @GET("/order/makeOrder")
    Call<String> makeOrder();

    @GET("/logout")
    Call<String> logoutFromAccount();
}
