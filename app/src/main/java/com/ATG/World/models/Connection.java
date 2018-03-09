package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 05-03-2018.
 */

public class Connection {
    @SerializedName("to_user_id")
    @Expose
    private Integer toUserId;
    @SerializedName("to_first_name")
    @Expose
    private String toFirstName;
    @SerializedName("to_gender")
    @Expose
    private String toGender;
    @SerializedName("to_last_name")
    @Expose
    private String toLastName;
    @SerializedName("to_profile_picture")
    @Expose
    private String toProfilePicture;
    @SerializedName("from_user_id")
    @Expose
    private Integer fromUserId;
    @SerializedName("from_first_name")
    @Expose
    private String fromFirstName;
    @SerializedName("from_gender")
    @Expose
    private String fromGender;
    @SerializedName("from_last_name")
    @Expose
    private String fromLastName;
    @SerializedName("from_profile_picture")
    @Expose
    private String fromProfilePicture;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("block_by")
    @Expose
    private Integer blockBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("is_referral")
    @Expose
    private String isReferral;

    /**
     * No args constructor for use in serialization
     */
    public Connection() {
    }

    /**
     * @param toLastName
     * @param status
     * @param toFirstName
     * @param blockBy
     * @param toGender
     * @param fromLastName
     * @param fromUserId
     * @param updatedAt
     * @param toProfilePicture
     * @param id
     * @param fromProfilePicture
     * @param toUserId
     * @param createdAt
     * @param isReferral
     * @param fromFirstName
     * @param fromGender
     */
    public Connection(Integer toUserId, String toFirstName, String toGender, String toLastName, String toProfilePicture, Integer fromUserId, String fromFirstName, String fromGender, String fromLastName, String fromProfilePicture, Integer id, String status, Integer blockBy, String createdAt, String updatedAt, String isReferral) {
        super();
        this.toUserId = toUserId;
        this.toFirstName = toFirstName;
        this.toGender = toGender;
        this.toLastName = toLastName;
        this.toProfilePicture = toProfilePicture;
        this.fromUserId = fromUserId;
        this.fromFirstName = fromFirstName;
        this.fromGender = fromGender;
        this.fromLastName = fromLastName;
        this.fromProfilePicture = fromProfilePicture;
        this.id = id;
        this.status = status;
        this.blockBy = blockBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isReferral = isReferral;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getToFirstName() {
        return toFirstName;
    }

    public void setToFirstName(String toFirstName) {
        this.toFirstName = toFirstName;
    }

    public String getToGender() {
        return toGender;
    }

    public void setToGender(String toGender) {
        this.toGender = toGender;
    }

    public String getToLastName() {
        return toLastName;
    }

    public void setToLastName(String toLastName) {
        this.toLastName = toLastName;
    }

    public String getToProfilePicture() {
        return toProfilePicture;
    }

    public void setToProfilePicture(String toProfilePicture) {
        this.toProfilePicture = toProfilePicture;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromFirstName() {
        return fromFirstName;
    }

    public void setFromFirstName(String fromFirstName) {
        this.fromFirstName = fromFirstName;
    }

    public String getFromGender() {
        return fromGender;
    }

    public void setFromGender(String fromGender) {
        this.fromGender = fromGender;
    }

    public String getFromLastName() {
        return fromLastName;
    }

    public void setFromLastName(String fromLastName) {
        this.fromLastName = fromLastName;
    }

    public String getFromProfilePicture() {
        return fromProfilePicture;
    }

    public void setFromProfilePicture(String fromProfilePicture) {
        this.fromProfilePicture = fromProfilePicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBlockBy() {
        return blockBy;
    }

    public void setBlockBy(Integer blockBy) {
        this.blockBy = blockBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getIsReferral() {
        return isReferral;
    }

    public void setIsReferral(String isReferral) {
        this.isReferral = isReferral;
    }
}
