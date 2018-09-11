package com.johnnyfivedev.mirtest.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private Context context;
    private ListItemClickListener listItemClickListener;
    private List<NewsItem> items;


    public NewsAdapter(Context context,
                       ListItemClickListener listItemClickListener) {
        this.context = context;
        this.listItemClickListener = listItemClickListener;
        this.items = new ArrayList<>();
    }

    //region ===================== Adapter methods ======================

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsItemViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        NewsItem newsItem = items.get(position);

        holder.tvCategory.setText(newsItem.getNewsCategory().getTitle());
        holder.tvTitle.setText(newsItem.getTitle());
        holder.tvCreationDate.setText(newsItem.getCreationDateFormatted());

        Glide.with(context)
                .load(newsItem.getThumbnailUrl())
                .into(holder.ivThumbnail);

        holder.itemView.setOnClickListener(v -> listItemClickListener.onItemClicked(newsItem.getId(), position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swapItems(List<NewsItem> newItems) {
        items = newItems;
        notifyDataSetChanged();
    }

    //endregion

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
