package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by him on 1/17/2018.
 */

public class PostArticleResponse {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("article_id")
    @Expose
    private Integer articleId;
    @SerializedName("user_data")
    @Expose
    private User_details userData;
    @SerializedName("article_data")
    @Expose
    private PostArticleDetails articleData;

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

    public User_details getUserData() {
        return userData;
    }

    public void setUserData(User_details userData) {
        this.userData = userData;
    }

    public PostArticleDetails getArticleData() {
        return articleData;
    }

    public void setArticleData(PostArticleDetails articleData) {
        this.articleData = articleData;
    }

}