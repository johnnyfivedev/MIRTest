package com.johnnyfivedev.mirtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.mirtest.ListItemClickListener;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.news.NewsModule;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsPresenter;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;
import com.johnnyfivedev.mirtest.ui.adapter.news.NewsPagingAdapter;
import com.johnnyfivedev.mirtest.ui.adapter.news.OnNextPageRequestedCallback;
import com.johnnyfivedev.mirtest.ui.adapter.news.PagedListInitializer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

public class NewsFragment extends BaseFragment implements NewsView {

    @InjectPresenter
    NewsPresenter presenter;

    @Inject
    Provider<NewsPresenter> presenterProvider;

    @ProvidePresenter
    NewsPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Inject
    NewsPagingAdapter adapter;

    @Inject
    PagedListInitializer<NewsItem> pagedListInitializer;

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
        setHasOptionsMenu(true);
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

    private ListItemClickListener newsItemClickListener = (data, position) -> {
        if (data instanceof Long) {
            presenter.onNewsItemClicked((Long) data);
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            presenter.onRefreshClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private OnNextPageRequestedCallback onNextPageRequestedCallback =
            (page, pageSize) -> presenter.onNewsPageRequested(page, pageSize);


    //endregion

    //region ===================== View ======================

    @Override
    public void buildNewsPaging() {
        adapter.submitList(pagedListInitializer.buildPagedList());
    }

    @Override
    public void setNews(List<NewsItem> items) {
        pagedListInitializer.setItems(items);
        logIds(items);
    }

    @Override
    public void openNewsDetailScreen(Long newsItemId) {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .hide(this)
                    .add(R.id.fragment_news_layout_container, NewsDetailedFragment.newInstance(newsItemId))
                    .addToBackStack(null)
                    .commit();
        }
    }

    //endregion

    //region ===================== DI =====================

    public void initDI() {
        getAppComponent().plus(new NewsModule(getActivity(),
                newsItemClickListener,
                onNextPageRequestedCallback))
                .inject(this);
    }

    //endregion

    //region ===================== Internal ======================

    public void initUI(View itemView) {
        setupToolbar(itemView,
                R.string.news,
                null,
                false,
                null);

        rvNews = itemView.findViewById(R.id.rv_news);

        rvNews.setItemAnimator(null);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.setAdapter(adapter);
    }

    private void logIds(List<NewsItem> items) {
        for (NewsItem newsItem : items) {
            Log.d("newsids", String.valueOf(newsItem.getId()));
        }
    }

    //endregion
}