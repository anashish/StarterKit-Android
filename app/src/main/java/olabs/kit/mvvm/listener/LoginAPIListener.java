package olabs.kit.mvvm.listener;

import olabs.kit.API.ILoginAPI;
import olabs.kit.model.LoginResponse;
import olabs.kit.mvvm.login.LoginViewModel;
import olabs.kit.network.CallbackManager;
import olabs.network.rxnetwork.RetroError;
import retrofit2.Call;

/**
 * Created by ttnd on 27/2/17.
 */

public class LoginAPIListener extends CallbackManager {
    LoginViewModel mLoginViewModel;
    protected ILoginAPI mLoginAPI;
    public LoginAPIListener(LoginViewModel loginViewModel) {
        mLoginAPI = (ILoginAPI) getServiceClient(ILoginAPI.class);
        this.mLoginViewModel = loginViewModel;

    }

    public void doLogin(String arn,String grantType,String password) {
        Call<LoginResponse> call = mLoginAPI.doLogin(arn, grantType, password);
        call.enqueue(this);
    }

    @Override
    protected void onSuccess(Object o) {
        if(o instanceof LoginResponse){
            mLoginViewModel.onLoginSuccess((LoginResponse) o);
        }

    }

    @Override
    protected void onError(RetroError retroError) {
        mLoginViewModel.onError(retroError.getErrorMessage());
    }
}
