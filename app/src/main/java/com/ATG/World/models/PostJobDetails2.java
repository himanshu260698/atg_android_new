package com.ATG.World.models;

/**
 * Created by him on 2/25/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostJobDetails2 {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id_fk")
    @Expose
    private Integer userIdFk;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("job_location")
    @Expose
    private String jobLocation;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("min")
    @Expose
    private String min;
    @SerializedName("preferred_qualification")
    @Expose
    private String preferredQualification;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("skill")
    @Expose
    private String skill;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("external_link_to_apply")
    @Expose
    private String externalLinkToApply;
    @SerializedName("functional_area")
    @Expose
    private String functionalArea;
    @SerializedName("employment_type")
    @Expose
    private String employmentType;
    @SerializedName("application_deadline")
    @Expose
    private String applicationDeadline;
    @SerializedName("salary_range")
    @Expose
    private Integer salaryRange;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("email_address")
    @Expose
    private String emailAddress;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("about_us")
    @Expose
    private Object aboutUs;
    @SerializedName("contact_us")
    @Expose
    private Object contactUs;
    @SerializedName("how_to_apply")
    @Expose
    private Object howToApply;
    @SerializedName("hiring_process")
    @Expose
    private Object hiringProcess;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("cmp_name")
    @Expose
    private String cmpName;
    @SerializedName("max")
    @Expose
    private String max;
    @SerializedName("telephone_number")
    @Expose
    private String telephoneNumber;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getPreferredQualification() {
        return preferredQualification;
    }

    public void setPreferredQualification(String preferredQualification) {
        this.preferredQualification = preferredQualification;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getExternalLinkToApply() {
        return externalLinkToApply;
    }

    public void setExternalLinkToApply(String externalLinkToApply) {
        this.externalLinkToApply = externalLinkToApply;
    }

    public String getFunctionalArea() {
        return functionalArea;
    }

    public void setFunctionalArea(String functionalArea) {
        this.functionalArea = functionalArea;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public Integer getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(Integer salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(Object aboutUs) {
        this.aboutUs = aboutUs;
    }

    public Object getContactUs() {
        return contactUs;
    }

    public void setContactUs(Object contactUs) {
        this.contactUs = contactUs;
    }

    public Object getHowToApply() {
        return howToApply;
    }

    public void setHowToApply(Object howToApply) {
        this.howToApply = howToApply;
    }

    public Object getHiringProcess() {
        return hiringProcess;
    }

    public void setHiringProcess(Object hiringProcess) {
        this.hiringProcess = hiringProcess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCmpName() {
        return cmpName;
    }

    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}