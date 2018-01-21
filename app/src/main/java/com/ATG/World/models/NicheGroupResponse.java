package com.ATG.World.models;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class NicheGroupResponse {

    @SerializedName("error_code")
    @Expose
    private String error_code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("subgroup")
    @Expose
    private List<Subgroup> subgroup = null;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Subgroup> getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(List<Subgroup> subgroup) {
        this.subgroup = subgroup;
    }

}