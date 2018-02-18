package com.ATG.World.network;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.ATG.World.models.AddEditDialogueResponse;
import com.ATG.World.models.DashboardResponse;
import com.ATG.World.models.FeedDetailResponse;
import com.ATG.World.models.JobDetailResponse;
import com.ATG.World.models.PostJobResponse;
import com.ATG.World.models.PostQriousResponse;
import com.ATG.World.models.UpvoteDownvoteResponse;
import com.ATG.World.models.FeedDetailResponse;
import com.ATG.World.models.UpvoteDownvoteResponse;
import com.ATG.World.models.GroupPostListResponse;
import com.ATG.World.models.JoinLeaveGroupResponse;
import com.ATG.World.models.LocationDetails;
import com.ATG.World.models.MainGroup;
import com.ATG.World.models.MarkNotificationResponse;
import com.ATG.World.models.MyGroupResponse;
import com.ATG.World.models.NicheGroupResponse;
import com.ATG.World.models.NotificationRes;
import com.ATG.World.models.PostArticleDetails;
import com.ATG.World.models.PostArticleResponse;
import com.ATG.World.models.SignUpResponse;
import com.ATG.World.models.SubGroupResponse;
import com.ATG.World.models.User_details;
import com.ATG.World.models.WsJoinLeaveGroupResponse;
import com.ATG.World.models.WsLoginResponse;

import java.util.List;

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
                                           @Query("last_name") String lastname, @Query("email") String email);

    @GET("ws-social-media")
    Call<WsLoginResponse> getGoogleLogin(@Query("google_id") String fbid, @Query("first_name") String firstname,
                                         @Query("last_name") String lastname, @Query("email") String email);

    @GET("ws-social-media")
    Call<WsLoginResponse> getTwitterLogin(@Query("twitter_id") String fbid, @Query("user_name") String username);

    @GET("ws-register-user")
    Call<SignUpResponse> getEmailSignUp(@Query("first_name") String fname, @Query("last_name") String lname,
                                        @Query("email") String email, @Query("password") String password, @Query("device_name") String dev_name);

    @GET("ws-group")
    Call<MainGroup> getmainGroup();

    @GET("ws-sub-group")
    Call<NicheGroupResponse> getNicheGroup(@Query("id") int id);

    @GET("ws-join-leave-group")
    Call<WsJoinLeaveGroupResponse> joinLeaveGroup(@Query("status") int status, @Query("group_id") String group_id
            , @Query("user_id") String user_id);

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

    @POST("ws-update-user-location")
    @FormUrlEncoded
    Call<LocationDetails> Postlocation(
            @Field("user_id") @NonNull String userId,
            @Field("latitude") @NonNull Double latitude,
            @Field("longitude") @NonNull Double longitude);

    /*@GET("ws-get-notification-list")
    Call<> getNotificationList(@Field("user_id") int user_id);

    @POST("ws-mark-notifications-read")
    Call<> markNotificationRead(@Field("user_id") int user_id);*/
    /* API Calls related to settings
        goes here
     */
    @FormUrlEncoded
    @POST("ws-change-password")
    Call<WsLoginResponse> postChangedPassword(@Field("user_id") String userId, @Field("new_password") String newPassword);

    /*
    @FormUrlEncoded
    @POST("ws-account-setting")
    Call<WsLoginResponse> getUserDetails(@Field("user_id") int userId);
      */
    @Multipart
    @POST("ws-edit-account-setting")
    Call<User_details> updateUserDetails(@Part("user_id") RequestBody userId, @Part MultipartBody.Part file, @Part("user_name") RequestBody userName, @Part("first_name") RequestBody firstName,
                                         @Part("last_name") RequestBody lastName, @Part("tagline") RequestBody tagLine,
                                         @Part("email") RequestBody email, @Part("profession") RequestBody profession,
                                         @Part("about_me") RequestBody aboutMe, @Part("mob_no") RequestBody mobileNo, @Part("phone_no") RequestBody phNo,
                                         @Part("location") RequestBody location, @Part("user_type") RequestBody userType);

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

    @GET("ws-get-notification-list")
    Call<NotificationRes> getNotificationList(@Query("user_id") String user_id);


    @POST("ws-mark-notifications-read")
    Call<MarkNotificationResponse> markNotificationRead(@Field("user_id") @NonNull int user_id);

    /**
     * This API is used to fetch details of a particular feed
     *
     * @param postType Article, Events, etc
     * @param feedId   A feed have a unique id
     * @param userId   Id of logged User
     * @return
     */
    @POST("ws-feed-detail")
    @FormUrlEncoded
    Call<FeedDetailResponse> getFeedDetails(@Field("type") String postType,
                                            @Field("feed_id") int feedId,
                                            @Field("user_id") int userId);

    @POST("ws-feed-detail")
    @FormUrlEncoded
    Call<JobDetailResponse> getJobDetails(@Field("type") String postType,
                                          @Field("feed_id") int feedId,
                                          @Field("user_id") int userId);

    @GET("ws-upvote-downvote")
    Call<UpvoteDownvoteResponse> setUpvoteDownvote(@Query("status") int status,
                                                   @Query("type") String postType,
                                                   @Query("feed_id") int feedId,
                                                   @Query("user_id") int userId);

    @POST("ws-single-group-post-list")
    @FormUrlEncoded
    Call<GroupPostListResponse> getGroupPosts(@Field("user_id") @NonNull String user_id,
                                              @Field("group_id") @NonNull String group_id,
                                              @Field("type") @NonNull String type);
    //Post article calls

    @POST("ws-post-article-step-one")
    @FormUrlEncoded
    Call<PostArticleResponse> postArticleStepOne(@Field("user_id")@NonNull String userId, @Field("group_id")@NonNull int groupId,
                                                @Field("already_exist_article_id")String already_exist_article_id,
                                                @Field("title")@NonNull String title, @Field("description")@NonNull String description);
    @Multipart
    @POST("ws-post-article-step-two")
    Call<PostArticleResponse> postArticleStepTwo(@Part("user_id")RequestBody userId,@Part("article_id")RequestBody articleId,
                                                 @Part("tags")RequestBody tags, @Part MultipartBody.Part file,
                                                 @Part("title")RequestBody title);
    //Post qrious calls

    @POST("ws-post-qrious-step-one")
    @FormUrlEncoded
    Call<PostQriousResponse> postQriousStepOne(@Field("user_id")@NonNull String userId, @Field("group_id")@NonNull int groupId,
                                               @Field("already_exist_qrious_id")String already_exist_qrious_id,
                                               @Field("title")@NonNull String title, @Field("description")@NonNull String description);

    @Multipart
    @POST("ws-post-qrious-step-two")
    Call<PostQriousResponse> postQriousStepTwo(@Part("user_id")RequestBody userId,@Part("qrious_id")RequestBody articleId,
                                                 @Part("tags")RequestBody tags, @Part MultipartBody.Part file,
                                                 @Part("title")RequestBody title);

    //Post job calls
    @POST("ws-post-job-step-one")
    @FormUrlEncoded
    Call<PostJobResponse> postJobStepOne(@Field("user_id")@NonNull String userId, @Field("group_id")@NonNull int groupId,
                                             @Field("already_exist_article_id")String already_exist_article_id,
                                             @Field("title")@NonNull String title, @Field("description")@NonNull String description,
                                             @Field("location")@NonNull String location);

    @POST("ws-post-job-step-two")
    @FormUrlEncoded
    Call<PostJobResponse> postJobStepTwo(@Field("user_id")@NonNull String userId, @Field("job_id")@NonNull int jobId,
                                         @Field("company_name")String companyName,@Field("company_website")String companyWebsite,
                                         @Field("role")String role,@Field("external_link")String extLink,
                                         @Field("email_id")String emailId,@Field("dead_line")String deadline,
                                         @Field("education")String education,@Field("min_exp")String minExperience,
                                         @Field("location")String location,@Field("tags")String tags,
                                         @Field("emp_type")int empType);
}
