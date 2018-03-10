package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 27-02-2018.
 */

public class FollowersList {
    @SerializedName("follower_id_fk")
    @Expose
    private Integer followerIdFk;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("last_name")
    @Expose
    private String lastName;

    /**
     * No args constructor for use in serialization
     *
     */
    public FollowersList() {
    }

    /**
     *
     * @param lastName
     * @param followerIdFk
     * @param profilePicture
     * @param gender
     * @param firstName
     */
    public FollowersList(Integer followerIdFk, String firstName, String gender, String profilePicture, String lastName) {
        super();
        this.followerIdFk = followerIdFk;
        this.firstName = firstName;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.lastName = lastName;
    }

    public Integer getFollowerIdFk() {
        return followerIdFk;
    }

    public void setFollowerIdFk(Integer followerIdFk) {
        this.followerIdFk = followerIdFk;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
