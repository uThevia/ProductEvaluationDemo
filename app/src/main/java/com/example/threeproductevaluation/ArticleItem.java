package com.example.threeproductevaluation;

public class ArticleItem {

    private int id;
    private String url, title, detail;

    public ArticleItem(){
        super();
        id = -1;
        url = null;
        title = null;
        detail = null;
    }
    public ArticleItem(int id, String url, String title, String detail){
        super();
        this.id = id;
        this.url = url;
        this.title = title;
        this.detail = detail;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUrl(){ return url; }
    public void setUrl(String url){
        this.url = url;
    }
    public void setUrl(String url, String defaultUrl){
        if (null == url || "".equals(url)) url = defaultUrl;
        this.url = url;
    }

    public String getTitle(){ return title; }
    public void setTitle(String title){
        this.title = title;
    }
    public void setTitle(String title, String defaultTitle){
        if (null == title || "".equals(title)) title = defaultTitle;
        this.title = title;
    }

    public String getDetail(){
        return detail;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
    public void setDetail(String detail, String defaultDetail){
        if (null == detail || "".equals(detail)) detail = defaultDetail;
        this.detail = detail;
    }
}
