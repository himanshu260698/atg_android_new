
package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrJoinedGroupDetails {

    @SerializedName("id")
    @Expose
    private String id;
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
    @SerializedName("is_join")
    @Expose
    private String isJoin;
    @SerializedName("tag_line")
    @Expose
    private String tagLine;
    @SerializedName("img_is_available")
    @Expose
    private String imgIsAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getImgIsAvailable() {
        return imgIsAvailable;
    }

    public void setImgIsAvailable(String imgIsAvailable) {
        this.imgIsAvailable = imgIsAvailable;
    }

}
