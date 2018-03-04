package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhimanoj on 25-02-2018.
 */

public class MyPostCountPojo {

    @SerializedName("qrious_count")
    @Expose
    private Integer qriousCount;

    @SerializedName("event_count")
    @Expose
    private Integer eventCount;

    @SerializedName("article_count")
    @Expose
    private Integer articleCount;

    @SerializedName("meetup_count")
    @Expose
    private Integer meetupCount;


    @SerializedName("education_count")
    @Expose
    private Integer educationCount;

    @SerializedName("job_count")
    @Expose
    private Integer jobCount;

    @SerializedName("people_count")
    @Expose
    private Integer peopleCount;


    public Integer getQriousCount() {
        return qriousCount;
    }

    public void setQriousCount(Integer qriousCount) {
        this.qriousCount = qriousCount;
    }

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getMeetupCount() {
        return meetupCount;
    }

    public void setMeetupCount(Integer meetupCount) {
        this.meetupCount = meetupCount;
    }

    public Integer getEducationCount() {
        return educationCount;
    }

    public void setEducationCount(Integer educationCount) {
        this.educationCount = educationCount;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

}
