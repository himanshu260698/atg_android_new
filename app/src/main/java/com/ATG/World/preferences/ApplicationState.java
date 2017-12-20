package com.ATG.World.preferences;

import android.support.annotation.NonNull;

import java.util.Set;

/**
 * This class is the interface between {@link AtgPreferences} and other classes. It is used so
 * that an item once queried from {//@link SharedPreferences}, which are maintained for this app
 * through {@link AtgPreferences}, is not queried again and maintained in memory.
 * <p>
 * It also stores the temporary states which shouldn't be stored in {//@link SharedPreferences}.
 */
@SuppressWarnings("unused")
//@Singleton
public final class ApplicationState {

    /*private final AtgPreferences preferences;
    private Set<String> cookie;
    private String profilePic;
    private String userId;
    private String name;
    private String email;

    private String password;
    private String countryCode;
    private String phoneNumber;
    private boolean isLoggedIn;
    private boolean isVerified;

    @Inject
    public ApplicationState(AtgPreferences preferences) {
        this.preferences = preferences;
        this.userId = "";
    }

    @NonNull
    public Set<String> getCookie() {
        if (cookie == null) cookie = preferences.getCookie();
        return cookie;
    }

    public void setCookie(@NonNull final Set<String> cookie) {
        preferences.setCookie(cookie);
        this.cookie = cookie;
    }

    public String getProfilePic() {
        if (StringUtil.isBlank(profilePic)) profilePic = preferences.getProfilePic();
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        preferences.setProfilePic(profilePic);
        this.profilePic = profilePic;
    }

    public String getUserId() {
        if (StringUtil.isBlank(userId)) userId = preferences.getUserId();
        return userId;
    }

    public void setUserId(final String userId) {
        preferences.setUserId(userId);
        this.userId = userId;
    }

    @NonNull
    public String getPassword() {
        if (StringUtil.isBlank(password)) password = preferences.getUserPassword();
        return password;
    }

    public void setPassword(final String password) {
        preferences.setUserPassword(password);
        this.password = password;
    }

    @NonNull
    public String getName() {
        if (StringUtil.isBlank(name)) name = preferences.getName();
        return name;
    }

    public void setName(@NonNull final String name) {
        preferences.setName(name);
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        if (StringUtil.isBlank(email)) email = preferences.getEmail();
        return email;
    }

    public void setEmail(final String email) {
        preferences.setEmail(email);
        this.email = email;
    }

    public String getPhoneNumber() {
        if (StringUtil.isBlank(phoneNumber)) phoneNumber = preferences.getPhoneNumber();
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        preferences.setPhoneNumber(phoneNumber);
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        preferences.setVerified(verified);
        this.isVerified = verified;
    }

    public boolean isLoggedIn() {
        return preferences.isLoggedIn();
    }

    public void setLoggedIn(boolean isLoggedIn) {
        preferences.setLoggedIn(isLoggedIn);
    }

    public boolean isRegistrationComplete() {
        return preferences.isRegistrationComplete();
    }

    public void setRegistrationComplete(final boolean registrationComplete) {
        preferences.setRegistrationComplete(registrationComplete);
    }

    public String getCountryCode() {
        if (StringUtil.isBlank(countryCode)) countryCode = preferences.getCountryCode();
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        preferences.setCountryCode(countryCode);
        this.countryCode = countryCode;
    }*/
}

