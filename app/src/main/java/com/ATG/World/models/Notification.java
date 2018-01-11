
package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("notification_from")
    @Expose
    private Integer notification_from;
    @SerializedName("notification_to")
    @Expose
    private Integer notification_to;
    @SerializedName("notification_type")
    @Expose
    private String notification_type;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("notification_status")
    @Expose
    private String notification_status;
    @SerializedName("read_status")
    @Expose
    private String read_status;
    @SerializedName("delete_status")
    @Expose
    private String delete_status;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("team_id_fk")
    @Expose
    private String team_id_fk;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("activity_subject")
    @Expose
    private String activity_subject;
    @SerializedName("activity_message")
    @Expose
    private String activity_message;
    @SerializedName("post_id")
    @Expose
    private Integer post_id;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("follower_notification")
    @Expose
    private String follower_notification;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("profile_picture")
    @Expose
    private String profile_picture;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("feed_id")
    @Expose
    private String feed_id;
    @SerializedName("feed_type")
    @Expose
    private String feed_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotification_from() {
        return notification_from;
    }

    public void setNotification_from(Integer notification_from) {
        this.notification_from = notification_from;
    }

    public Integer getNotification_to() {
        return notification_to;
    }

    public void setNotification_to(Integer notification_to) {
        this.notification_to = notification_to;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotification_status() {
        return notification_status;
    }

    public void setNotification_status(String notification_status) {
        this.notification_status = notification_status;
    }

    public String getRead_status() {
        return read_status;
    }

    public void setRead_status(String read_status) {
        this.read_status = read_status;
    }

    public String getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(String delete_status) {
        this.delete_status = delete_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTeam_id_fk() {
        return team_id_fk;
    }

    public void setTeam_id_fk(String team_id_fk) {
        this.team_id_fk = team_id_fk;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getActivity_subject() {
        return activity_subject;
    }

    public void setActivity_subject(String activity_subject) {
        this.activity_subject = activity_subject;
    }

    public String getActivity_message() {
        return activity_message;
    }

    public void setActivity_message(String activity_message) {
        this.activity_message = activity_message;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFollower_notification() {
        return follower_notification;
    }

    public void setFollower_notification(String follower_notification) {
        this.follower_notification = follower_notification;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getFeed_type() {
        return feed_type;
    }

    public void setFeed_type(String feed_type) {
        this.feed_type = feed_type;
    }

}
