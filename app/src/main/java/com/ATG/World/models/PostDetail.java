package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chetan on 15-01-2018.
 */

public class PostDetail {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("user_id_fk")
    @Expose
    private Integer userIdFk;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("article_type")
    @Expose
    private String articleType;
    @SerializedName("article_id")
    @Expose
    private Integer articleId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("follower_count")
    @Expose
    private Integer followerCount;
    @SerializedName("arr_tag")
    @Expose
    private List<Tags> arrTag = null;
    @SerializedName("arr_comment")
    @Expose
    private List<Comments> arrComment = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("posted_by")
    @Expose
    private List<PostedBy> postedBy = null;
    @SerializedName("audience")
    @Expose
    private Integer audience;
    @SerializedName("total_upvote")
    @Expose
    private Integer totalUpvote;
    @SerializedName("total_downvote")
    @Expose
    private Integer totalDownvote;
    @SerializedName("is_reported")
    @Expose
    private Integer isReported;
    @SerializedName("follower_status")
    @Expose
    private String followerStatus;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("descriptionURL")
    @Expose
    private String descriptionURL;
    @SerializedName("current_upvote_downvote_status")
    @Expose
    private String currentUpvoteDownvoteStatus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(Integer userIdFk) {
        this.userIdFk = userIdFk;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
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

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public List<Tags> getArrTag() {
        return arrTag;
    }

    public void setArrTag(List<Tags> arrTag) {
        this.arrTag = arrTag;
    }

    public List<Comments> getArrComment() {
        return arrComment;
    }

    public void setArrComment(List<Comments> arrComment) {
        this.arrComment = arrComment;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<PostedBy> getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(List<PostedBy> postedBy) {
        this.postedBy = postedBy;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public Integer getTotalUpvote() {
        return totalUpvote;
    }

    public void setTotalUpvote(Integer totalUpvote) {
        this.totalUpvote = totalUpvote;
    }

    public Integer getTotalDownvote() {
        return totalDownvote;
    }

    public void setTotalDownvote(Integer totalDownvote) {
        this.totalDownvote = totalDownvote;
    }

    public Integer getIsReported() {
        return isReported;
    }

    public void setIsReported(Integer isReported) {
        this.isReported = isReported;
    }

    public String getFollowerStatus() {
        return followerStatus;
    }

    public void setFollowerStatus(String followerStatus) {
        this.followerStatus = followerStatus;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescriptionURL() {
        return descriptionURL;
    }

    public void setDescriptionURL(String descriptionURL) {
        this.descriptionURL = descriptionURL;
    }

    public String getCurrentUpvoteDownvoteStatus() {
        return currentUpvoteDownvoteStatus;
    }

    public void setCurrentUpvoteDownvoteStatus(String currentUpvoteDownvoteStatus) {
        this.currentUpvoteDownvoteStatus = currentUpvoteDownvoteStatus;
    }
}