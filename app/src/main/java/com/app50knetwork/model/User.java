package com.app50knetwork.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class User implements Serializable{

    private long id;
    private String uuid;
    private Profile profile;
    private List<Metadata> userTypes;
    private List<CompanyProfile> startupCompanies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Metadata> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<Metadata> userTypes) {
        this.userTypes = userTypes;
    }

    public List<CompanyProfile> getStartupCompanies() {
        return startupCompanies;
    }

    public void setStartupCompanies(List<CompanyProfile> startupCompanies) {
        this.startupCompanies = startupCompanies;
    }
}
