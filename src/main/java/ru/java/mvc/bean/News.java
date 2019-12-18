package ru.java.mvc.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class News implements Serializable {

    private int idNews;
    private String newsAuthor;
    private String newsHeadline;
    private String newsMain;
    private Timestamp newsDate;

    public News() {
    }

    public News(String newsAuthor, String newsHeadline, String newsMain, Timestamp newsDate) {
        this.newsAuthor = newsAuthor;
        this.newsHeadline = newsHeadline;
        this.newsMain = newsMain;
        this.newsDate = newsDate;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public String getNewsMain() {
        return newsMain;
    }

    public void setNewsMain(String newsMain) {
        this.newsMain = newsMain;
    }

    public Timestamp getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Timestamp newsDate) {
        this.newsDate = newsDate;
    }
}
