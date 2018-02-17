package com.ATG.World.models;

/**
 * Created by him on 2/14/2018.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostJobResponse {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("job_id")
    @Expose
    private Integer jobId;
    @SerializedName("user_data")
    @Expose
    private User_details userData;
    @SerializedName("job_data")
    @Expose
    private PostJobDetails jobData;

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

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public User_details getUserData() {
        return userData;
    }

    public void setUserData(User_details userData) {
        this.userData = userData;
    }

    public PostJobDetails getJobData() {
        return jobData;
    }

    public void setJobData(PostJobDetails jobData) {
        this.jobData = jobData;
    }


}