package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.support.v7.util.DiffUtil;

import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

import javax.inject.Inject;

public class NewsItemDiffUtilCallback extends DiffUtil.ItemCallback<NewsItem> {

    @Inject
    public NewsItemDiffUtilCallback() {
    }

    @Override
    public boolean areItemsTheSame(NewsItem oldItem, NewsItem newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(NewsItem oldItem, NewsItem newItem) {
        // Let's assume that news are different if at least ids are different
        return oldItem.equals(newItem);
    }
}