package com.ATG.World.models;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobDetailResponse extends JobDetail {

    @SerializedName("error_code")
    @Expose
    private String error_code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("PostDetail")
    @Expose
    private JobDetail postDetail;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JobDetail getJobDetail() {
        return postDetail;
    }

    public void setJobDetail(JobDetail postDetail) {
        this.postDetail = postDetail;
    }

}
