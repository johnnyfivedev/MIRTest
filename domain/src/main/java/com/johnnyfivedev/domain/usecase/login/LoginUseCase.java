package com.johnnyfivedev.domain.usecase.login;

import com.johnnyfivedev.domain.entity.login.LoginResult;
import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.domain.usecase.base.SingleUseCase;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class LoginUseCase extends SingleUseCase<LoginUseCaseParams, LoginResult> {

    private final LoginRepository loginRepository;


    @Inject
    public LoginUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Single<LoginResult> buildUseCaseSingle(LoginUseCaseParams params) {
        return loginRepository.login(params.getLogin(), params.getPassword(), params.getGrantType());
    }
}
