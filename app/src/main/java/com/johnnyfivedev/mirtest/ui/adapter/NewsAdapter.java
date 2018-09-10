package com.johnnyfivedev.mirtest.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.mirtest.R;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private Context context;
    private List<NewsItem> items;


    public NewsAdapter(Context context) {
        this.context = context;
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

        NewsItemViewHolder(View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tv_category);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreationDate = itemView.findViewById(R.id.tv_creation_date);
        }
    }

    //endregion
}
