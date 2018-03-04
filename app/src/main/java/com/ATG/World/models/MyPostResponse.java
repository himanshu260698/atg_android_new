package com.ATG.World.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Abhimanoj on 24-02-2018.
 */

public class MyPostResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("arr_data")
    @Expose
    private ArrayList<MyPostPojo> myPostPojoList;

    @SerializedName("count")
    @Expose
    private MyPostCountPojo myPostCountPojoInstance;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public ArrayList<MyPostPojo> getMyPostPojoList() {
        return myPostPojoList;
    }

    public void setMyPostPojoList(ArrayList<MyPostPojo> myPostPojoList) {
        this.myPostPojoList = myPostPojoList;
    }


    public MyPostCountPojo getMyPostCountPojoInstance() {
        return myPostCountPojoInstance;
    }

    public void setMyPostCountPojoInstance(MyPostCountPojo myPostCountPojoInstance) {
        this.myPostCountPojoInstance = myPostCountPojoInstance;
    }

}
