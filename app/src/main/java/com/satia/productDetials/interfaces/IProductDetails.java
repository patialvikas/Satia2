package com.satia.productDetials.interfaces;

import com.satia.productDetials.model.ProductDetailResponseModelReel;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductDetailResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;

public interface IProductDetails {
    void successResponseFromPresenter(ProductDetailResponseModel productDetailResponseModel);
    //void successResponseFromPresenterReel(ProductDetailResponseModelReel productDetailResponseModelReel);
    void failedResponseFromPresenter(String message);

    void logoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel);

    void logoutFailedFromPresenter(String message);

    void productStatusSuccessFromPresenter(ProductStatus productStatus);

    void productStatusFailedFromPresenter(String message);

   /// void successResponseFromPresenterReel(ProductDetailResponseModelReel productDetailResponseModelreel);
}
