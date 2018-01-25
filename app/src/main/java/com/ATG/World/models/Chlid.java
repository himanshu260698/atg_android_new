
package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chlid {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("icon_img")
    @Expose
    private String iconImg;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("cover_img")
    @Expose
    private String coverImg;
    @SerializedName("img_is_available")
    @Expose
    private String imgIsAvailable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getImgIsAvailable() {
        return imgIsAvailable;
    }

    public void setImgIsAvailable(String imgIsAvailable) {
        this.imgIsAvailable = imgIsAvailable;
    }

}
