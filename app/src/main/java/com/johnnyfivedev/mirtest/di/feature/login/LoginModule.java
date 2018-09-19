package com.johnnyfivedev.mirtest.di.feature.login;

import android.support.v4.app.FragmentActivity;

import com.johnnyfivedev.data.api.LoginApi;
import com.johnnyfivedev.data.repositoryimpl.LoginRepositoryImpl;
import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.domain.usecase.login.IsAuthorizedUseCase;
import com.johnnyfivedev.domain.usecase.login.LoginUseCase;
import com.johnnyfivedev.localstorage.LocalStorageService;
import com.johnnyfivedev.mirtest.OkHttpClientFactory;
import com.johnnyfivedev.mirtest.ServerUrls;
import com.johnnyfivedev.mirtest.di.scope.LoginScope;
import com.johnnyfivedev.mirtest.presentation.presenter.LoginPresenter;
import com.johnnyfivedev.mirtest.ui.activity.login.LoginNavigator;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;

@Module
public class LoginModule {

    private final FragmentActivity activity;
    private final int fragmentContainerId;


    public LoginModule(FragmentActivity activity, int fragmentContainerId) {
        this.activity = activity;
        this.fragmentContainerId = fragmentContainerId;
    }

    @Provides
    @LoginScope
    LoginPresenter providePresenter(Router router,
                                    LoginUseCase loginUseCase,
                                    IsAuthorizedUseCase isAuthorizedUseCase) {
        return new LoginPresenter(router, loginUseCase, isAuthorizedUseCase);
    }

    @Provides
    @LoginScope
    SupportAppNavigator provideLoginNavigator() {
        return new LoginNavigator(activity, fragmentContainerId);
    }

    @Provides
    @LoginScope
    LoginApi provideLoginApi() {
        return new Retrofit.Builder()
                .baseUrl(ServerUrls.LOGIN_BASE_URL)
                .client(OkHttpClientFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LoginApi.class);
    }

    @Provides
    @LoginScope
    LoginRepository provideLoginRepository(LoginApi api,
                                           LocalStorageService localStorageService) {
        return new LoginRepositoryImpl(api, localStorageService);
    }
}
