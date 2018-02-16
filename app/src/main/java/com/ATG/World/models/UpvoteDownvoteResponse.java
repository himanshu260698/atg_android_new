package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 18-01-2018.
 */

public class UpvoteDownvoteResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("Upvote count")
    @Expose
    private Integer upvoteCount;
    @SerializedName("Downvote count")
    @Expose
    private Integer downvoteCount;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("notification")
    @Expose
    private Integer notification;

    /**
     * No args constructor for use in serialization
     */
    public UpvoteDownvoteResponse() {
    }

    /**
     * @param lastName
     * @param downvoteCount
     * @param upvoteCount
     * @param errorCode
     * @param notification
     * @param firstName
     * @param msg
     */
    public UpvoteDownvoteResponse(String errorCode, String msg, Integer upvoteCount, Integer downvoteCount, String firstName, String lastName, Integer notification) {
        super();
        this.errorCode = errorCode;
        this.msg = msg;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notification = notification;
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

    public Integer getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Integer getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(Integer downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNotification() {
        return notification;
    }

    public void setNotification(Integer notification) {
        this.notification = notification;
    }

}
