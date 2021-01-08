package com.satia.loginActivity.interfaces;

import com.satia.loginActivity.responseModel.LoginResponseModel;

public interface IPLogin {
    void login(String user_email,String user_password);
    void loginSuccessResponseFromModel(LoginResponseModel responseModel);
    void loginFailedResponseFromModel(String message);
}
