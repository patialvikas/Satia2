package com.satia.productDetials.presenter;

import com.satia.productDetials.ProductDetailsActivity;
import com.satia.productDetials.ProductDetailsActivityReem;
import com.satia.productDetials.interfaces.IMProductDetails;
import com.satia.productDetials.interfaces.IPProductDetails;

import com.satia.productDetials.interfaces.IPProductDetailss;
import com.satia.productDetials.interfaces.IProductDetailss;
import com.satia.productDetials.model.MProductDetails;
import com.satia.productDetials.model.ProductDetailResponseModelReel;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductDetailResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;

public class PProductDetailss implements IPProductDetailss {

    IProductDetailss iProductDetails;
    IMProductDetails imProductDetails;

    public PProductDetailss(ProductDetailsActivityReem productDetailsActivityReem) {
        this.iProductDetails=productDetailsActivityReem;
    }



    @Override
    public void productDetailsReel(String access_token, String product_id) {
        imProductDetails=new MProductDetails(this);
        imProductDetails.productDetailsRestCallsReel(access_token,product_id);
    }

    @Override
    public void successResponseFromModelReel(ProductDetailResponseModelReel productDetailResponseModelReel) {
        iProductDetails.successResponseFromPresenterReel(productDetailResponseModelReel);
    }



    @Override
    public void failedResponseFromModel(String message) {
        iProductDetails.failedResponseFromPresenter(message);
    }

    @Override
    public void logout(String access_token,String user_id) {
        imProductDetails=new MProductDetails(this);
        imProductDetails.logoutRestCalls(access_token,user_id);
    }

    @Override
    public void logoutSuccessFromModel(LogoutResponseModel logoutResponseModel) {
        iProductDetails.logoutSuccessFromPresenter(logoutResponseModel);
    }

    @Override
    public void logoutFailedFromModel(String message) {
        iProductDetails.logoutFailedFromPresenter(message);
    }

    @Override
    public void productStatus(String access_token, String user_id, String product_id, String product_status) {
        imProductDetails=new MProductDetails(this);
        imProductDetails.productStatusRestCalls(access_token,user_id,product_id,product_status);
    }

    @Override
    public void productStatusSuccessFromModel(ProductStatus productStatus) {
        iProductDetails.productStatusSuccessFromPresenter(productStatus);
    }

    @Override
    public void productStatusFailedFromModel(String message) {
        iProductDetails.productStatusFailedFromPresenter(message);
    }



}