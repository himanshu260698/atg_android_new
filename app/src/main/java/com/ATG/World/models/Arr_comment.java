package com.ATG.World.models;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Arr_comment {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("commented_by")
    @Expose
    private String commented_by;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("comment_on")
    @Expose
    private String comment_on;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("parent_id")
    @Expose
    private Integer parent_id;
    @SerializedName("r_parent_id")
    @Expose
    private Integer r_parent_id;
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("profile_picture")
    @Expose
    private String profile_picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommented_by() {
        return commented_by;
    }

    public void setCommented_by(String commented_by) {
        this.commented_by = commented_by;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_on() {
        return comment_on;
    }

    public void setComment_on(String comment_on) {
        this.comment_on = comment_on;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getR_parent_id() {
        return r_parent_id;
    }

    public void setR_parent_id(Integer r_parent_id) {
        this.r_parent_id = r_parent_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

}
