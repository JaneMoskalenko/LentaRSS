package com.innopolis.lentanews.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innopolis.lentanews.OnListItemCallback;
import com.innopolis.lentanews.R;
import com.innopolis.lentanews.model.News;

import java.util.List;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<News> mData;
    private LayoutInflater mInflater;
    private OnListItemCallback callback;

    public NewsRecyclerViewAdapter(Context context, List<News> data, OnListItemCallback callback) {
        mData = data;
        this.mInflater = LayoutInflater.from(context);
        this.callback = callback;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_news_list, parent, false);
        NewsViewHolder holder= new NewsViewHolder(view, callback);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = mData.get(position);
        holder.setData(news, position);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
