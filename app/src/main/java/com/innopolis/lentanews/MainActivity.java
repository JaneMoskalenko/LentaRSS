package com.innopolis.lentanews;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.innopolis.lentanews.adapter.NewsRecyclerViewAdapter;
import com.innopolis.lentanews.model.News;
import com.innopolis.lentanews.parser.LoadTask;
import com.innopolis.lentanews.parser.ParserCallback;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListItemCallback, ParserCallback {

    android.app.Fragment main_news_fragment;
    RecyclerView recyclerView;
    List<News> testNewsList = new ArrayList<News>();
    List<News> newsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadTask task = new LoadTask(this);
        task.execute();

    }

    @Nullable
    private InputStream getInputStream(URL url) throws IOException {
        return url.openConnection().getInputStream();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(News news) {
        Toast.makeText(this, news.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetNewsList(List<News> result) {

        recyclerView = (RecyclerView) findViewById(R.id.rcv_main_news);
       // Toast.makeText(this, result(), Toast.LENGTH_LONG).show();

        NewsRecyclerViewAdapter adapter = new NewsRecyclerViewAdapter(this, result, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(this);
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mlinearLayoutManager);

    }
}
