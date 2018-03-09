package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 09-03-2018.
 */

public class EditProfileInfoResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("UserInformation")
    @Expose
    private UserInformation userInformation;

    /**
     * No args constructor for use in serialization
     *
     */
    public EditProfileInfoResponse() {
    }

    /**
     *
     * @param errorCode
     * @param userInformation
     * @param msg
     */
    public EditProfileInfoResponse(String errorCode, String msg, UserInformation userInformation) {
        super();
        this.errorCode = errorCode;
        this.msg = msg;
        this.userInformation = userInformation;
    }

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

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

}