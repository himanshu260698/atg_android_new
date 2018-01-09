package com.ATG.World.network;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.ATG.World.models.AddEditDialogueResponse;
import com.ATG.World.models.DashboardResponse;
import com.ATG.World.models.JoinLeaveGroupResponse;
import com.ATG.World.models.MyGroupResponse;
import com.ATG.World.models.SubGroupResponse;
import com.ATG.World.models.User_details;
import com.ATG.World.models.WsLoginResponse;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Chetan on 06-12-2017.
 * This interface will be used for maintaining all API Calls.
 */

public interface AtgService {
    @GET("ws-login-user")
    Call<WsLoginResponse> getEmailLogin(@Query("email") String email, @Query("password") String password, @Query("device_name")
            String devicename, @Query("registration_id") String registration);

    @GET("ws-social-media")
    Call<WsLoginResponse> getFacebookLogin(@Query("fb_id") String fbid, @Query("first_name") String firstname,
                                           @Query("last_name") String lastname, @Query("email") String email, @Query("registration_id") String regid,
                                           @Query("device_name") int devname);

    @GET("ws-social-media")
    Call<WsLoginResponse> getGoogleLogin(@Query("google_id") String fbid, @Query("first_name") String firstname,
                                         @Query("last_name") String lastname, @Query("email") String email, @Query("registration_id") String regid,
                                         @Query("device_name") int devname);

    @GET("ws-social-media")
    Call<WsLoginResponse> getTwitterLogin(@Query("twitter_id") String fbid, @Query("user_name") String username, @Query("registration_id") String regid,
                                          @Query("device_name") int devname);

    /**
     * This API call will be used for getting data for Homepage.
     * Options -0:ALL, 1: article, 2-education, 5-job, 4- event, 3-meetup, 6- qrious Can accept multiple values using commas
     *
     * @return
     */
    @POST("ws-dashboard")
    @FormUrlEncoded
    Call<DashboardResponse> getDashboardData(@Field("filter") @NonNull int filterOption,
                                             @Field("page_number") @NonNull int pageNumber,
                                             @Field("user_id") @NonNull int userId);

    /*@GET("ws-get-notification-list")
    Call<> getNotificationList(@Field("user_id") int user_id);

    @POST("ws-mark-notifications-read")
    Call<> markNotificationRead(@Field("user_id") int user_id);*/
    /* API Calls related to settings
        goes here
     */
    @FormUrlEncoded
    @POST("ws-change-password")
    Call<WsLoginResponse> postChangedPassword(@Field("user_id") String userId,@Field("new_password")String newPassword);
    /*
    @FormUrlEncoded
    @POST("ws-account-setting")
    Call<WsLoginResponse> getUserDetails(@Field("user_id") int userId);
      */
    @Multipart
    @POST("ws-edit-account-setting")
    Call<User_details> updateUserDetails(@Part("user_id") RequestBody userId,@Part MultipartBody.Part file,  @Part("user_name") RequestBody userName, @Part("first_name") RequestBody firstName,
                                          @Part("last_name")RequestBody lastName, @Part("tagline")RequestBody tagLine,
                                          @Part("email")RequestBody email, @Part("profession")RequestBody profession,
                                          @Part("about_me")RequestBody aboutMe, @Part("mob_no")RequestBody mobileNo, @Part("phone_no")RequestBody phNo,
                                          @Part("location")RequestBody location, @Part("user_type")RequestBody userType);

    @POST("ws-my-groups")
    @FormUrlEncoded
    Call<MyGroupResponse> getMyGroupsData(@Field("user_id") @NonNull String userId);

    @POST("ws-join-leave-group")
    @FormUrlEncoded
    Call<JoinLeaveGroupResponse> joinLeaveGroup(@Field("status") @NonNull String status,
                                                @Field("user_id") @NonNull String userId,
                                                @Field("group_id") @NonNull String groupId);

    @GET("ws-sub-niche-groups")
    Call<SubGroupResponse> getSubGroupsData(@Query("user_id") @NonNull String userId,
                                            @Query("group_id") @NonNull String groupId);

    @POST("ws-group-edit-tagline")
    @FormUrlEncoded
    Call<AddEditDialogueResponse> getTagDialogueResponse(@Field("user_id") @NonNull String userId,
                                                         @Field("group_id") @NonNull String groupId,
                                                         @Field("tag_line") @NonNull String tagLine);

}
