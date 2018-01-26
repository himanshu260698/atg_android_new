
package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrUserData {

    @SerializedName("tagline")
    @Expose
    private Object tagline;
    @SerializedName("location")
    @Expose
    private Object location;

    public Object getTagline() {
        return tagline;
    }

    public void setTagline(Object tagline) {
        this.tagline = tagline;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

}
