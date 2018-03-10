package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chetan on 28-02-2018.
 */

public class UserProfile {
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("arr_u_data")
    @Expose
    private UserData arrUData;
    @SerializedName("arr_u_follower")
    @Expose
    private List<FollowersList> arrUFollower = null;
    @SerializedName("arr_u_following")
    @Expose
    private List<FollowingUsersList> arrUFollowing = null;
    @SerializedName("arr_u_group")
    @Expose
    private List<UserGroups> UserGroups = null;
    @SerializedName("arr_u_cover")
    @Expose
    private List<Object> arrUCover = null;
    @SerializedName("arr_u_profile_pic")
    @Expose
    private List<UserProfilePhoto> UserProfilePhoto = null;
    @SerializedName("arr_u_connection")
    @Expose
    private List<Object> arrUConnection = null;
    @SerializedName("arr_u_notification")
    @Expose
    private List<Object> arrUNotification = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserProfile() {
    }

    /**
     *
     * @param arrUNotification
     * @param arrUCover
     * @param UserGroups
     * @param UserProfilePhoto
     * @param arrUFollowing
     * @param arrUData
     * @param errorCode
     * @param arrUConnection
     * @param msg
     * @param arrUFollower
     */
    public UserProfile(String errorCode, String msg, UserData arrUData, List<FollowersList> arrUFollower, List<FollowingUsersList> arrUFollowing, List<UserGroups> UserGroups, List<Object> arrUCover, List<UserProfilePhoto> UserProfilePhoto, List<Object> arrUConnection, List<Object> arrUNotification) {
        super();
        this.errorCode = errorCode;
        this.msg = msg;
        this.arrUData = arrUData;
        this.arrUFollower = arrUFollower;
        this.arrUFollowing = arrUFollowing;
        this.UserGroups = UserGroups;
        this.arrUCover = arrUCover;
        this.UserProfilePhoto = UserProfilePhoto;
        this.arrUConnection = arrUConnection;
        this.arrUNotification = arrUNotification;
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

    public UserData getArrUData() {
        return arrUData;
    }

    public void setArrUData(UserData arrUData) {
        this.arrUData = arrUData;
    }

    public List<FollowersList> getArrUFollower() {
        return arrUFollower;
    }

    public void setArrUFollower(List<FollowersList> arrUFollower) {
        this.arrUFollower = arrUFollower;
    }

    public List<FollowingUsersList> getArrUFollowing() {
        return arrUFollowing;
    }

    public void setArrUFollowing(List<FollowingUsersList> arrUFollowing) {
        this.arrUFollowing = arrUFollowing;
    }

    public List<UserGroups> getUserGroups() {
        return UserGroups;
    }

    public void setUserGroups(List<UserGroups> UserGroups) {
        this.UserGroups = UserGroups;
    }

    public List<Object> getArrUCover() {
        return arrUCover;
    }

    public void setArrUCover(List<Object> arrUCover) {
        this.arrUCover = arrUCover;
    }

    public List<UserProfilePhoto> getUserProfilePhoto() {
        return UserProfilePhoto;
    }

    public void setUserProfilePhoto(List<UserProfilePhoto> UserProfilePhoto) {
        this.UserProfilePhoto = UserProfilePhoto;
    }

    public List<Object> getArrUConnection() {
        return arrUConnection;
    }

    public void setArrUConnection(List<Object> arrUConnection) {
        this.arrUConnection = arrUConnection;
    }

    public List<Object> getArrUNotification() {
        return arrUNotification;
    }

    public void setArrUNotification(List<Object> arrUNotification) {
        this.arrUNotification = arrUNotification;
    }
}
