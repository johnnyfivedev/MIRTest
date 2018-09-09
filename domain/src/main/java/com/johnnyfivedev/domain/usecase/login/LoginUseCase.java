package com.johnnyfivedev.domain.usecase.login;

import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.domain.usecase.base.SingleUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginUseCase extends SingleUseCase<LoginUseCaseParams, Object> {

    private final LoginRepository loginRepository;


    @Inject
    public LoginUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Single<Object> buildUseCaseSingle(LoginUseCaseParams params) {
        return loginRepository.login(params.getLogin(), params.getPassword(), params.getGrantType());
    }
}
