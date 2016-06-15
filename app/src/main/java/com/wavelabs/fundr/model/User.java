package com.wavelabs.fundr.model;

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
    private List<CompanyProfile> wishlistCompanies;
    private List<CompanyProfile> protifolioCompanies;
    private List<Metadata> areaOfInterests;
    private List<DomainExp> domainExpertises;


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

    public List<CompanyProfile> getWishlistCompanies() {
        return wishlistCompanies;
    }

    public void setWishlistCompanies(List<CompanyProfile> wishlistCompanies) {
        this.wishlistCompanies = wishlistCompanies;
    }

    public List<CompanyProfile> getProtifolioCompanies() {
        return protifolioCompanies;
    }

    public void setProtifolioCompanies(List<CompanyProfile> protifolioCompanies) {
        this.protifolioCompanies = protifolioCompanies;
    }

    public List<Metadata> getAreaOfInterests() {
        return areaOfInterests;
    }

    public void setAreaOfInterests(List<Metadata> areaOfInterests) {
        this.areaOfInterests = areaOfInterests;
    }

    public List<DomainExp> getDomainExpertises() {
        return domainExpertises;
    }

    public void setDomainExpertises(List<DomainExp> domainExpertises) {
        this.domainExpertises = domainExpertises;
    }
}
