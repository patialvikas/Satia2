package com.satia.productDetials.interfaces;


import com.satia.productDetials.model.ProductUpdateModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {
   // public static final int USER_REGISTER_SUCCESS = 1;
   // public static final int USER_REGISTER_FAILED = 2;

    //@FormUrlEncoded // annotation used in POST type requests
    @POST("/satia/api/productstatusupdate")
        // API's endpoints
    Call<ProductUpdateModel> productupdate(@Body Map<String, String> body);



}
