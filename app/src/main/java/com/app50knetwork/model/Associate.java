package com.app50knetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashkumar on 5/6/2016.
 */
public class Associate implements Serializable{

    @SerializedName("associate_type")
    private String associateType;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("postion")
    private String position;
    @SerializedName("experience_and_expertise")
    private String experience;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("location")
    private String location;
    @SerializedName("website")
    private String website;
    @SerializedName("linkedin_profile")
    private String linkedinProfile;
    @SerializedName("twitter_profile")
    private String twitterProfile;

    public String getAssociateType() {
        return associateType;
    }

    public void setAssociateType(String associateType) {
        this.associateType = associateType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
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

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public String getTwitterProfile() {
        return twitterProfile;
    }

    public void setTwitterProfile(String twitterProfile) {
        this.twitterProfile = twitterProfile;
    }
}
