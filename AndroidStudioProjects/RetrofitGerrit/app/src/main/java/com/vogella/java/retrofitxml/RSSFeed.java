package com.vogella.java.retrofitxml;

import com.vogella.java.retrofitxml.Article;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class RSSFeed {

    @Element(name="item")
    @Path("channel")
    private String channelTitle;

    @ElementList(name="item", inline=true)
    @Path("channel")
    private List<Article> articleList;

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
