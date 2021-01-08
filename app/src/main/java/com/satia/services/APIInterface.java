package com.satia.services;

import com.satia.loginActivity.responseModel.LoginResponseModel;
import com.satia.productDetials.model.ProductDetailResponseModelReel;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductDetailResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    public static final int PRODUCT_DETAIL_SUCCESS = 1;
    public static final int PRODUCT_DETAIL_FAILED = 2;

    public static final int LOGIN_SUCCESS = 3;
    public static final int LOGIN_FAILD = 4;

    public static final int LOGOUT_SUCCESS = 5;
    public static final int LOGOUT_FAILED = 6;

    public static final int PRODUCT_STATUS_SUCCESS = 7;
    public static final int PRODUCT_STATUS_FAILED = 8;
    public static final int PRODUCT_DETAIL_SUCCESS_REEL =9 ;

    // latest code
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> user_Login(@Field("email") String email,
                                        @Field("password") String password);


    @FormUrlEncoded
    @POST("productdetail")
    Call<ProductDetailResponseModel> getproductDetails(@Field("Authorization") String access_token,
                                                       @Field("product_id") String product_id);
    @FormUrlEncoded
    @POST("productdetail")
    Call<ProductDetailResponseModelReel> getproductDetailsReel(@Field("Authorization") String access_token,
                                                           @Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("logout")
    Call<LogoutResponseModel> logout(@Field("Authorization") String access_token,
                                     @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("productstatusupdate")
    Call<ProductStatus> product_status(@Field("Authorization") String access_token,
                                       @Field("user_id") String user_id,
                                       @Field("product_id") String product_id,
                                       @Field("product_status") String product_status);
}
