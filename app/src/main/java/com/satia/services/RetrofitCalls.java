package com.satia.services;

import android.os.Handler;
import android.os.Message;

import com.satia.loginActivity.responseModel.LoginResponseModel;
import com.satia.productDetials.model.ProductDetailResponseModelReel;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductDetailResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;
import com.satia.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class RetrofitCalls {

    public APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void login_Api(String user_email, String user_password, final Handler mHandler) {

        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.user_Login(user_email, user_password);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call,
                                   Response<LoginResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGIN_FAILD;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGIN_FAILD;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    public void product_details_Api(String access_token, String product_id, final Handler mHandler) {

        final Message message = new Message();
        Call<ProductDetailResponseModel> call = apiInterface.getproductDetails(Constant.BEARER + access_token, product_id);
        call.enqueue(new Callback<ProductDetailResponseModel>() {
            @Override
            public void onResponse(Call<ProductDetailResponseModel> call,
                                   Response<ProductDetailResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.PRODUCT_DETAIL_SUCCESS;
                        message.obj = response.body();

                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PRODUCT_DETAIL_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponseModel> call, Throwable t) {
                message.what = apiInterface.PRODUCT_DETAIL_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }
// sec for reel
public void product_details_Api_reel(String access_token, String product_id, final Handler mHandler) {

    final Message message = new Message();
    Call<ProductDetailResponseModelReel> call = apiInterface.getproductDetailsReel(Constant.BEARER + access_token, product_id);
    call.enqueue(new Callback<ProductDetailResponseModelReel>() {
        @Override
        public void onResponse(Call<ProductDetailResponseModelReel> call,
                               Response<ProductDetailResponseModelReel> response) {

            if (response.body() != null) {
                if (response.body().getStatus() == 200) {
                    message.what = apiInterface.PRODUCT_DETAIL_SUCCESS_REEL;
                    message.obj = response.body();
                    mHandler.sendMessage(message);
                } else {
                    message.what = apiInterface.PRODUCT_DETAIL_FAILED;
                    message.obj = response.body().getMessage();
                    mHandler.sendMessage(message);
                }
            }
        }

        @Override
        public void onFailure(Call<ProductDetailResponseModelReel> call, Throwable t) {
            message.what = apiInterface.PRODUCT_DETAIL_FAILED;
            message.obj = t.getMessage();
            mHandler.sendMessage(message);
        }


    });
}




    public void logout_Api(String access_token, String user_id, final Handler mHandler) {

        final Message message = new Message();
        Call<LogoutResponseModel> call = apiInterface.logout(Constant.BEARER + access_token, user_id);
        call.enqueue(new Callback<LogoutResponseModel>() {
            @Override
            public void onResponse(Call<LogoutResponseModel> call,
                                   Response<LogoutResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGOUT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGOUT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGOUT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void product_status_Api(String access_token, String user_id, String product_id, String product_status, final Handler mHandler) {

        final Message message = new Message();
        Call<ProductStatus> call = apiInterface.product_status(Constant.BEARER + access_token, user_id, product_id, product_status);
        call.enqueue(new Callback<ProductStatus>() {
            @Override
            public void onResponse(Call<ProductStatus> call,
                                   Response<ProductStatus> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.PRODUCT_STATUS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PRODUCT_STATUS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductStatus> call, Throwable t) {
                message.what = apiInterface.PRODUCT_STATUS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }
}
