package com.ATG.World.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * This class manages all the code related to the information saved locally in
 * {@link SharedPreferences}.
 */
//@Singleton
public class AtgPreferences {
    /*private static final String IS_VERIFIED = "is_verified";
    private final SharedPreferences preferences;
    private final String COOKIE = "cookie";
    private final String PROFILE_PIC = "profile_pic";
    private final String USER_ID = "user_id";
    private final String USER_PASSWORD = "user_password";
    private final String EMAIL = "email";
    private final String NAME = "name";
    private final String PHONE_NUMBER = "phone_number";
    private final String IS_REGISTERED = "is_registered";
    private final String IS_LOGGED_IN = "is_logged_in";
    private final String COUNTRY_CODE = "country_code";

    @Inject
    AtgPreferences(@Named("app") Context appContext) {
        final String appName = appContext.getPackageName();
        preferences = appContext.getSharedPreferences(appName, Context.MODE_PRIVATE);
    }

    @NonNull
    Set<String> getCookie() {
        return preferences.getStringSet(COOKIE, new HashSet<>());
    }

    void setCookie(@NonNull final Set<String> cookie) {
        preferences.edit().putStringSet(COOKIE, cookie).apply();
    }


    String getUserId() {
        return preferences.getString(USER_ID, "");
    }

    void setUserId(final String userId) {
        preferences.edit().putString(USER_ID, userId).apply();
    }

    @NonNull
    String getUserPassword() {
        return preferences.getString(USER_PASSWORD, "");
    }

    public void setUserPassword(@NonNull String userPassword) {
        preferences.edit().putString(USER_PASSWORD, userPassword).apply();
    }

    @NonNull
    public String getEmail() {
        return preferences.getString(EMAIL, "");
    }

    public void setEmail(@NonNull String email) {
        preferences.edit().putString(EMAIL, email).apply();
    }

    @NonNull
    public String getName() {
        return preferences.getString(NAME, "");
    }

    public void setName(@NonNull final String name) {
        preferences.edit().putString(NAME, name).apply();
    }

    boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGGED_IN, false);
    }

    void setLoggedIn(boolean isLoggedIn) {
        preferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply();
    }

    boolean isRegistrationComplete() {
        return preferences.getBoolean(IS_REGISTERED, false);
    }

    void setRegistrationComplete(final boolean registrationComplete) {
        preferences.edit().putBoolean(IS_REGISTERED, registrationComplete).apply();
    }


    public String getProfilePic() {
        return preferences.getString(PROFILE_PIC, "");
    }

    public void setProfilePic(String profilePic) {
        preferences.edit().putString(PROFILE_PIC, profilePic).apply();
    }

    public String getPhoneNumber() {
        return preferences.getString(PHONE_NUMBER, "");
    }

    public void setPhoneNumber(String phoneNumber) {
        preferences.edit().putString(PHONE_NUMBER, phoneNumber).apply();
    }

    public boolean isVerified() {
        return preferences.getBoolean(IS_VERIFIED, false);
    }

    public void setVerified(boolean verified) {
        preferences.edit().putBoolean(IS_VERIFIED, isLoggedIn()).apply();
    }

    public String getCountryCode() {
        return preferences.getString(COUNTRY_CODE, "");
    }

    public void setCountryCode(String countryCode) {
        preferences.edit().putString(COUNTRY_CODE, countryCode).apply();
    }*/
}
