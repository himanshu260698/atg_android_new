package com.ATG.World.models;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobDetail {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("user_id_fk")
    @Expose
    private Integer user_id_fk;
    @SerializedName("job_location")
    @Expose
    private String job_location;
    @SerializedName("min")
    @Expose
    private String min;
    @SerializedName("preferred_qualification")
    @Expose
    private String preferred_qualification;
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
    private String external_link_to_apply;
    @SerializedName("functional_area")
    @Expose
    private String functional_area;
    @SerializedName("employment_type")
    @Expose
    private String employment_type;
    @SerializedName("application_deadline")
    @Expose
    private String application_deadline;
    @SerializedName("salary_range")
    @Expose
    private Integer salary_range;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("email_address")
    @Expose
    private String email_address;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("about_us")
    @Expose
    private String about_us;
    @SerializedName("contact_us")
    @Expose
    private String contact_us;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("how_to_apply")
    @Expose
    private String how_to_apply;
    @SerializedName("hiring_process")
    @Expose
    private String hiring_process;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("cmp_name")
    @Expose
    private String cmp_name;
    @SerializedName("max")
    @Expose
    private String max;
    @SerializedName("telephone_number")
    @Expose
    private String telephone_number;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("job_id")
    @Expose
    private Integer job_id;
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
    @SerializedName("arr_tag")
    @Expose
    private List<Tags> arr_tag = null;
    @SerializedName("posted_by")
    @Expose
    private List<JobPosted_by> posted_by = null;
    @SerializedName("apply_count")
    @Expose
    private Integer apply_count;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(Integer user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getPreferred_qualification() {
        return preferred_qualification;
    }

    public void setPreferred_qualification(String preferred_qualification) {
        this.preferred_qualification = preferred_qualification;
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

    public String getExternal_link_to_apply() {
        return external_link_to_apply;
    }

    public void setExternal_link_to_apply(String external_link_to_apply) {
        this.external_link_to_apply = external_link_to_apply;
    }

    public String getFunctional_area() {
        return functional_area;
    }

    public void setFunctional_area(String functional_area) {
        this.functional_area = functional_area;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }

    public String getApplication_deadline() {
        return application_deadline;
    }

    public void setApplication_deadline(String application_deadline) {
        this.application_deadline = application_deadline;
    }

    public Integer getSalary_range() {
        return salary_range;
    }

    public void setSalary_range(Integer salary_range) {
        this.salary_range = salary_range;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
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

    public Object getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public Object getContact_us() {
        return contact_us;
    }

    public void setContact_us(String contact_us) {
        this.contact_us = contact_us;
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

    public Object getHow_to_apply() {
        return how_to_apply;
    }

    public void setHow_to_apply(String how_to_apply) {
        this.how_to_apply = how_to_apply;
    }

    public Object getHiring_process() {
        return hiring_process;
    }

    public void setHiring_process(String hiring_process) {
        this.hiring_process = hiring_process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCmp_name() {
        return cmp_name;
    }

    public void setCmp_name(String cmp_name) {
        this.cmp_name = cmp_name;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
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

    public List<Tags> getArr_tag() {
        return arr_tag;
    }

    public void setArr_tag(List<Tags> arr_tag) {
        this.arr_tag = arr_tag;
    }

    public List<JobPosted_by> getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(List<JobPosted_by> posted_by) {
        this.posted_by = posted_by;
    }

    public Integer getApply_count() {
        return apply_count;
    }

    public void setApply_count(Integer apply_count) {
        this.apply_count = apply_count;
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
