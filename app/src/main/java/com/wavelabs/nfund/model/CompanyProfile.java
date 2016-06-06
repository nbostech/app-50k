package com.wavelabs.nfund.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashkumar on 4/16/2016.
 */

public class CompanyProfile implements Serializable{


    @SerializedName("company_category")
    private String category;
    @SerializedName("company_stage")
    private String fundingStage;
    @SerializedName("currency_type")
    private String currencyCode;
    @SerializedName("startup_name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("emp_strength")
    private String empStrength;
    @SerializedName("founder_name")
    private String founderName;
    @SerializedName("location")
    private String location;
    @SerializedName("website")
    private String website;
    @SerializedName("business_summary")
    private String businessSummary;
    @SerializedName("description")
    private String description;
    @SerializedName("usb_product_uniqueness")
    private String productUSP;
    @SerializedName("date_founded")
    private String foundedDate;
    @SerializedName("year_founded")
    private String foundedYear;
    @SerializedName("linkedin_profile_url")
    private String linkedinProfileUrl;
    @SerializedName("twitter_profile_url")
    private String twitterProfileUrl;
    @SerializedName("facebook_profile_url")
    private String facebookProfileUrl;
    @SerializedName("other_profile_url")
    private String otherProfileUrl;
    @SerializedName("capital_raised")
    private String raisedCapital;
    @SerializedName("previous_capital")
    private String previousCapital;
    @SerializedName("monthly_net_burn")
    private String monthlyNetBurn;
    @SerializedName("pre_money_valuation")
    private String perMoneyValuation;
    @SerializedName("contact_number")
    private String contactNumber;



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFundingStage() {
        return fundingStage;
    }

    public void setFundingStage(String fundingStage) {
        this.fundingStage = fundingStage;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public String getEmpStrength() {
        return empStrength;
    }

    public void setEmpStrength(String empStrength) {
        this.empStrength = empStrength;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
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

    public String getBusinessSummary() {
        return businessSummary;
    }

    public void setBusinessSummary(String businessSummary) {
        this.businessSummary = businessSummary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductUSP() {
        return productUSP;
    }

    public void setProductUSP(String productUSP) {
        this.productUSP = productUSP;
    }

    public String getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(String foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(String foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getLinkedinProfileUrl() {
        return linkedinProfileUrl;
    }

    public void setLinkedinProfileUrl(String linkedinProfileUrl) {
        this.linkedinProfileUrl = linkedinProfileUrl;
    }

    public String getRaisedCapital() {
        return raisedCapital;
    }

    public void setRaisedCapital(String raisedCapital) {
        this.raisedCapital = raisedCapital;
    }

    public String getPreviousCapital() {
        return previousCapital;
    }

    public void setPreviousCapital(String previousCapital) {
        this.previousCapital = previousCapital;
    }

    public String getMonthlyNetBurn() {
        return monthlyNetBurn;
    }

    public void setMonthlyNetBurn(String monthlyNetBurn) {
        this.monthlyNetBurn = monthlyNetBurn;
    }

    public String getPerMoneyValuation() {
        return perMoneyValuation;
    }

    public void setPerMoneyValuation(String perMoneyValuation) {
        this.perMoneyValuation = perMoneyValuation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getTwitterProfileUrl() {
        return twitterProfileUrl;
    }

    public void setTwitterProfileUrl(String twitterProfileUrl) {
        this.twitterProfileUrl = twitterProfileUrl;
    }

    public String getFacebookProfileUrl() {
        return facebookProfileUrl;
    }

    public void setFacebookProfileUrl(String facebookProfileUrl) {
        this.facebookProfileUrl = facebookProfileUrl;
    }

    public String getOtherProfileUrl() {
        return otherProfileUrl;
    }

    public void setOtherProfileUrl(String otherProfileUrl) {
        this.otherProfileUrl = otherProfileUrl;
    }
}
