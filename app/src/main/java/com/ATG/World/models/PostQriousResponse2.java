package com.ATG.World.models;

/**
 * Created by him on 2/25/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostQriousResponse2 {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("qrious_id")
    @Expose
    private Integer qriousId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("qrious_image")
    @Expose
    private String qriousImage;
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

    public Integer getQriousId() {
        return qriousId;
    }

    public void setQriousId(Integer qriousId) {
        this.qriousId = qriousId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQriousImage() {
        return qriousImage;
    }

    public void setQriousImage(String qriousImage) {
        this.qriousImage = qriousImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}