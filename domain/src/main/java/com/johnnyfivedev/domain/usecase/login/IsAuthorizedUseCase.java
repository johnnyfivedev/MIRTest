package com.johnnyfivedev.domain.usecase.login;

import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.domain.usecase.base.SingleUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

public class IsAuthorizedUseCase extends SingleUseCase<Void, Boolean> {

    private final LoginRepository loginRepository;

    @Inject
    public IsAuthorizedUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Single<Boolean> buildUseCaseSingle(Void aVoid) {
        return loginRepository.isAuthorized();
    }
}
