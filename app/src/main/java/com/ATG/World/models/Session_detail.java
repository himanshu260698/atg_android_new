package com.ATG.World.models;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session_detail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("agenda_id_fk")
    @Expose
    private Integer agenda_id_fk;
    @SerializedName("session")
    @Expose
    private String session;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("created_at")
    @Expose
    private String created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgenda_id_fk() {
        return agenda_id_fk;
    }

    public void setAgenda_id_fk(Integer agenda_id_fk) {
        this.agenda_id_fk = agenda_id_fk;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
