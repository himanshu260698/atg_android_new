package com.ATG.World.models;

/**
 * Created by him on 2/2/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostQriousResponse {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("qrious_id")
    @Expose
    private Integer qriousId;
    @SerializedName("user_data")
    @Expose
    private User_details userData;
    @SerializedName("qrious_data")
    @Expose
    private PostQriousDetails qriousData;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getQriousId() {
        return qriousId;
    }

    public void setQriousId(Integer qriousId) {
        this.qriousId = qriousId;
    }

    public User_details getUserData() {
        return userData;
    }

    public void setUserData(User_details userData) {
        this.userData = userData;
    }

    public PostQriousDetails getQriousData() {
        return qriousData;
    }

    public void setQriousData(PostQriousDetails qriousData) {
        this.qriousData = qriousData;
    }

}
