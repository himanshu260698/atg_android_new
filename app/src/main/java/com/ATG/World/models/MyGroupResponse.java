package com.ATG.World.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyGroupResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("arr_my_groups")
    @Expose
    private List<GroupDetails> arrMyGroups = null;
    @SerializedName("user_parent_group")
    @Expose
    private List<String> userParentGroup = null;

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

    public List<GroupDetails> getArrMyGroups() {
        return arrMyGroups;
    }

    public void setArrMyGroups(List<GroupDetails> arrMyGroups) {
        this.arrMyGroups = arrMyGroups;
    }

    public List<String> getUserParentGroup() {
        return userParentGroup;
    }

    public void setUserParentGroup(List<String> userParentGroup) {
        this.userParentGroup = userParentGroup;
    }

}