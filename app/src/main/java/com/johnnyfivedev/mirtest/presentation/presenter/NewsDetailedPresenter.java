package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.usecase.news.GetNewsItemById;
import com.johnnyfivedev.mirtest.presentation.view.NewsDetailedView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class NewsDetailedPresenter extends BaseDisposablePresenter<NewsDetailedView> {

    private final Router router;
    private final Long newsItemId;
    private final GetNewsItemById getNewsItemById;

    private NewsItem newsItem;


    public NewsDetailedPresenter(Router router,
                                 Long newsItemId,
                                 GetNewsItemById getNewsItemById) {
        this.router = router;
        this.newsItemId = newsItemId;
        this.getNewsItemById = getNewsItemById;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        disposeOnDestroy(getNewsItemById.buildUseCaseObservable(newsItemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsItem -> {
                    this.newsItem = newsItem;
                    getViewState().showNewsItem(newsItem);
                }, Throwable::printStackTrace)
        );
    }

    //endregion

    //region ===================== Presenter ======================

    public void onBackPressed() {
        router.exit();
    }

    public void onSourceClicked() {
        getViewState().openSource(newsItem.getSourceUrl());
    }

    public void onChangeTextSizeButtonClicked() {
        getViewState().changeTextSize();
    }

    //endregion
}
