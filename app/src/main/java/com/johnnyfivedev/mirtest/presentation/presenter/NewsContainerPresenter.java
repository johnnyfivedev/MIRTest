package com.johnnyfivedev.mirtest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.johnnyfivedev.mirtest.presentation.view.NewsContainerView;
import com.johnnyfivedev.mirtest.ui.Screens;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class NewsContainerPresenter extends BaseDisposablePresenter<NewsContainerView> {

    private final Router router;


    public NewsContainerPresenter(Router router) {
        this.router = router;
    }

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        //getViewState().openNewsScreen();
        router.navigateTo(Screens.NEWS);
    }

    //endregion
}