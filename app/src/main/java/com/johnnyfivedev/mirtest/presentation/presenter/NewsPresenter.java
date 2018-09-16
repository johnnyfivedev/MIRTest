package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCase;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCaseParams;
import com.johnnyfivedev.domain.usecase.news.GetNewsUseCase;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class NewsPresenter extends BaseDisposablePresenter<NewsView> {

    //private final GetNewsUseCase getNewsUseCase;
    private final GetNewsPageUseCase getNewsPageUseCase;


    //public NewsPresenter(GetNewsUseCase getNewsUseCase, GetNewsPageUseCase getNewsPageUseCase) {
    public NewsPresenter(GetNewsPageUseCase getNewsPageUseCase) {
        this.getNewsPageUseCase = getNewsPageUseCase;
        //this.getNewsUseCase = getNewsUseCase;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
       /* disposeOnDestroy(getNewsObservable().subscribe(newsItems -> {
            getViewState().showNews(newsItems);
        }, Throwable::printStackTrace));*/

        //MyPositionalDataSource dataSource = new MyPositionalDataSource(new EmployeeStorage());

    }

    //endregion

    //region ===================== Presenter ======================

    public void onNewsItemClicked(Long newsItemId) {
        getViewState().openNewsDetailScreen(newsItemId);
    }

    public void onRefreshClicked() {
      /*  disposeOnDestroy(getNewsObservable().subscribe(newsItems -> {
            getViewState().showNews(newsItems);
            getViewState().showMessage();
        }, Throwable::printStackTrace));*/
    }

    //endregion

    //region ===================== Internal ======================
/*
    private Observable<List<NewsItem>> getNewsObservable() {
        return getNewsUseCase.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }*/

    public void onNewsPageRequested(boolean initialRequest, int page, int pageSize) {
        GetNewsPageUseCaseParams getNewsPageUseCaseParams = new GetNewsPageUseCaseParams(page, pageSize);
        disposeOnDestroy(getNewsPageUseCase.buildUseCaseObservable(getNewsPageUseCaseParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsItems -> {
                    getViewState().setNews(initialRequest, newsItems);
                }, throwable -> {
                    throwable.printStackTrace();
                }));
    }

    //endregion
}