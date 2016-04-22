package com.app50knetwork.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class Profile {

    private long id;
    @SerializedName("full_name")
    private String fullName;
    private String email;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("company_name")
    private String companyName;
    private String location;
    private String website;
    @SerializedName("profile_summary")
    private String profileSummary;
    @SerializedName("profile_links")
    private String profileLinks;
    @SerializedName("social_accounts")
    private String socialAccounts;
    @SerializedName("idn_image_url")
    private String idnImageUrl;
    @SerializedName("user_id")
    private String userId;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getProfileLinks() {
        return profileLinks;
    }

    public void setProfileLinks(String profileLinks) {
        this.profileLinks = profileLinks;
    }

    public String getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(String socialAccounts) {
        this.socialAccounts = socialAccounts;
    }

    public String getIdnImageUrl() {
        return idnImageUrl;
    }

    public void setIdnImageUrl(String idnImageUrl) {
        this.idnImageUrl = idnImageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
