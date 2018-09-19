package com.johnnyfivedev.mirtest.ui.activity.newscontainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.johnnyfivedev.mirtest.BackButtonListener;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.newscontainer.NewsContainerModule;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsContainerPresenter;
import com.johnnyfivedev.mirtest.presentation.view.NewsContainerView;
import com.johnnyfivedev.mirtest.ui.activity.BaseMvpAppCompatActivity;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.terrakok.cicerone.android.SupportAppNavigator;

public class NewsContainerActivity extends BaseMvpAppCompatActivity implements NewsContainerView {

    @InjectPresenter
    NewsContainerPresenter presenter;

    @Inject
    Provider<NewsContainerPresenter> presenterProvider;

    @ProvidePresenter
    NewsContainerPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Inject
    NewsNavigator navigator;

    @Override
    public SupportAppNavigator getNavigator() {
        return navigator;
    }


    //region ===================== Lifecycle ======================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initDI();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_container);
    }

    //endregion

    //region ===================== Callbacks ======================

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_news_layout_container);
        if (getSupportFragmentManager().getBackStackEntryCount() < 2) {
            finish();
        } else if (fragment instanceof BackButtonListener) {
            ((BackButtonListener) fragment).onBackPressed();
        }
    }

    //endregion

    //region ===================== DI ======================

    private void initDI() {
        getAppComponent().plus(new NewsContainerModule(this, R.id.fragment_news_layout_container))
                .inject(this);
    }

    //endregion
}