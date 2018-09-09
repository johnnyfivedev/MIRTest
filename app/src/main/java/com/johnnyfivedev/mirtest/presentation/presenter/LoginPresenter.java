package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.data.repositoryimpl.LoginRepositoryImpl;
import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.domain.usecase.login.LoginUseCase;
import com.johnnyfivedev.domain.usecase.login.LoginUseCaseParams;
import com.johnnyfivedev.mirtest.presentation.view.LoginView;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class LoginPresenter extends BaseDisposablePresenter<LoginView> {

    private final LoginUseCase loginUseCase;


    public LoginPresenter(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    //endregion

    //region ===================== Presenter ======================

    public void onLoginButtonClicked(String login, String password) {
        LoginUseCaseParams loginUseCaseParams = new LoginUseCaseParams(login,
                password,
                "password");

        disposeOnDestroy(loginUseCase.buildUseCaseSingle(loginUseCaseParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                            Object qwe = o;
                            getViewState().openNewsScreen();
                        },
                        Throwable::printStackTrace));
    }

    //endregion
}
