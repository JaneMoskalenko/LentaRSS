package com.innopolis.lentanews.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innopolis.lentanews.OnListItemCallback;
import com.innopolis.lentanews.R;
import com.innopolis.lentanews.model.News;

class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnListItemCallback callback;
    TextView title;
    ImageView imgThumb;
    int position;
    News mNews;

    public NewsViewHolder(View itemView, OnListItemCallback callback) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tv_news);
       // imgThumb = (ImageView) itemView.findViewById(R.id.imgThumb);
        this.callback = callback;
        itemView.setOnClickListener(this);
    }

    public void setData(News news, int position) {
        this.title.setText(news.getTitle());
      //  this.imgThumb.setImageResource(news.getImageID());
        this.position = position;
        this.mNews = news;
    }

    @Override
    public void onClick(View view) {
        callback.onClick(mNews);

    }
}
