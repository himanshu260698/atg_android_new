package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nnav on 1/12/2018.
 */

public class LocationDetails {

    @SerializedName("user_id")
    @Expose
    private String UserId;

    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @SerializedName("longitude")
    @Expose
    private Double longitude;




    public String getUser_id() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    public Double setLongitude() {
        return longitude;
    }

    public void setLongitudee(Double longitude) {
        this.longitude = longitude;
    }
}
