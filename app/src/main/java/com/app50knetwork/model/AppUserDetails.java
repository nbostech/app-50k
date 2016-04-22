package com.app50knetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashkumar on 4/17/2016.
 */
public class AppUserDetails implements Serializable{

        private String email;
        @SerializedName("contact_number")
        private String contactNumber;
        @SerializedName("company_name")
        private String companyName;
        @SerializedName("full_name")
        private String fullName;

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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fulllName) {
            this.fullName = fulllName;
        }

}
