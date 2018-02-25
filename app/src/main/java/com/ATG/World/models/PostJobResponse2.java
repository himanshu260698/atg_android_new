package com.ATG.World.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostJobResponse2 {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("job_id")
    @Expose
    private Integer jobId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("arr_job")
    @Expose
    private PostJobDetails2 arrJob;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PostJobDetails2 getArrJob() {
        return arrJob;
    }

    public void setArrJob(PostJobDetails2 arrJob) {
        this.arrJob = arrJob;
    }

}
