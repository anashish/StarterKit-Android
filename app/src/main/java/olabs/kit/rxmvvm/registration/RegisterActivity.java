package olabs.kit.rxmvvm.registration;

import android.os.Bundle;

import olabs.kit.mvvm.base.BaseActivity;
import olabs.kit.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_activity_register);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, RegistrationFragment.newInstance()).addToBackStack(RegistrationFragment.mTitle).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
