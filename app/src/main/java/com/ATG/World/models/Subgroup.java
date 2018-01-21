
package com.ATG.World.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subgroup {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("icon_img")
    @Expose
    private String icon_img;
    @SerializedName("cover_img")
    @Expose
    private String cover_img;
    @SerializedName("parent_id")
    @Expose
    private Integer parent_id;
    @SerializedName("group_name")
    @Expose
    private String group_name;
    @SerializedName("group_description")
    @Expose
    private String group_description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("chlid")
    @Expose
    private List<Object> chlid = null;
    private boolean following=false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon_img() {
        return icon_img;
    }

    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<Object> getChlid() {
        return chlid;
    }

    public void setChlid(List<Object> chlid) {
        this.chlid = chlid;
    }

    public boolean getFollowing(){return following;}

    public void setFollowing(boolean following){this.following=following;}

}
