package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.mirtest.presentation.view.NewsContainerView;

@InjectViewState
public class NewsContainerPresenter extends BaseDisposablePresenter<NewsContainerView> {


    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().openNewsScreen();
    }

    //endregion
}