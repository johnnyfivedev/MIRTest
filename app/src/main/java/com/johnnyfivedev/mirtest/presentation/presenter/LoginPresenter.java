package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.domain.usecase.login.IsAuthorizedUseCase;
import com.johnnyfivedev.domain.usecase.login.LoginUseCase;
import com.johnnyfivedev.domain.usecase.login.LoginUseCaseParams;
import com.johnnyfivedev.mirtest.presentation.view.LoginView;
import com.johnnyfivedev.mirtest.ui.Screens;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class LoginPresenter extends BaseDisposablePresenter<LoginView> {

    private final Router router;
    private final LoginUseCase loginUseCase;
    private final IsAuthorizedUseCase isAuthorizedUseCase;


    public LoginPresenter(Router router,
                          LoginUseCase loginUseCase,
                          IsAuthorizedUseCase isAuthorizedUseCase) {
        this.router = router;
        this.loginUseCase = loginUseCase;
        this.isAuthorizedUseCase = isAuthorizedUseCase;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        // Это нужно делать в SplashActivity
        disposeOnDestroy(isAuthorizedUseCase.buildUseCaseSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isAuthorized -> {
                    if (isAuthorized) {
                        //getViewState().openNewsScreen();
                        router.newRootScreen(Screens.CONTAINER_NEWS);
                    }
                }, Throwable::printStackTrace));
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
                .subscribe(loginResult -> {
                            router.newRootScreen(Screens.CONTAINER_NEWS);
                        },
                        throwable -> {
                            throwable.printStackTrace();
                        }));
    }

    //endregion
}
