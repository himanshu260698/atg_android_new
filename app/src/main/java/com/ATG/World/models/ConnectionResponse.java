package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chetan on 05-03-2018.
 */

public class ConnectionResponse {
    @SerializedName("err")
    @Expose
    private Integer err;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("connections")
    @Expose
    private List<Connection> connections = null;
    @SerializedName("total_connections")
    @Expose
    private Integer totalConnections;

    /**
     * No args constructor for use in serialization
     *
     */
    public ConnectionResponse() {
    }

    /**
     *
     * @param totalConnections
     * @param err
     * @param connections
     * @param msg
     */
    public ConnectionResponse(Integer err, String msg, List<Connection> connections, Integer totalConnections) {
        super();
        this.err = err;
        this.msg = msg;
        this.connections = connections;
        this.totalConnections = totalConnections;
    }

    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public Integer getTotalConnections() {
        return totalConnections;
    }

    public void setTotalConnections(Integer totalConnections) {
        this.totalConnections = totalConnections;
    }
}
