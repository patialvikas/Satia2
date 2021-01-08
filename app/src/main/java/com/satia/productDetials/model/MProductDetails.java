package com.satia.productDetials.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.satia.productDetials.interfaces.IMProductDetails;
import com.satia.productDetials.interfaces.IPProductDetails;
import com.satia.productDetials.interfaces.IPProductDetailss;
import com.satia.productDetials.presenter.PProductDetails;
import com.satia.productDetials.presenter.PProductDetailss;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductDetailResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;
import com.satia.services.APIInterface;
import com.satia.services.RetrofitCalls;

public class MProductDetails implements IMProductDetails {

    IPProductDetails ipProductDetails;
    IPProductDetailss ipProductDetailss;

    public MProductDetails(PProductDetails pProductDetails) {
        this.ipProductDetails =(IPProductDetails) pProductDetails;
    }
    public MProductDetails(PProductDetailss pProductDetailss) {
        this.ipProductDetailss = (IPProductDetailss) pProductDetailss;
    }
    @Override
    public void productDetailsRestCalls(String access_token, String product_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        String s=product_id;
        String result =s.substring(s.lastIndexOf(',') + 1).trim();
        ///Log.e("final",result);
        if(result.matches("product_sheet")){

            retrofitCalls.product_details_Api(access_token, product_id, mHandler);
        }else{
            retrofitCalls.product_details_Api_reel(access_token, product_id, mHandler);
        }
    }

    @Override
    public void logoutRestCalls(String access_token, String user_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.logout_Api(access_token, user_id, mHandler);
    }

    @Override
    public void productStatusRestCalls(String access_token, String user_id, String product_id, String product_status) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.product_status_Api(access_token, user_id, product_id, product_status, mHandler);
    }

    @Override
    public void productDetailsRestCallsReel(String access_token, String product_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();

            retrofitCalls.product_details_Api_reel(access_token, product_id, mHandler);

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGOUT_SUCCESS:
                    LogoutResponseModel logoutResponseModel = ((LogoutResponseModel) msg.obj);
                    ipProductDetails.logoutSuccessFromModel(logoutResponseModel);
                    break;
                case APIInterface.LOGOUT_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipProductDetails.logoutFailedFromModel(invalidRequest);
                    break;
                case APIInterface.PRODUCT_DETAIL_SUCCESS:
                    ProductDetailResponseModel productDetailResponseModel = ((ProductDetailResponseModel) msg.obj);
                    ipProductDetails.successResponseFromModel(productDetailResponseModel);
                    break;
                case APIInterface.PRODUCT_DETAIL_SUCCESS_REEL:
                    ProductDetailResponseModelReel productDetailResponseModelreel = ((ProductDetailResponseModelReel) msg.obj);
                    ipProductDetailss.successResponseFromModelReel(productDetailResponseModelreel);
                    break;

                case APIInterface.PRODUCT_DETAIL_FAILED:
                    String invalidRequests = String.valueOf(msg.obj);
                    ipProductDetails.failedResponseFromModel(invalidRequests);
                    break;
                case APIInterface.PRODUCT_STATUS_SUCCESS:
                    ProductStatus productStatus = ((ProductStatus) msg.obj);
                    ipProductDetails.productStatusSuccessFromModel(productStatus);
                    break;
                case APIInterface.PRODUCT_STATUS_FAILED:
                    String requests = String.valueOf(msg.obj);
                    ipProductDetails.productStatusFailedFromModel(requests);
                    break;
            }
        }
    };
}
