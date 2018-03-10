package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 09-03-2018.
 */

public class UserInformation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("cover_photo")
    @Expose
    private String coverPhoto;
    @SerializedName("area")
    @Expose
    private String area;
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
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("mob_no")
    @Expose
    private String mobNo;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("activation_code")
    @Expose
    private String activationCode;
    @SerializedName("reset_password_code")
    @Expose
    private String resetPasswordCode;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("fb_id")
    @Expose
    private String fbId;
    @SerializedName("linkdin_id")
    @Expose
    private Object linkdinId;
    @SerializedName("twitter_id")
    @Expose
    private Object twitterId;
    @SerializedName("google_id")
    @Expose
    private Object googleId;
    @SerializedName("body_id")
    @Expose
    private Integer bodyId;
    @SerializedName("ethnicity_id")
    @Expose
    private Integer ethnicityId;
    @SerializedName("eye_id")
    @Expose
    private Integer eyeId;
    @SerializedName("hair_id")
    @Expose
    private Integer hairId;
    @SerializedName("send_email_notification")
    @Expose
    private String sendEmailNotification;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("user_country")
    @Expose
    private String userCountry;
    @SerializedName("user_state")
    @Expose
    private String userState;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("user_birth_date")
    @Expose
    private String userBirthDate;
    @SerializedName("user_age")
    @Expose
    private Integer userAge;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("height_unit")
    @Expose
    private String heightUnit;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("is_member")
    @Expose
    private String isMember;
    @SerializedName("last_logout_time")
    @Expose
    private String lastLogoutTime;
    @SerializedName("about_me")
    @Expose
    private String aboutMe;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("is_google_crawalable")
    @Expose
    private Integer isGoogleCrawalable;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("facebook_page")
    @Expose
    private String facebookPage;
    @SerializedName("twitter_page")
    @Expose
    private String twitterPage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("latitude")
    @Expose
    private Integer latitude;
    @SerializedName("longitude")
    @Expose
    private Integer longitude;
    @SerializedName("signup_step")
    @Expose
    private String signupStep;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("fn_updated")
    @Expose
    private String fnUpdated;
    @SerializedName("ln_updated")
    @Expose
    private String lnUpdated;
    @SerializedName("ut_updated")
    @Expose
    private String utUpdated;
    @SerializedName("first_social_login")
    @Expose
    private Integer firstSocialLogin;
    @SerializedName("registration_id")
    @Expose
    private String registrationId;
    @SerializedName("device_name")
    @Expose
    private String deviceName;
    @SerializedName("from_mobile_registration")
    @Expose
    private String fromMobileRegistration;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserInformation() {
    }

    /**
     *
     * @param registrationId
     * @param firstSocialLogin
     * @param location
     * @param profession
     * @param linkdinId
     * @param bodyId
     * @param userType
     * @param joiningDate
     * @param userState
     * @param hairId
     * @param height
     * @param deviceName
     * @param coverPhoto
     * @param userCountry
     * @param activationCode
     * @param userName
     * @param gender
     * @param longitude
     * @param roleId
     * @param mobNo
     * @param lastName
     * @param isGoogleCrawalable
     * @param googleId
     * @param utUpdated
     * @param fromMobileRegistration
     * @param phoneNo
     * @param twitterPage
     * @param email
     * @param userStatus
     * @param isMember
     * @param latitude
     * @param ethnicityId
     * @param tagline
     * @param weight
     * @param userCity
     * @param userBirthDate
     * @param twitterId
     * @param emailVerified
     * @param aboutMe
     * @param id
     * @param resetPasswordCode
     * @param eyeId
     * @param area
     * @param fnUpdated
     * @param createdAt
     * @param heightUnit
     * @param lnUpdated
     * @param lastLogoutTime
     * @param firstName
     * @param ipAddress
     * @param fbId
     * @param website
     * @param profilePicture
     * @param facebookPage
     * @param updatedAt
     * @param lastLogin
     * @param signupStep
     * @param sendEmailNotification
     * @param userAge
     */
    public UserInformation(Integer id, String userName, String email, String profilePicture, String coverPhoto, String area, String tagline, String profession, String location, String gender, String userType, String userStatus, String mobNo, String phoneNo, String activationCode, String resetPasswordCode, String emailVerified, String lastLogin, String fbId, Object linkdinId, Object twitterId, Object googleId, Integer bodyId, Integer ethnicityId, Integer eyeId, Integer hairId, String sendEmailNotification, String ipAddress, String userCountry, String userState, String userCity, String userBirthDate, Integer userAge, String height, String weight, String heightUnit, String firstName, String lastName, String isMember, String lastLogoutTime, String aboutMe, Integer roleId, Integer isGoogleCrawalable, String website, String facebookPage, String twitterPage, String createdAt, String updatedAt, Integer latitude, Integer longitude, String signupStep, String joiningDate, String fnUpdated, String lnUpdated, String utUpdated, Integer firstSocialLogin, String registrationId, String deviceName, String fromMobileRegistration) {
        super();
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.coverPhoto = coverPhoto;
        this.area = area;
        this.tagline = tagline;
        this.profession = profession;
        this.location = location;
        this.gender = gender;
        this.userType = userType;
        this.userStatus = userStatus;
        this.mobNo = mobNo;
        this.phoneNo = phoneNo;
        this.activationCode = activationCode;
        this.resetPasswordCode = resetPasswordCode;
        this.emailVerified = emailVerified;
        this.lastLogin = lastLogin;
        this.fbId = fbId;
        this.linkdinId = linkdinId;
        this.twitterId = twitterId;
        this.googleId = googleId;
        this.bodyId = bodyId;
        this.ethnicityId = ethnicityId;
        this.eyeId = eyeId;
        this.hairId = hairId;
        this.sendEmailNotification = sendEmailNotification;
        this.ipAddress = ipAddress;
        this.userCountry = userCountry;
        this.userState = userState;
        this.userCity = userCity;
        this.userBirthDate = userBirthDate;
        this.userAge = userAge;
        this.height = height;
        this.weight = weight;
        this.heightUnit = heightUnit;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isMember = isMember;
        this.lastLogoutTime = lastLogoutTime;
        this.aboutMe = aboutMe;
        this.roleId = roleId;
        this.isGoogleCrawalable = isGoogleCrawalable;
        this.website = website;
        this.facebookPage = facebookPage;
        this.twitterPage = twitterPage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.signupStep = signupStep;
        this.joiningDate = joiningDate;
        this.fnUpdated = fnUpdated;
        this.lnUpdated = lnUpdated;
        this.utUpdated = utUpdated;
        this.firstSocialLogin = firstSocialLogin;
        this.registrationId = registrationId;
        this.deviceName = deviceName;
        this.fromMobileRegistration = fromMobileRegistration;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public Object getLinkdinId() {
        return linkdinId;
    }

    public void setLinkdinId(Object linkdinId) {
        this.linkdinId = linkdinId;
    }

    public Object getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Object twitterId) {
        this.twitterId = twitterId;
    }

    public Object getGoogleId() {
        return googleId;
    }

    public void setGoogleId(Object googleId) {
        this.googleId = googleId;
    }

    public Integer getBodyId() {
        return bodyId;
    }

    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    public Integer getEthnicityId() {
        return ethnicityId;
    }

    public void setEthnicityId(Integer ethnicityId) {
        this.ethnicityId = ethnicityId;
    }

    public Integer getEyeId() {
        return eyeId;
    }

    public void setEyeId(Integer eyeId) {
        this.eyeId = eyeId;
    }

    public Integer getHairId() {
        return hairId;
    }

    public void setHairId(Integer hairId) {
        this.hairId = hairId;
    }

    public String getSendEmailNotification() {
        return sendEmailNotification;
    }

    public void setSendEmailNotification(String sendEmailNotification) {
        this.sendEmailNotification = sendEmailNotification;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(String userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
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

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(String lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsGoogleCrawalable() {
        return isGoogleCrawalable;
    }

    public void setIsGoogleCrawalable(Integer isGoogleCrawalable) {
        this.isGoogleCrawalable = isGoogleCrawalable;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getTwitterPage() {
        return twitterPage;
    }

    public void setTwitterPage(String twitterPage) {
        this.twitterPage = twitterPage;
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

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getSignupStep() {
        return signupStep;
    }

    public void setSignupStep(String signupStep) {
        this.signupStep = signupStep;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getFnUpdated() {
        return fnUpdated;
    }

    public void setFnUpdated(String fnUpdated) {
        this.fnUpdated = fnUpdated;
    }

    public String getLnUpdated() {
        return lnUpdated;
    }

    public void setLnUpdated(String lnUpdated) {
        this.lnUpdated = lnUpdated;
    }

    public String getUtUpdated() {
        return utUpdated;
    }

    public void setUtUpdated(String utUpdated) {
        this.utUpdated = utUpdated;
    }

    public Integer getFirstSocialLogin() {
        return firstSocialLogin;
    }

    public void setFirstSocialLogin(Integer firstSocialLogin) {
        this.firstSocialLogin = firstSocialLogin;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFromMobileRegistration() {
        return fromMobileRegistration;
    }

    public void setFromMobileRegistration(String fromMobileRegistration) {
        this.fromMobileRegistration = fromMobileRegistration;
    }

}
