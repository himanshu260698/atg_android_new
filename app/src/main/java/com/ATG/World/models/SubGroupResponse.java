package com.ATG.World.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubGroupResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("subgroup")
    @Expose
    private List<Object> subgroup = null;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(List<Object> subgroup) {
        this.subgroup = subgroup;
    }

}