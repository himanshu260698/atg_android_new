package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 28-02-2018.
 */

public class UserProfilePhoto {
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserProfilePhoto() {
    }

    /**
     *
     * @param createdAt
     * @param profilePicture
     */
    public UserProfilePhoto(String profilePicture, String createdAt) {
        super();
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
