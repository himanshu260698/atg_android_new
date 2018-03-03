package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostEducationResponse2 {

    @SerializedName("error_code")
    @Expose
    private Integer error_code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("education_id")
    @Expose
    private Integer education_id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("education_image")
    @Expose
    private String education_image;
    @SerializedName("link")
    @Expose
    private String link;

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getEducation_id() {
        return education_id;
    }

    public void setEducation_id(Integer education_id) {
        this.education_id = education_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEducation_image() {
        return education_image;
    }

    public void setEducation_image(String education_image) {
        this.education_image = education_image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}