package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;

import java.util.Arrays;
import java.util.List;

@InjectViewState
public class NewsPresenter extends BaseDisposablePresenter<NewsView> {


    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        List<Object> news = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        getViewState().showNews(news);
    }

    //endregion

    //region ===================== Presenter ======================

    //endregion
}