package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 27-02-2018.
 */

public class FollowingUsersList {
    @SerializedName("user_id_fk")
    @Expose
    private Integer userIdFk;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;

    /**
     * No args constructor for use in serialization
     *
     */
    public FollowingUsersList() {
    }

    /**
     *
     * @param lastName
     * @param userIdFk
     * @param profilePicture
     * @param gender
     * @param firstName
     */
    public FollowingUsersList(Integer userIdFk, String firstName, String gender, String lastName, String profilePicture) {
        super();
        this.userIdFk = userIdFk;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    public Integer getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(Integer userIdFk) {
        this.userIdFk = userIdFk;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
