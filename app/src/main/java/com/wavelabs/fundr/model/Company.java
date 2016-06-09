package com.wavelabs.fundr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashkumar on 5/6/2016.
 */

public class Company implements Serializable{
    private Long id;
    private CompanyProfile profile;

    private MediaFile logoImage;
    private MediaFile brandImage;
    private List<String> addressList = new ArrayList<>();
    private List<String> documentsList = new ArrayList<>();
    private List<Associate> associates = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyProfile getProfile() {
        return profile;
    }

    public void setProfile(CompanyProfile profile) {
        this.profile = profile;
    }

    public MediaFile getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(MediaFile logoImage) {
        this.logoImage = logoImage;
    }

    public MediaFile getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(MediaFile brandImage) {
        this.brandImage = brandImage;
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    public List<String> getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(List<String> documentsList) {
        this.documentsList = documentsList;
    }

    public List<Associate> getAssociates() {
        return associates;
    }

    public void setAssociates(List<Associate> associates) {
        this.associates = associates;
    }
}
