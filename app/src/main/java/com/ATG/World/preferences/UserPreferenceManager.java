package com.ATG.World.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by prate on 09-12-2017.
 */

public class UserPreferenceManager {

    private static SharedPreferences mSharedPreferences;
    private static final String PREF_NAME = "ATGWORLD";


    private static String USER_ID = "user_id";
    private static String USER_FNAME = "name";
    private static  String USER_LNAME="last_name";
    private static String USER_TYPE="user_type";
    private static String FIRM_NAME="firm_name";
    private static String PUSH_ID="push_id";
    private static String UNIQUE_ID="unique_id";
    private static  String USER_LOCAT="location";
    private static String USER_IMAGE= "profile_picture";
    private static String USER_FBLogin="fb_login";
    private static String USER_GOOGLELogin="google_login";
    private static String USER_LINKDINLogin="linkdin_login";
    private static String USER_TWITTERLogin="twitter_login";
    private static String USER_EMAIL="email";
    private static String USER_MOBILE_NO="mob_no";
    private static String userId="";

    private static void init(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

    }

    public static void login(Context mContext,
                             String userID,
                             String userFname,
                             String userLname,
                             String userType,
                             String firmName,
                             String image,
                             String uniqID,
                             String location,
                             String FbLogin,
                             String GglLogin,
                             String LkdnLogin,
                             String TwtrLogin,
                             String userEmail,
                             String userMobileNo){
        if(mSharedPreferences==null){
            init(mContext);
        }
        setId(userID);
        SharedPreferences.Editor mShEditor=mSharedPreferences.edit();
        mShEditor.putString(USER_ID,userID);
        mShEditor.putString(USER_FNAME,userFname);
        mShEditor.putString(USER_LNAME,userLname);
        mShEditor.putString(USER_TYPE,userType);
        mShEditor.putString(FIRM_NAME,firmName);
        mShEditor.putString(USER_IMAGE,image);
        mShEditor.putString(UNIQUE_ID,uniqID);
        mShEditor.putString(USER_LOCAT,location);
        mShEditor.putString(USER_FBLogin,FbLogin);
        mShEditor.putString(USER_GOOGLELogin,GglLogin);
        mShEditor.putString(USER_LINKDINLogin,LkdnLogin);
        mShEditor.putString(USER_TWITTERLogin,TwtrLogin);
        mShEditor.putString(USER_EMAIL,userEmail);
        mShEditor.putString(USER_MOBILE_NO,userMobileNo);
        mShEditor.commit();
    }
    private static void setId(String id) {
        userId = id;
    }


    public static void logout(Context mContext){
        if(mSharedPreferences==null){
            init(mContext);
        }
        SharedPreferences.Editor mSEditor=mSharedPreferences.edit();
        mSEditor.clear();
        mSEditor.commit();
    }
    public static String getUserImage(Context mContext) {
        String userImg = "";
        if (mSharedPreferences == null) {
            init(mContext);
        }
        userImg = mSharedPreferences.getString(USER_IMAGE, "");
        return userImg;
    }

    public static String getUserFbstatus(Context mContext) {
        String userFbstatus = "";
        if (mSharedPreferences == null) {
            init(mContext);
        }
        userFbstatus = mSharedPreferences.getString(USER_FBLogin, "");
        return userFbstatus;
    }
    public static String getUserGglstatus(Context mContext) {
        String userGglstatus = "";
        if (mSharedPreferences == null) {
            init(mContext);
        }
        userGglstatus = mSharedPreferences.getString(USER_GOOGLELogin, "");
        return userGglstatus;
    }
    public static String getUserLkdnstatus(Context mContext) {
        String userLkdnstatus = "";
        if (mSharedPreferences == null) {
            init(mContext);
        }
        userLkdnstatus = mSharedPreferences.getString(USER_LINKDINLogin, "");
        return userLkdnstatus;
    }
    public static String getUserTwtrstatus(Context mContext) {
        String userTwtrstatus = "";
        if (mSharedPreferences == null) {
            init(mContext);
        }
        userTwtrstatus = mSharedPreferences.getString(USER_TWITTERLogin, "");
        return userTwtrstatus;
    }
    public static String getUserLocatione(Context mContext) {
        String userLocat = "";
        if (mSharedPreferences == null) {
            init(mContext);
        }
        userLocat = mSharedPreferences.getString(USER_LOCAT, "");
        return userLocat;
    }
    public static void setUserImage(Context mContext, String urerIMag) {

        if (mSharedPreferences == null) {
            init(mContext);
        }
        SharedPreferences.Editor mShEditor = mSharedPreferences.edit();
        mShEditor.putString(USER_IMAGE, urerIMag);
        mShEditor.commit();
    }
    public static void setStoreName(Context mContext, String urerFirmName) {

        if (mSharedPreferences == null) {
            init(mContext);
        }
        SharedPreferences.Editor mShEditor = mSharedPreferences.edit();
        mShEditor.putString(FIRM_NAME, urerFirmName);
        mShEditor.commit();
    }
    public static String getUserName(Context mContext){
        String mUserName="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserName=mSharedPreferences.getString(USER_FNAME,"");
        return mUserName;
    }

    public static String getUserEmail(Context mContext){
        String mUserEmail="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserEmail=mSharedPreferences.getString(USER_EMAIL,"");
        return mUserEmail;
    }

    public static String getUserMobileNo(Context mContext){
        String mUserMobile="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserMobile=mSharedPreferences.getString(USER_MOBILE_NO,"");
        return mUserMobile;
    }

    public static String getUserId(Context mContext){
        String mUserId="";
        if(mSharedPreferences==null){
            init(mContext);
        }

        mUserId=mSharedPreferences.getString(USER_ID,userId);
        return mUserId;
    }
    public static void setUserId(Context mContext, String userId) {

        if (mSharedPreferences == null) {
            init(mContext);
        }
        SharedPreferences.Editor mShEditor = mSharedPreferences.edit();
        mShEditor.putString(USER_ID, userId);
        mShEditor.commit();
    }
    public static String getUserFirstName(Context mContext){
        String mUserLname="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserLname=mSharedPreferences.getString(USER_FNAME,"");
        return mUserLname;
    }
    public static String getUserLastName(Context mContext){
        String mUserLname="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserLname=mSharedPreferences.getString(USER_LNAME,"");
        return mUserLname;
    }
    public static String getFirmName(Context mContext){
        String mFirmName="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mFirmName=mSharedPreferences.getString(FIRM_NAME,"");
        return mFirmName;
    }
    public static String getUserType(Context mContext){
        String mUserType="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserType=mSharedPreferences.getString(USER_TYPE,"");
        return mUserType;
    }
    public static String getUniqId(Context mContext){
        String mUniqID="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUniqID=mSharedPreferences.getString(UNIQUE_ID,"");
        return mUniqID;
    }
    public static String getPushID(Context mContext){
        String mUserPushID="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserPushID=mSharedPreferences.getString(PUSH_ID,"");
        return mUserPushID;
    }

    public static void setPushNotificationId(Context mContext, String urerNotificationID) {

        if (mSharedPreferences == null) {
            init(mContext);
        }
        SharedPreferences.Editor mShEditor = mSharedPreferences.edit();
        mShEditor.putString(PUSH_ID, urerNotificationID);
        mShEditor.commit();
    }




}
