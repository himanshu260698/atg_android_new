package com.ATG.World.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Education_data {

    @SerializedName("user_id_fk")
    @Expose
    private String user_id_fk;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("education_type")
    @Expose
    private String education_type;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(String user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducation_type() {
        return education_type;
    }

    public void setEducation_type(String education_type) {
        this.education_type = education_type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
