package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.domain.usecase.news.GetNewsUseCase;
import com.johnnyfivedev.mirtest.presentation.view.NewsDetailedView;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class NewsDetailedPresenter extends BaseDisposablePresenter<NewsDetailedView> {

    private Long newsItemId;


    public NewsDetailedPresenter(Long newsItemId) {
        this.newsItemId = newsItemId;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        /*disposeOnDestroy(getNewsUseCase.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                    getViewState().showNews(news);
                }, Throwable::printStackTrace)
        );
    }*/

        //endregion

        //region ===================== Presenter ======================


        //endregion
    }
}