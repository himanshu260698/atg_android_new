package com.ATG.World.models;

import java.util.List;

import com.ATG.World.models.PostedBy;
import com.ATG.World.models.Tags;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationDetail {

    @SerializedName("user_id_fk")
    @Expose
    private Integer user_id_fk;
    @SerializedName("edu_id")
    @Expose
    private Integer edu_id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("education_type")
    @Expose
    private String education_type;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("profile_image")
    @Expose
    private String profile_image;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("education_id")
    @Expose
    private Integer education_id;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("profile_picture")
    @Expose
    private String profile_picture;
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
    private String user_type;
    @SerializedName("user_city")
    @Expose
    private String user_city;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("follower_count")
    @Expose
    private Integer follower_count;
    @SerializedName("arr_tag")
    @Expose
    private List<Tags> arr_tag = null;
    @SerializedName("arr_comment")
    @Expose
    private List<String> arr_comment = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("posted_by")
    @Expose
    private List<PostedBy> posted_by = null;
    @SerializedName("audience")
    @Expose
    private Integer audience;
    @SerializedName("total_upvote")
    @Expose
    private Integer total_upvote;
    @SerializedName("total_downvote")
    @Expose
    private Integer total_downvote;
    @SerializedName("is_reported")
    @Expose
    private Integer is_reported;
    @SerializedName("follower_status")
    @Expose
    private String follower_status;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("descriptionURL")
    @Expose
    private String descriptionURL;
    @SerializedName("current_upvote_downvote_status")
    @Expose
    private String current_upvote_downvote_status;

    public Integer getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(Integer user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    public Integer getEdu_id() {
        return edu_id;
    }

    public void setEdu_id(Integer edu_id) {
        this.edu_id = edu_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getEducation_type() {
        return education_type;
    }

    public void setEducation_type(String education_type) {
        this.education_type = education_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEducation_id() {
        return education_id;
    }

    public void setEducation_id(Integer education_id) {
        this.education_id = education_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
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

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(Integer follower_count) {
        this.follower_count = follower_count;
    }

    public List<Tags> getArr_tag() {
        return arr_tag;
    }

    public void setArr_tag(List<Tags> arr_tag) {
        this.arr_tag = arr_tag;
    }

    public List<String> getArr_comment() {
        return arr_comment;
    }

    public void setArr_comment(List<String> arr_comment) {
        this.arr_comment = arr_comment;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<PostedBy> getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(List<PostedBy> posted_by) {
        this.posted_by = posted_by;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public Integer getTotal_upvote() {
        return total_upvote;
    }

    public void setTotal_upvote(Integer total_upvote) {
        this.total_upvote = total_upvote;
    }

    public Integer getTotal_downvote() {
        return total_downvote;
    }

    public void setTotal_downvote(Integer total_downvote) {
        this.total_downvote = total_downvote;
    }

    public Integer getIs_reported() {
        return is_reported;
    }

    public void setIs_reported(Integer is_reported) {
        this.is_reported = is_reported;
    }

    public String getFollower_status() {
        return follower_status;
    }

    public void setFollower_status(String follower_status) {
        this.follower_status = follower_status;
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

    public String getCurrent_upvote_downvote_status() {
        return current_upvote_downvote_status;
    }

    public void setCurrent_upvote_downvote_status(String current_upvote_downvote_status) {
        this.current_upvote_downvote_status = current_upvote_downvote_status;
    }

}
