package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.mirtest.ListItemClickListener;
import com.johnnyfivedev.mirtest.R;

public class NewsPagingAdapter extends PagedListAdapter<NewsItem, NewsPagingAdapter.NewsItemViewHolder> {

    private final ListItemClickListener listItemClickListener;

    private Context context;


    public NewsPagingAdapter(DiffUtil.ItemCallback<NewsItem> diffUtilCallback,
                             ListItemClickListener listItemClickListener) {
        super(diffUtilCallback);
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public NewsPagingAdapter.NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new NewsPagingAdapter.NewsItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsPagingAdapter.NewsItemViewHolder holder, int position) {
        NewsItem newsItem = getItem(position);

        if (newsItem != null) {
            holder.tvCategory.setText(newsItem.getNewsCategory().getTitle());
            holder.tvTitle.setText(newsItem.getTitle());
            holder.tvCreationDate.setText(newsItem.getCreationDateFormatted());

            Glide.with(context)
                    .load(newsItem.getThumbnailUrl())
                    .into(holder.ivThumbnail);

            holder.itemView.setOnClickListener(v -> listItemClickListener.onItemClicked(newsItem.getId(), position));
        }
    }

    //region ===================== ViewHolder ======================

    static class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvTitle;
        TextView tvCreationDate;
        ImageView ivThumbnail;

        NewsItemViewHolder(View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tv_category);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreationDate = itemView.findViewById(R.id.tv_creation_date);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
        }

        //endregion
    }
}