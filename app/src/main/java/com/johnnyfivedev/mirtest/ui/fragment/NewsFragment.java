package com.johnnyfivedev.mirtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.news.NewsModule;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsPresenter;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;
import com.johnnyfivedev.mirtest.ui.adapter.NewsAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

public class NewsFragment extends BaseFragment implements NewsView {

    @InjectPresenter
    NewsPresenter presenter;

    @Inject
    Provider<NewsPresenter> presenterProvider;

    @ProvidePresenter
    NewsPresenter provideNewsPresenter() {
        return presenterProvider.get();
    }

    @Inject
    NewsAdapter adapter;

    private RecyclerView rvNews;


    //region ===================== New Instance ======================

    public static NewsFragment newInstance() {
        return new NewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initUI(view);
        return view;
    }

    //endregion

    //region ==================== Callbacks ====================

    //endregion

    //region ===================== View ======================

    @Override
    public void showNews(List<NewsItem> news) {
        adapter.swapItems(news);
    }

    //endregion

    //region ===================== DI =====================

    public void initDI() {
        getAppComponent().plus(new NewsModule(getActivity()))
                .inject(this);
    }

    //endregion

    //region ===================== UI ======================

    public void initUI(View itemView) {
        //setupToolbar(itemView, R.string.toolbar_title_about_app, null, true, btnCloseClickListener);
        rvNews = itemView.findViewById(R.id.rv_news);

        rvNews.setItemAnimator(null);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.setAdapter(adapter);
    }

    //endregion
}