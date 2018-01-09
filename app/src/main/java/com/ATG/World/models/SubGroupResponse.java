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
    @SerializedName("arr_user_groups")
    @Expose
    private List<SubGroupDetails> arrUserGroups = null;

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

    public List<SubGroupDetails> getArrUserGroups() {
        return arrUserGroups;
    }

    public void setArrUserGroups(List<SubGroupDetails> arrUserGroups) {
        this.arrUserGroups = arrUserGroups;
    }

}