package com.ATG.World.models;


import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class PostEducationResponse1 {

    @SerializedName("error_code")
    @Expose
    private Integer error_code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("education_id")
    @Expose
    private Integer education_id;
    @SerializedName("user_data")
    @Expose
    private User_data user_data;
    @SerializedName("education_data")
    @Expose
    private Education_data education_data;

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

    public User_data getUser_data() {
        return user_data;
    }

    public void setUser_data(User_data user_data) {
        this.user_data = user_data;
    }

    public Education_data getEducation_data() {
        return education_data;
    }

    public void setEducation_data(Education_data education_data) {
        this.education_data = education_data;
    }

}
