package com.app50knetwork.model;

import java.io.Serializable;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class User implements Serializable{

    private long id;
    private String uuid;
    private Profile profile;

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
}
