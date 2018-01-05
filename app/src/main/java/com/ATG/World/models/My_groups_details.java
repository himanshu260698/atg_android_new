package com.ATG.World.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class My_groups_details {

    @SerializedName("error_code")
    @Expose
    public String errorCode;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("arr_my_groups")
    @Expose
    public List<Object> arrMyGroups = null;
    @SerializedName("user_parent_group")
    @Expose
    public List<Object> userParentGroup = null;

}