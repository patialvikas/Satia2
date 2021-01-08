package com.satia.productDetials.interfaces;

import com.satia.productDetials.model.ProductDetailResponseModelReel;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductDetailResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;

public interface IPProductDetails {

    void productDetails(String access_token,String product_id);
    //void productDetailsReel(String access_token,String product_id);
    void successResponseFromModel(ProductDetailResponseModel productDetailResponseModel);
    //void successResponseFromModelReel(ProductDetailResponseModelReel productDetailResponseModelReel);
    void failedResponseFromModel(String message);

    void logout(String access_token,String user_id);
    void logoutSuccessFromModel(LogoutResponseModel logoutResponseModel);
    void logoutFailedFromModel(String message);

    void productStatus(String access_token,String user_id,String product_id,String product_status);
    void productStatusSuccessFromModel(ProductStatus productStatus);
    void productStatusFailedFromModel(String message);

   // void successResponseFromModelReel(ProductDetailResponseModelReel productDetailResponseModelreel);
}

