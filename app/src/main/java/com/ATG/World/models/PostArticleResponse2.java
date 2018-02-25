package com.ATG.World.models;

/**
 * Created by him on 2/25/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostArticleResponse2 {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("article_id")
    @Expose
    private Integer articleId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("article_image")
    @Expose
    private String articleImage;
    @SerializedName("link")
    @Expose
    private String link;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}


