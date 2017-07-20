package com.innopolis.lentanews.parser;


import com.innopolis.lentanews.model.News;

import java.util.List;

public interface ParserCallback {

    void onGetNewsList(List<News> result);
}
