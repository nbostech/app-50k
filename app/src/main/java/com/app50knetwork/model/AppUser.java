package com.app50knetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashkumar on 4/17/2016.
 */

public class AppUser implements Serializable{
    @SerializedName("user_type")
    private String userType;
    @SerializedName("idn_id")
    private long idnId;
    @SerializedName("details")
    private AppUserDetails details;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public long getIdnId() {
        return idnId;
    }

    public void setIdnId(long idnId) {
        this.idnId = idnId;
    }

    public AppUserDetails getDetails() {
        return details;
    }

    public void setDetails(AppUserDetails details) {
        this.details = details;
    }



}

