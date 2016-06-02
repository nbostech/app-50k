package com.app50knetwork.util;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class AppConstants {
    public static final String appURL="http://fundr.api.qa1.nbos.in";
    public static final String idnURL="http://api.qa1.nbos.in";
    public static final String baseURL = appURL+"/api/startup-fundraising/v0";
    public static final String userAppSignupURL = appURL + "/api/startup-fundraising/v0/users/signup";
    public static final String publicEventUrl = appURL + "/api/events/v0/TNT:vijaym/events";
    public static final String userUrl = appURL + "/api/startup-fundraising/v0/users";
    public static final String user1Url = appURL + "/api/startup-fundraising/v0/users/{userId}";
    public static final String aboutUsURL = "http://50knetwork.com/wp-json/wp/v2/pages/35";
    public static final String companyURL = appURL + "/api/startup-fundraising/v0/companies";
    public static final String companyProfileURL = companyURL + "/{companyId}/profile";
    public static final String updateUserProfileURL = userUrl + "/{userId}";
    public static final String userLogin = appURL + userUrl + "/signin";
    public static final String mediaURL = appURL + "/api/startup-fundraising/v0/media";
    public static final String logoutURL = idnURL + "/api/identity/v0/auth/logout";
    public static final String compCategorieListURL = appURL + "/api/startup-fundraising/v0/company/categories";
    public static final String compStageListURL = appURL + "/api/startup-fundraising/v0/company/stages";
    public static final String associateTeamTypesURL = appURL +"/api/startup-fundraising/v0/associates/teams";
    public static final String compCategoriesURL = appURL + "/api/startup-fundraising/v0/company/categories";
    public static final String companyAssociatesURL = companyURL + "/{companyId}/associates";
    public static final String associateURL = baseURL + "/associate/{associateId}";
    public static final String userSigninURL = userUrl + "/signin";
    public static final String userSignoutURL = userUrl +"/signout";


    public static final String blogURL = "http://50knetwork.com/blog/";


}
