package com.johnnyfivedev.domain.usecase.base;

import io.reactivex.Single;

public abstract class SingleUseCase<Params, ReturnType> {
    public abstract Single<ReturnType> buildUseCaseSingle(Params params);

    public Single<ReturnType> buildUseCaseSingle() {
        return buildUseCaseSingle(null);
    }
}