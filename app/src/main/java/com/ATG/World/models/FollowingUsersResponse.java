package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chetan on 27-02-2018.
 */

public class FollowingUsersResponse {
    @SerializedName("err")
    @Expose
    private Integer err;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("followings")
    @Expose
    private List<FollowingUsersList> followings = null;
    @SerializedName("followings__total_count")
    @Expose
    private Integer followingsTotalCount;

    /**
     * No args constructor for use in serialization
     *
     */
    public FollowingUsersResponse() {
    }

    /**
     *
     * @param followings
     * @param err
     * @param followingsTotalCount
     * @param msg
     */
    public FollowingUsersResponse(Integer err, String msg, List<FollowingUsersList> followings, Integer followingsTotalCount) {
        super();
        this.err = err;
        this.msg = msg;
        this.followings = followings;
        this.followingsTotalCount = followingsTotalCount;
    }

    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FollowingUsersList> getFollowings() {
        return followings;
    }

    public void setFollowings(List<FollowingUsersList> followings) {
        this.followings = followings;
    }

    public Integer getFollowingsTotalCount() {
        return followingsTotalCount;
    }

    public void setFollowingsTotalCount(Integer followingsTotalCount) {
        this.followingsTotalCount = followingsTotalCount;
    }
}
