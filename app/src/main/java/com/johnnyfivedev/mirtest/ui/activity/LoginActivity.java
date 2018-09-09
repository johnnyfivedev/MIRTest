package com.johnnyfivedev.mirtest.ui.activity;

import android.os.Bundle;
import android.support.v7.view.menu.MenuPresenter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.LoginModule;
import com.johnnyfivedev.mirtest.presentation.presenter.LoginPresenter;
import com.johnnyfivedev.mirtest.presentation.view.LoginView;

import javax.inject.Inject;
import javax.inject.Provider;

public class LoginActivity extends BaseMvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter presenter;

    @Inject
    Provider<LoginPresenter> presenterProvider;

    @ProvidePresenter
    LoginPresenter providePresenter() {
        return presenterProvider.get();
    }

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;


    //region ===================== Lifecycle ======================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initDI();
        super.onCreate(savedInstanceState);
        initUI();
    }

    //endregion

    //region ===================== Callbacks ======================

    private View.OnClickListener onButtonLoginClickListener =
            v -> presenter.onLoginButtonClicked(etEmail.getText().toString(),
                    etPassword.getText().toString());

    //endregion

    //region ===================== DI ======================

    public void initDI() {
        getAppComponent().plus(new LoginModule())
                .inject(this);
    }

    //endregion

    //region ===================== Internal ======================

    private void initUI() {
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(onButtonLoginClickListener);
    }

    //endregion
}