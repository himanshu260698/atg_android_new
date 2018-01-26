package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 15-01-2018.
 */

public class PostedBy {
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("icon_img")
    @Expose
    private String iconImg;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }
}
