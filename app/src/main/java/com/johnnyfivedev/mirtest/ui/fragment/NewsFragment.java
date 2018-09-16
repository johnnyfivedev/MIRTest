package com.johnnyfivedev.mirtest.ui.fragment;

import android.arch.paging.PagedList;
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
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.johnnyfivedev.data.MainThreadExecutor;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.mirtest.ListItemClickListener;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.news.NewsModule;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsPresenter;
import com.johnnyfivedev.mirtest.presentation.view.NewsView;
import com.johnnyfivedev.mirtest.ui.adapter.news.NewsPagingAdapter;
import com.johnnyfivedev.mirtest.ui.adapter.news.NewsPagingDataSource;

import java.util.List;
import java.util.concurrent.Executors;

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

    //endregion

    //region ===================== View ======================

    @Override
    public void buildNewsPaging() {
        adapter.submitList(pagedListBuilder.build());
    }

    @Override
    public void setNews(boolean isInitialRequest, List<NewsItem> newsItems) {
        dataSource.setItems(newsItems, isInitialRequest);
        logIds(newsItems);
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

    @Override
    public void showMessage() {
        Toast.makeText(getContext(), R.string.data_reloaded, Toast.LENGTH_SHORT).show();
    }

    //endregion

    //region ===================== DI =====================

    public void initDI() {
        getAppComponent().plus(new NewsModule(getActivity(), newsItemClickListener))
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

        setupNewsAdapter();
    }

    private void setupNewsAdapter() {
        rvNews.setItemAnimator(null);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.setAdapter(adapter);
    }

    private void logIds(List<NewsItem> items) {
        for (NewsItem newsItem : items) {
            Log.d("newsids", String.valueOf(newsItem.getId()));
        }
    }

    //todo move to di
    private PagedList.Config config = new PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build();

    private NewsPagingDataSource dataSource = new NewsPagingDataSource((initialRequest, page, pageSize) ->
            presenter.onNewsPageRequested(initialRequest, page, pageSize));

    private PagedList.Builder pagedListBuilder = new PagedList.Builder<>(dataSource, config)
            .setNotifyExecutor(new MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor());


    //endregion
}