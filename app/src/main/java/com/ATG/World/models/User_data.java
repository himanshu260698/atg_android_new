package com.ATG.World.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User_data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profile_picture")
    @Expose
    private String profile_picture;
    @SerializedName("cover_photo")
    @Expose
    private String cover_photo;
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
    private String user_type;
    @SerializedName("user_status")
    @Expose
    private String user_status;
    @SerializedName("mob_no")
    @Expose
    private String mob_no;
    @SerializedName("phone_no")
    @Expose
    private String phone_no;
    @SerializedName("activation_code")
    @Expose
    private String activation_code;
    @SerializedName("reset_password_code")
    @Expose
    private String reset_password_code;
    @SerializedName("email_verified")
    @Expose
    private String email_verified;
    @SerializedName("last_login")
    @Expose
    private String last_login;
    @SerializedName("fb_id")
    @Expose
    private String fb_id;
    @SerializedName("linkdin_id")
    @Expose
    private Object linkdin_id;
    @SerializedName("twitter_id")
    @Expose
    private Object twitter_id;
    @SerializedName("google_id")
    @Expose
    private Object google_id;
    @SerializedName("body_id")
    @Expose
    private Integer body_id;
    @SerializedName("ethnicity_id")
    @Expose
    private Integer ethnicity_id;
    @SerializedName("eye_id")
    @Expose
    private Integer eye_id;
    @SerializedName("hair_id")
    @Expose
    private Integer hair_id;
    @SerializedName("send_email_notification")
    @Expose
    private String send_email_notification;
    @SerializedName("ip_address")
    @Expose
    private String ip_address;
    @SerializedName("user_country")
    @Expose
    private String user_country;
    @SerializedName("user_state")
    @Expose
    private String user_state;
    @SerializedName("user_city")
    @Expose
    private String user_city;
    @SerializedName("user_birth_date")
    @Expose
    private String user_birth_date;
    @SerializedName("user_age")
    @Expose
    private Integer user_age;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("height_unit")
    @Expose
    private String height_unit;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("is_member")
    @Expose
    private String is_member;
    @SerializedName("last_logout_time")
    @Expose
    private String last_logout_time;
    @SerializedName("about_me")
    @Expose
    private String about_me;
    @SerializedName("role_id")
    @Expose
    private Integer role_id;
    @SerializedName("is_google_crawalable")
    @Expose
    private Integer is_google_crawalable;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("facebook_page")
    @Expose
    private String facebook_page;
    @SerializedName("twitter_page")
    @Expose
    private String twitter_page;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("latitude")
    @Expose
    private Integer latitude;
    @SerializedName("longitude")
    @Expose
    private Integer longitude;
    @SerializedName("signup_step")
    @Expose
    private String signup_step;
    @SerializedName("joining_date")
    @Expose
    private String joining_date;
    @SerializedName("fn_updated")
    @Expose
    private String fn_updated;
    @SerializedName("ln_updated")
    @Expose
    private String ln_updated;
    @SerializedName("ut_updated")
    @Expose
    private String ut_updated;
    @SerializedName("first_social_login")
    @Expose
    private Integer first_social_login;
    @SerializedName("registration_id")
    @Expose
    private String registration_id;
    @SerializedName("device_name")
    @Expose
    private String device_name;
    @SerializedName("from_mobile_registration")
    @Expose
    private String from_mobile_registration;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
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

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public String getReset_password_code() {
        return reset_password_code;
    }

    public void setReset_password_code(String reset_password_code) {
        this.reset_password_code = reset_password_code;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public Object getLinkdin_id() {
        return linkdin_id;
    }

    public void setLinkdin_id(Object linkdin_id) {
        this.linkdin_id = linkdin_id;
    }

    public Object getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(Object twitter_id) {
        this.twitter_id = twitter_id;
    }

    public Object getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(Object google_id) {
        this.google_id = google_id;
    }

    public Integer getBody_id() {
        return body_id;
    }

    public void setBody_id(Integer body_id) {
        this.body_id = body_id;
    }

    public Integer getEthnicity_id() {
        return ethnicity_id;
    }

    public void setEthnicity_id(Integer ethnicity_id) {
        this.ethnicity_id = ethnicity_id;
    }

    public Integer getEye_id() {
        return eye_id;
    }

    public void setEye_id(Integer eye_id) {
        this.eye_id = eye_id;
    }

    public Integer getHair_id() {
        return hair_id;
    }

    public void setHair_id(Integer hair_id) {
        this.hair_id = hair_id;
    }

    public String getSend_email_notification() {
        return send_email_notification;
    }

    public void setSend_email_notification(String send_email_notification) {
        this.send_email_notification = send_email_notification;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_birth_date() {
        return user_birth_date;
    }

    public void setUser_birth_date(String user_birth_date) {
        this.user_birth_date = user_birth_date;
    }

    public Integer getUser_age() {
        return user_age;
    }

    public void setUser_age(Integer user_age) {
        this.user_age = user_age;
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

    public String getHeight_unit() {
        return height_unit;
    }

    public void setHeight_unit(String height_unit) {
        this.height_unit = height_unit;
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

    public String getIs_member() {
        return is_member;
    }

    public void setIs_member(String is_member) {
        this.is_member = is_member;
    }

    public String getLast_logout_time() {
        return last_logout_time;
    }

    public void setLast_logout_time(String last_logout_time) {
        this.last_logout_time = last_logout_time;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getIs_google_crawalable() {
        return is_google_crawalable;
    }

    public void setIs_google_crawalable(Integer is_google_crawalable) {
        this.is_google_crawalable = is_google_crawalable;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook_page() {
        return facebook_page;
    }

    public void setFacebook_page(String facebook_page) {
        this.facebook_page = facebook_page;
    }

    public String getTwitter_page() {
        return twitter_page;
    }

    public void setTwitter_page(String twitter_page) {
        this.twitter_page = twitter_page;
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

    public String getSignup_step() {
        return signup_step;
    }

    public void setSignup_step(String signup_step) {
        this.signup_step = signup_step;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public String getFn_updated() {
        return fn_updated;
    }

    public void setFn_updated(String fn_updated) {
        this.fn_updated = fn_updated;
    }

    public String getLn_updated() {
        return ln_updated;
    }

    public void setLn_updated(String ln_updated) {
        this.ln_updated = ln_updated;
    }

    public String getUt_updated() {
        return ut_updated;
    }

    public void setUt_updated(String ut_updated) {
        this.ut_updated = ut_updated;
    }

    public Integer getFirst_social_login() {
        return first_social_login;
    }

    public void setFirst_social_login(Integer first_social_login) {
        this.first_social_login = first_social_login;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getFrom_mobile_registration() {
        return from_mobile_registration;
    }

    public void setFrom_mobile_registration(String from_mobile_registration) {
        this.from_mobile_registration = from_mobile_registration;
    }

}
