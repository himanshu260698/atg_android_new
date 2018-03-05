package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhimanoj on 25-02-2018.
 */

public class MyPostPojo {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;


    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("user_id_fk")
    @Expose
    private String userIdFk;

    @SerializedName("tags")
    @Expose
    private String tags;

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("image")
    @Expose
    private String image;


    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("post_status")
    @Expose
    private String postStatus;

    @SerializedName("share_link")
    @Expose
    private String shareLink;


    @SerializedName("count")
    @Expose
    private String count;

    @SerializedName("upvote_count")
    @Expose
    private String upvoteCount;

    @SerializedName("downvote_count")
    @Expose
    private String downvoteCount;

    @SerializedName("follower_status")
    @Expose
    private String followerStatus;

    @SerializedName("follower_count")
    @Expose
    private String followerCount;

    @SerializedName("arr_tag")
    @Expose
    private String[] arrTag;

    @SerializedName("type")
    @Expose
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(String userIdFk) {
        this.userIdFk = userIdFk;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(String upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public String getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(String downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public String getFollowerStatus() {
        return followerStatus;
    }

    public void setFollowerStatus(String followerStatus) {
        this.followerStatus = followerStatus;
    }

    public String getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(String followerCount) {
        this.followerCount = followerCount;
    }

    public String[] getArrTag() {
        return arrTag;
    }

    public void setArrTag(String[] arr_tag) {
        this.arrTag = arr_tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
