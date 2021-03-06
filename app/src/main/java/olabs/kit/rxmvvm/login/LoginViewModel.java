package olabs.kit.rxmvvm.login;

import android.content.Context;
import android.content.Intent;

import olabs.kit.rxmvvm.base.BaseViewModel;
import olabs.kit.rxmvvm.login.APIs.LoginAPIListener;
import olabs.kit.model.LoginRequest;
import olabs.kit.model.LoginResponse;
import olabs.kit.rxmvvm.registration.RegisterActivity;

/**
 * Created by Salil Kaul on 19/10/16.
 */

public class LoginViewModel extends BaseViewModel {

    LoginAPIListener mLoginAPIListener;

    LoginViewModel(Context ctx) {
        super(ctx);
        mLoginAPIListener = new LoginAPIListener(this);
    }

    public void onSubmit(LoginRequest loginRequest) {
        doLogin(loginRequest.getArnCode(),loginRequest.getGrant_type(),loginRequest.getPassword());
    }

    public void onRegister() {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    private void doLogin(String arn,String grantType,String password){
        mOnProgressListener.showProgress();
        mLoginAPIListener.doLogin(arn, grantType, password);
    }


    public void onLoginSuccess(LoginResponse loginResponse) {
        mOnProgressListener.hideProgress();
        if (loginResponse.getAccess_token() == null) {
            mOnProgressListener.showMessage(loginResponse.getMessage());
        } else {
            mOnProgressListener.showMessage(loginResponse.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        mLoginAPIListener.unsubscribe();
    }

    @Override
    public void onResume() {
        mLoginAPIListener.subscribe();
    }

    @Override
    public void onPause() {
        mLoginAPIListener.unsubscribe();
    }

}
