package com.satia.loginActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.ContextCompat;

import com.satia.R;
import com.satia.baseClass.BaseClass;
import com.satia.loginActivity.interfaces.ILogin;
import com.satia.loginActivity.interfaces.IPLogin;
import com.satia.loginActivity.presenters.PLogin;
import com.satia.loginActivity.responseModel.LoginResponseModel;
import com.satia.mainActivity.MainActivity;
import com.satia.networks.ConnectivityStatus;
import com.satia.networks.NetworkUtills;
import com.satia.utils.AppControler;
import com.satia.utils.AppController2;
import com.satia.utils.UtilsDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseClass implements ILogin {

    Context context;
    @BindView(R.id.editUserEmail)
    EditText editUserEmail;
    @BindView(R.id.imgEyes)
    ImageView imgEyes;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.checkRemberMe)
    AppCompatCheckBox checkRemberMe;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    String email;
    String password;
    Dialog progressDialog;
    IPLogin ipLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = LoginActivity.this;
        ipLogin = new PLogin(this);
        screenFull();
        initViews();
    }

    private void initViews() {
        email = AppController2.getInstance(context).getString(AppController2.Key.USER_LOGIN_EMAIL_STR);
        password = AppController2.getInstance(context).getString(AppController2.Key.USER_LOGIN_PASSWORD_STR);

        if (email != null || password != null) {
            editUserEmail.setText(email);
            editPassword.setText(password);
            checkRemberMe.setChecked(true);
        } else {
            editUserEmail.setText("");
            editPassword.setText("");
            checkRemberMe.setChecked(false);
        }
    }

    private void screenFull() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.colorActionBar));
        }
    }

    @OnClick({R.id.imgEyes, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgEyes:
                if (editPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    if (editPassword.getText().toString().isEmpty()) {
                        Toast.makeText(context, "Nothing to show", Toast.LENGTH_SHORT).show();
                    } else {
                        imgEyes.setImageResource(R.drawable.ic_visibility);
                        //Show Password
                        editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    }
                } else {

                    imgEyes.setImageResource(R.drawable.ic_visibility_off);
                    //Hide Password
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
                break;
            case R.id.btnLogin:
                validationOnLogin();
                break;
        }
    }

    private void validationOnLogin() {
        if (NetworkUtills.isNetworkConnectionAvailable(context)) {
            if (editUserEmail.getText().toString().isEmpty()) {
                editUserEmail.setError(getString(R.string.email));
            } else if (editPassword.getText().toString().isEmpty()) {
                editPassword.setError(getString(R.string.passwords));
            } else {
                if (checkRemberMe.isChecked()) {
                    rememberMe(editUserEmail.getText().toString().trim(), editPassword.getText().toString().trim());
                } else {
                    clear();
                }
                progressDialog = UtilsDialog.ShowDialog(this);
                ipLogin.login(editUserEmail.getText().toString().trim(), editPassword.getText().toString().trim());
            }
        }

    }

    public void rememberMe(String email, String password) {

        AppController2.getInstance(context).put(AppController2.Key.USER_LOGIN_EMAIL_STR, email);
        AppController2.getInstance(context).put(AppController2.Key.USER_LOGIN_PASSWORD_STR, password);
    }

    public void clear() {
        AppController2.getInstance(context).clear();
    }

    @Override
    public void loginSuccessResponseFromPresenter(LoginResponseModel responseModel) {
        progressDialog.dismiss();
        if (responseModel.getData() != null) {
            AppControler.getInstance(context).put(AppControler.Key.ID, responseModel.getData().getId());
            AppControler.getInstance(context).put(AppControler.Key.EMAIL, responseModel.getData().getEmail());
            AppControler.getInstance(context).put(AppControler.Key.NAME, responseModel.getData().getName());
            AppControler.getInstance(context).put(AppControler.Key.AUTH_TOKEN, responseModel.getData().getToken());
            SharedPreferences as=getSharedPreferences("logindata",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=as.edit();
            editor.putString("userId",responseModel.getData().getId().toString());
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {

        }

    }

    @Override
    public void loginFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}