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
}

