package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.johnnyfivedev.domain.util.RxUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Holds {@link Disposable} object and perform disposal in onDestroy()
 **/
public abstract class BaseDisposablePresenter<T extends MvpView> extends MvpPresenter<T> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtil.disposeIfNeeded(compositeDisposable);
    }

    protected void disposeOnDestroy(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}