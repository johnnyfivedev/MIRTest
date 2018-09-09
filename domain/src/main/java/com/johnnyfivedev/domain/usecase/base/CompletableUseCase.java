package com.johnnyfivedev.domain.usecase.base;

import io.reactivex.Completable;


public abstract class CompletableUseCase<Params> {
    public abstract Completable buildUseCaseCompletable(Params params);

    public Completable buildUseCaseCompletable() {
        return buildUseCaseCompletable(null);
    }
}