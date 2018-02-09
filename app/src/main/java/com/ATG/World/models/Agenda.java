package com.ATG.World.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agenda {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("event_id_fk")
    @Expose
    private Integer event_id_fk;
    @SerializedName("start_date")
    @Expose
    private String start_date;
    @SerializedName("start_time")
    @Expose
    private String start_time;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("session_details")
    @Expose
    private List<Session_detail> session_details = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvent_id_fk() {
        return event_id_fk;
    }

    public void setEvent_id_fk(Integer event_id_fk) {
        this.event_id_fk = event_id_fk;
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

    public List<Session_detail> getSession_details() {
        return session_details;
    }

    public void setSession_details(List<Session_detail> session_details) {
        this.session_details = session_details;
    }

}
