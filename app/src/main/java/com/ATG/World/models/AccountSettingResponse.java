package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 27-02-2018.
 */

public class AccountSettingResponse {
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("AccountSetting")
    @Expose
    private AccountSetting accountSetting;

    /**
     * No args constructor for use in serialization
     */
    public AccountSettingResponse() {
    }

    /**
     * @param errorCode
     * @param accountSetting
     * @param msg
     */
    public AccountSettingResponse(String errorCode, String msg, AccountSetting accountSetting) {
        super();
        this.errorCode = errorCode;
        this.msg = msg;
        this.accountSetting = accountSetting;
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

    public AccountSetting getAccountSetting() {
        return accountSetting;
    }

    public void setAccountSetting(AccountSetting accountSetting) {
        this.accountSetting = accountSetting;
    }
}
