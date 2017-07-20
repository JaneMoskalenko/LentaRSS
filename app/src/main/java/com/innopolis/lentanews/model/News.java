package com.innopolis.lentanews.model;

import java.util.Date;

public class News {
    private String giud;
    private String title;
    private String link;
    private String description;
    private String date_publish;
    private String url;
    private String category;

    public News() {
    }

    public News( String title, String link,
                String description, String date_publish,
                String url, String category) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.date_publish = date_publish;
        this.url = url;
        this.category = category;
    }

    public News(String title) {
        this.title = title;
    }

    public String getGiud() {
        return giud;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {     return title;   }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getDate_publish() {
        return date_publish;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }


}
