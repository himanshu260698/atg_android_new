
package com.ATG.World.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationRes {

    @SerializedName("error_code")
    @Expose
    private Integer error_code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("notification")
    @Expose
    private List<Notification> notification = null;
    @SerializedName("slice_arr_count")
    @Expose
    private Integer slice_arr_count;

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Notification> getNotification() {
        return notification;
    }

    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }

    public Integer getSlice_arr_count() {
        return slice_arr_count;
    }

    public void setSlice_arr_count(Integer slice_arr_count) {
        this.slice_arr_count = slice_arr_count;
    }

}
