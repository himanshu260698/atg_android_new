package com.ATG.World.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubGroupDetails {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("icon_img")
    @Expose
    private String iconImg;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("cover_img")
    @Expose
    private String coverImg;
    @SerializedName("img_is_available")
    @Expose
    private String imgIsAvailable;
    @SerializedName("is_join")
    @Expose
    private String isJoin;
    @SerializedName("tag_line")
    @Expose
    private String tagLine;
    @SerializedName("subgroups")
    @Expose
    private List<SubGroupDetails> subgroups = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<SubGroupDetails> getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(List<SubGroupDetails> subgroups) {
        this.subgroups = subgroups;
    }

}