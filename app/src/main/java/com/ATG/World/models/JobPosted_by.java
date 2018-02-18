package com.ATG.World.models;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobPosted_by {

    @SerializedName("group_name")
    @Expose
    private String group_name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("job_id_fk")
    @Expose
    private Integer job_id_fk;
    @SerializedName("icon_img")
    @Expose
    private String icon_img;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJob_id_fk() {
        return job_id_fk;
    }

    public void setJob_id_fk(Integer job_id_fk) {
        this.job_id_fk = job_id_fk;
    }

    public String getIcon_img() {
        return icon_img;
    }

    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }

}
