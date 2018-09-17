package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCase;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCaseParams;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class NewsPresenter extends BaseDisposablePresenter<NewsView> {

    private final GetNewsPageUseCase getNewsPageUseCase;


    public NewsPresenter(GetNewsPageUseCase getNewsPageUseCase) {
        this.getNewsPageUseCase = getNewsPageUseCase;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().buildNewsPaging();
    }

    //endregion

    //region ===================== Presenter ======================

    public void onNewsItemClicked(Long newsItemId) {
        getViewState().openNewsDetailScreen(newsItemId);
    }

    public void onNewsPageRequested(int page, int pageSize) {
        GetNewsPageUseCaseParams getNewsPageUseCaseParams = new GetNewsPageUseCaseParams(page, pageSize);
        disposeOnDestroy(getNewsPageUseCase.buildUseCaseObservable(getNewsPageUseCaseParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsItems -> {
                    getViewState().setNews(newsItems);
                }, throwable -> {
                    throwable.printStackTrace();
                }));
    }

    public void onRefreshClicked() {
        getViewState().buildNewsPaging();
    }

    //endregion
}