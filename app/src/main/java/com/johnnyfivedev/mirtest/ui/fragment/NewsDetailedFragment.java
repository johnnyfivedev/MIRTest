package com.johnnyfivedev.mirtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.newsdetailed.NewsDetailedModule;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsDetailedPresenter;
import com.johnnyfivedev.mirtest.presentation.view.NewsDetailedView;

import javax.inject.Inject;
import javax.inject.Provider;

public class NewsDetailedFragment extends BaseFragment implements NewsDetailedView {

    public static final String KEY_NEWS_ITEM_ID = "KEY_NEWS_ITEM_ID";

    @InjectPresenter
    NewsDetailedPresenter presenter;

    @Inject
    Provider<NewsDetailedPresenter> presenterProvider;

    @ProvidePresenter
    NewsDetailedPresenter providePresenter() {
        return presenterProvider.get();
    }


    //region ===================== New Instance ======================

    public static NewsDetailedFragment newInstance(Long newsItemId) {
        NewsDetailedFragment fragment = new NewsDetailedFragment();
        Bundle args = new Bundle();
        args.putLong(KEY_NEWS_ITEM_ID, newsItemId);
        fragment.setArguments(args);
        return fragment;
    }

    //endregion

    //region ==================== Lifecycle ====================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initDI();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detailed, container, false);
        initUI(view);
        return view;
    }

    //endregion

    //region ===================== DI =====================

    public void initDI() {
        getAppComponent().plus(new NewsDetailedModule(getArguments().getLong(KEY_NEWS_ITEM_ID)))
                .inject(this);
    }

    //endregion

    //region ===================== UI ======================

    public void initUI(View itemView) {
    }

    //endregion
}
