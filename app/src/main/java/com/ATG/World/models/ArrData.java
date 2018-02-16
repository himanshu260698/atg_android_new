
package com.ATG.World.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrData {

    @SerializedName("arr_post_data")
    @Expose
    private List<ArrPostDatum> arrPostData = null;
    @SerializedName("arr_joined_group_details")
    @Expose
    private ArrJoinedGroupDetails arrJoinedGroupDetails;
    @SerializedName("arr_parent_group")
    @Expose
    private List<ArrParentGroup> arrParentGroup = null;
    @SerializedName("arr_user_data")
    @Expose
    private ArrUserData arrUserData;

    public List<ArrPostDatum> getArrPostData() {
        return arrPostData;
    }

    public void setArrPostData(List<ArrPostDatum> arrPostData) {
        this.arrPostData = arrPostData;
    }

    public ArrJoinedGroupDetails getArrJoinedGroupDetails() {
        return arrJoinedGroupDetails;
    }

    public void setArrJoinedGroupDetails(ArrJoinedGroupDetails arrJoinedGroupDetails) {
        this.arrJoinedGroupDetails = arrJoinedGroupDetails;
    }

    public List<ArrParentGroup> getArrParentGroup() {
        return arrParentGroup;
    }

    public void setArrParentGroup(List<ArrParentGroup> arrParentGroup) {
        this.arrParentGroup = arrParentGroup;
    }

    public ArrUserData getArrUserData() {
        return arrUserData;
    }

    public void setArrUserData(ArrUserData arrUserData) {
        this.arrUserData = arrUserData;
    }

}
