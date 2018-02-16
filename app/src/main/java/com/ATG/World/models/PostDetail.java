package com.ATG.World.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostDetail {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("agenda")
    @Expose
    private List<Agenda> agenda = null;
    @SerializedName("user_id_fk")
    @Expose
    private Integer user_id_fk;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("start_date")
    @Expose
    private String start_date;
    @SerializedName("start_time")
    @Expose
    private String start_time;
    @SerializedName("end_date")
    @Expose
    private String end_date;
    @SerializedName("end_time")
    @Expose
    private String end_time;
    @SerializedName("min_age")
    @Expose
    private Integer min_age;
    @SerializedName("max_age")
    @Expose
    private Integer max_age;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("terms_condition")
    @Expose
    private String terms_condition;
    @SerializedName("contact_name")
    @Expose
    private String contact_name;
    @SerializedName("contact_number")
    @Expose
    private String contact_number;
    @SerializedName("email_address")
    @Expose
    private String email_address;
    @SerializedName("guest")
    @Expose
    private List<String> guest = null;
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
    @SerializedName("event_id")
    @Expose
    private Integer event_id;
    @SerializedName("main_cost")
    @Expose
    private String main_cost;
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
    @SerializedName("gender")
    @Expose
    private Object gender;
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
    @SerializedName("agenda_title")
    @Expose
    private String agenda_title;
    @SerializedName("arr_tag")
    @Expose
    private List<Tags> arr_tag = null;
    @SerializedName("arr_comment")
    @Expose
    private List<Arr_comment> arr_comment = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("posted_by")
    @Expose
    private List<Object> posted_by = null;
    @SerializedName("cost")
    @Expose
    private String cost;
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
    @SerializedName("get_save_user_post")
    @Expose
    private Integer get_save_user_post;
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

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public Integer getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(Integer user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getMin_age() {
        return min_age;
    }

    public void setMin_age(Integer min_age) {
        this.min_age = min_age;
    }

    public Integer getMax_age() {
        return max_age;
    }

    public void setMax_age(Integer max_age) {
        this.max_age = max_age;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTerms_condition() {
        return terms_condition;
    }

    public void setTerms_condition(String terms_condition) {
        this.terms_condition = terms_condition;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public List<String> getGuest() {
        return guest;
    }

    public void setGuest(List<String> guest) {
        this.guest = guest;
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

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getMain_cost() {
        return main_cost;
    }

    public void setMain_cost(String main_cost) {
        this.main_cost = main_cost;
    }

    public Object getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String  getUser_name() {
        return user_name;
    }

    public void setUser_name(String  user_name) {
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

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getUser_type() {
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

    public String  getLast_name() {
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

    public String getAgenda_title() {
        return agenda_title;
    }

    public void setAgenda_title(String agenda_title) {
        this.agenda_title = agenda_title;
    }

    public List<Tags> getArr_tag() {
        return arr_tag;
    }

    public void setArr_tag(List<Tags> arr_tag) {
        this.arr_tag = arr_tag;
    }

    public List<Arr_comment> getArr_comment() {
        return arr_comment;
    }

    public void setArr_comment(List<Arr_comment> arr_comment) {
        this.arr_comment = arr_comment;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Object> getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(List<Object> posted_by) {
        this.posted_by = posted_by;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public Integer getGet_save_user_post() {
        return get_save_user_post;
    }

    public void setGet_save_user_post(Integer get_save_user_post) {
        this.get_save_user_post = get_save_user_post;
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
