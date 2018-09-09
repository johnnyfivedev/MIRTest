package com.johnnyfivedev.mirtest.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johnnyfivedev.mirtest.R;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private Context context;
    private List<Object> items;


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

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swapItems(List<Object> newItems) {
        items = newItems;
    }

    //endregion

    //region ===================== ViewHolder ======================

    static class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        NewsItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    //endregion
}
