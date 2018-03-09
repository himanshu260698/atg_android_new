package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chetan on 27-02-2018.
 */

public class FollowersResponse {
    @SerializedName("err")
    @Expose
    private Integer err;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("followers")
    @Expose
    private List<FollowersList> followers = null;
    @SerializedName("followers__total_count")
    @Expose
    private Integer followersTotalCount;

    /**
     * No args constructor for use in serialization
     *
     */
    public FollowersResponse() {
    }

    /**
     *
     * @param followers
     * @param err
     * @param followersTotalCount
     * @param msg
     */
    public FollowersResponse(Integer err, String msg, List<FollowersList> followers, Integer followersTotalCount) {
        super();
        this.err = err;
        this.msg = msg;
        this.followers = followers;
        this.followersTotalCount = followersTotalCount;
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

    public List<FollowersList> getFollowers() {
        return followers;
    }

    public void setFollowers(List<FollowersList> followers) {
        this.followers = followers;
    }

    public Integer getFollowersTotalCount() {
        return followersTotalCount;
    }

    public void setFollowersTotalCount(Integer followersTotalCount) {
        this.followersTotalCount = followersTotalCount;
    }
}
