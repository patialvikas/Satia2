package com.satia.loginActivity.viewModel;

import android.os.Handler;
import android.os.Message;
import com.satia.loginActivity.interfaces.IMLogin;
import com.satia.loginActivity.interfaces.IPLogin;
import com.satia.loginActivity.presenters.PLogin;
import com.satia.loginActivity.responseModel.LoginResponseModel;
import com.satia.services.APIInterface;
import com.satia.services.RetrofitCalls;

public class MLogin implements IMLogin {

    IPLogin ipLogin;

    public MLogin(PLogin pLogin) {
        this.ipLogin = pLogin;
    }

    @Override
    public void loginRestCalls(String user_email, String user_password) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.login_Api(user_email, user_password, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:
                    LoginResponseModel loginResponseModel = ((LoginResponseModel) msg.obj);
                    ipLogin.loginSuccessResponseFromModel(loginResponseModel);
                    break;
                case APIInterface.LOGIN_FAILD:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipLogin.loginFailedResponseFromModel(invalidRequest);
                    break;
            }
        }
    };
}
