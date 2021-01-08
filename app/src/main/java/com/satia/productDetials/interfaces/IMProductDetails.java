package com.satia.productDetials.interfaces;

public interface IMProductDetails {
    void productDetailsRestCalls(String access_token,String product_id);
    void logoutRestCalls(String access_token,String user_id);
    void productStatusRestCalls(String access_token,String user_id,String product_id,String product_status);

    void productDetailsRestCallsReel(String access_token, String product_id);
}
