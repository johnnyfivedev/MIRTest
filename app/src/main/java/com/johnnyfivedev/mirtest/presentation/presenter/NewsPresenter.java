package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCase;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCaseParams;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;
import com.johnnyfivedev.mirtest.ui.Screens;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class NewsPresenter extends BaseDisposablePresenter<NewsView> {

    private final Router router;
    private final GetNewsPageUseCase getNewsPageUseCase;


    public NewsPresenter(Router router, GetNewsPageUseCase getNewsPageUseCase) {
        this.router = router;
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
        router.navigateTo(Screens.NEWS_DETAILED, newsItemId);
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

    public void onBackPressed() {
        router.exit();
    }

    //endregion
}