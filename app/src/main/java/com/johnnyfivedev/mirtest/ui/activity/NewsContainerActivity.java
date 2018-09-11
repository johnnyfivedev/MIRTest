package com.johnnyfivedev.mirtest.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.newscontainer.NewsContainerModule;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsContainerPresenter;
import com.johnnyfivedev.mirtest.presentation.view.NewsContainerView;
import com.johnnyfivedev.mirtest.ui.fragment.NewsFragment;

import javax.inject.Inject;
import javax.inject.Provider;

public class NewsContainerActivity extends BaseMvpAppCompatActivity implements NewsContainerView {

    @InjectPresenter
    NewsContainerPresenter presenter;

    @Inject
    Provider<NewsContainerPresenter> presenterProvider;

    @ProvidePresenter
    NewsContainerPresenter providePresenter() {
        return presenterProvider.get();
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
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    //endregion

    //region ===================== View ======================

    @Override
    public void openNewsScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_news_layout_container, NewsFragment.newInstance());
        fragmentTransaction.commit();
    }

    //endregion

    //region ===================== DI ======================

    private void initDI() {
        getAppComponent().plus(new NewsContainerModule())
                .inject(this);
    }

    //endregion
}