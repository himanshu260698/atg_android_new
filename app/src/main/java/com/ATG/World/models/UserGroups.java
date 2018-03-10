package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 28-02-2018.
 */

public class UserGroups {

    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id_fk")
    @Expose
    private Integer userIdFk;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserGroups() {
    }

    /**
     *
     * @param id
     * @param groupName
     * @param userIdFk
     */
    public UserGroups(String groupName, Integer id, Integer userIdFk) {
        super();
        this.groupName = groupName;
        this.id = id;
        this.userIdFk = userIdFk;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(Integer userIdFk) {
        this.userIdFk = userIdFk;
    }
}
