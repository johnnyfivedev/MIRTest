package com.johnnyfivedev.mirtest.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.mirtest.R;
import com.johnnyfivedev.mirtest.di.feature.newsdetailed.NewsDetailedModule;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsDetailedPresenter;
import com.johnnyfivedev.mirtest.presentation.view.NewsDetailedView;
import com.johnnyfivedev.mirtest.util.UiUtil;

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


    private ImageView ivImage;
    private TextView tvCategory;
    private TextView tvTitle;
    private TextView tvCreationDate;
    private TextView tvText;
    private TextView tvSource;

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

    //region ===================== Callbacks ======================

    private View.OnClickListener toolbarHomeButtonClickListener = v -> {
        presenter.onBackPressed();
    };

    private View.OnClickListener sourceTextClickListener = v -> {
        presenter.onSourceClicked();
    };

    //endregion

    //region ===================== View ======================

    @Override
    public void showNewsItem(NewsItem newsItem) {
        if (getContext() != null) {
            Glide.with(getContext())
                    .load(newsItem.getImageUrl())
                    .into(ivImage);
        }

        tvCategory.setText(newsItem.getNewsCategory().getTitle());
        tvTitle.setText(newsItem.getTitle());
        tvCreationDate.setText(newsItem.getCreationDateFormatted());
        UiUtil.setParsedHtmlText(tvText, newsItem.getText());
    }

    @Override
    public void openSource(String sourceUrl) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl));
        startActivity(browserIntent);
    }

    @Override
    public void closeScreen() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
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
        setupToolbar(itemView,
                null,
                R.drawable.ic_back,
                true,
                toolbarHomeButtonClickListener);

        ivImage = itemView.findViewById(R.id.iv_image);
        tvCategory = itemView.findViewById(R.id.tv_category);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvCreationDate = itemView.findViewById(R.id.tv_creation_date);
        tvText = itemView.findViewById(R.id.tv_text);
        tvSource = itemView.findViewById(R.id.tv_source);

        tvSource.setOnClickListener(sourceTextClickListener);
    }

    //endregion
}
