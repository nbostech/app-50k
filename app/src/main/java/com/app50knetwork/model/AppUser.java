package com.app50knetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashkumar on 4/17/2016.
 */

public class AppUser implements Serializable{



    @SerializedName("user_type")
    private String userType;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("email")
    private String email;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("location")
    private String location;
    @SerializedName("website")
    private String website;
    @SerializedName("profile_summary")
    private String profileSummary;
    @SerializedName("linkedIn_profile")
    private String linkedInProfile;
    @SerializedName("facebook_profile")
    private String facebookProfile;
    @SerializedName("twitter_profile")
    private String twitterProfile;
    @SerializedName("other_profile")
    private String otherProfile;
    @SerializedName("social_acounts")
    private String socialAccounts;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfileSummary() {
        return profileSummary;
    }

    public void setProfileSummary(String profileSummary) {
        this.profileSummary = profileSummary;
    }

    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public String getTwitterProfile() {
        return twitterProfile;
    }

    public void setTwitterProfile(String twitterProfile) {
        this.twitterProfile = twitterProfile;
    }

    public String getOtherProfile() {
        return otherProfile;
    }

    public void setOtherProfile(String otherProfile) {
        this.otherProfile = otherProfile;
    }

    public String getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(String socialAccounts) {
        this.socialAccounts = socialAccounts;
    }
}

