package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chetan on 17-01-2018.
 */

public class Tags {

    @SerializedName("tag_name")
    @Expose
    private String tagName;

    /**
     * No args constructor for use in serialization
     */
    public Tags() {
    }

    /**
     * @param tagName
     */
    public Tags(String tagName) {
        super();
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;

    }
}