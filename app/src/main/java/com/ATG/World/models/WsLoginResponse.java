
package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsLoginResponse {

    @SerializedName("error_code")
    @Expose
    private int error_code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user_details")
    @Expose
    private User_details user_details;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User_details getUser_details() {
        return user_details;
    }

    public void setUser_details(User_details user_details) {
        this.user_details = user_details;
    }

}
