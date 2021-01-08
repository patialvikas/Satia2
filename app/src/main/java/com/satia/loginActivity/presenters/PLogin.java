package com.satia.loginActivity.presenters;

import com.satia.loginActivity.LoginActivity;
import com.satia.loginActivity.interfaces.ILogin;
import com.satia.loginActivity.interfaces.IMLogin;
import com.satia.loginActivity.interfaces.IPLogin;
import com.satia.loginActivity.responseModel.LoginResponseModel;
import com.satia.loginActivity.viewModel.MLogin;

public class PLogin implements IPLogin {
    ILogin iLogin;
    IMLogin imLogin;
    public PLogin(LoginActivity loginActivity) {
        this.iLogin=loginActivity;
    }

    @Override
    public void login(String user_email, String user_password) {
        imLogin=new MLogin(this);
        imLogin.loginRestCalls(user_email,user_password);
    }

    @Override
    public void loginSuccessResponseFromModel(LoginResponseModel responseModel) {
        iLogin.loginSuccessResponseFromPresenter(responseModel);
    }

    @Override
    public void loginFailedResponseFromModel(String message) {
        iLogin.loginFailedResponseFromPresenter(message);
    }
}
