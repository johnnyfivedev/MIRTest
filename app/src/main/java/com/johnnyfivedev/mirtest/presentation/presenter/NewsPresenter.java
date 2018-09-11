package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.usecase.news.GetNewsUseCase;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class NewsPresenter extends BaseDisposablePresenter<NewsView> {

    private final GetNewsUseCase getNewsUseCase;


    public NewsPresenter(GetNewsUseCase getNewsUseCase) {
        this.getNewsUseCase = getNewsUseCase;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposeOnDestroy(getNewsObservable().subscribe(newsItems -> {
            getViewState().showNews(newsItems);
        }, Throwable::printStackTrace));
    }

    //endregion

    //region ===================== Presenter ======================

    public void onNewsItemClicked(Long newsItemId) {
        getViewState().openNewsDetailScreen(newsItemId);
    }

    public void onRefreshClicked() {
        disposeOnDestroy(getNewsObservable().subscribe(newsItems -> {
            getViewState().showNews(newsItems);
            getViewState().showMessage();
        }, Throwable::printStackTrace));
    }

    //endregion

    //region ===================== Internal ======================

    private Observable<List<NewsItem>> getNewsObservable() {
        return getNewsUseCase.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //endregion
}