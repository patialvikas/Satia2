package com.satia.loginActivity.interfaces;

import com.satia.loginActivity.responseModel.LoginResponseModel;

public interface ILogin {
    void loginSuccessResponseFromPresenter(LoginResponseModel responseModel);
    void loginFailedResponseFromPresenter(String message);
}
