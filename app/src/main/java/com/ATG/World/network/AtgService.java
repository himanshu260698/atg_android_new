package com.ATG.World.network;

import com.ATG.World.models.WsLoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Chetan on 06-12-2017.
 * This interface will be used for maintaining all API Calls.
 */

public interface AtgService {
    @GET("ws-login-user")
    Call<WsLoginResponse> getEmailLogin(@Query("email") String email,@Query("password") String password,@Query("device_name")
            String devicename,@Query("registration_id") String registration);

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














}
