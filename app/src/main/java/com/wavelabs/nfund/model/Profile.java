package com.wavelabs.nfund.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class Profile implements Serializable{

    private long id;
    @SerializedName("full_name")
    private String fullName;
    private String email;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("location")
    private String location;
    @SerializedName("website")
    private String website;
    @SerializedName("profile_summary")
    private String profileSummary;
    @SerializedName("linkedin_profile")
    private String linkedInProfile;
    @SerializedName("facebook_profile")
    private String facebookProfile;
    @SerializedName("twitter_profile")
    private String twitterProfile;
    @SerializedName("other_profile")
    private String otherProfile;
    @SerializedName("social_accounts")
    private String socialAccounts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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



    public String getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(String socialAccounts) {
        this.socialAccounts = socialAccounts;
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
}
