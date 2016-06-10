package com.wavelabs.fundr.util;

import com.wavelabs.fundr.model.AboutUs;
import com.wavelabs.fundr.model.AppUser;
import com.wavelabs.fundr.model.Associate;
import com.wavelabs.fundr.model.Company;
import com.wavelabs.fundr.model.CompanyProfile;
import com.wavelabs.fundr.model.DomainExp;
import com.wavelabs.fundr.model.Event;
import com.wavelabs.fundr.model.MediaFile;
import com.wavelabs.fundr.model.Metadata;
import com.wavelabs.fundr.model.Profile;
import com.wavelabs.fundr.model.TeamType;
import com.wavelabs.fundr.model.User;
import com.wavelabs.fundr.model.UuidModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ashkumar on 4/16/2016.
 */
public interface APIUtil {

    @GET(AppConstants.publicEventUrl)
    Call<List<Event>> getEvents(@Header("Authorization") String authorization);

    @GET(AppConstants.userUrl)
    Call<ResponseBody> getUsers(@Header("Authorization") String authorization, @Query("user_type") String userType);

    @GET(AppConstants.user1Url)
    Call<ResponseBody> getUserDetails(@Header("Authorization") String authorization, @Path("userId") String userId);

    @Headers("Content-Type: application/json")
    @POST(AppConstants.userAppSignupURL)
    Call<User> createUser(@Header("Authorization") String authorization, @Body AppUser user);

    @PUT(AppConstants.user1Url)
    Call<Profile> updateUser(@Header("Authorization") String authorization, @Path("userId") Long userId, @Body Profile profile);


    @GET(AppConstants.aboutUsURL)
    Call<AboutUs> getAboutUs();

    @GET(AppConstants.compCategorieListURL)
    Call<ArrayList<Metadata>> getCategories(@Header("Authorization") String authorization);

    @GET(AppConstants.compStageListURL)
    Call<ArrayList<Metadata>> getStages(@Header("Authorization") String authorization);

    @GET(AppConstants.associateTeamTypesURL)
    Call<ArrayList<TeamType>> getAssociateTypes(@Header("Authorization") String authorization);




    @POST(AppConstants.companyURL)
    Call<Company> createCompany(@Header("Authorization") String authorization, @Body CompanyProfile companyProfile);

    @PUT(AppConstants.companyProfileURL)
    Call<Company> updateCompanyProfile(@Header("Authorization") String authorization, @Path("companyId") Long companyId, @Body CompanyProfile companyProfile);

    @GET(AppConstants.companyURL)
    Call<ArrayList<Company>> getCompanies(@Header("Authorization") String authorization, @Field("company_type") String companyType);


    @GET(AppConstants.companyAssociatesURL)
    Call<ArrayList<Associate>> getAssociates(@Header("Authorization") String authorization, @Path("companyId") Long companyId);

    @POST(AppConstants.companyAssociatesURL)
    Call<Associate> createAssociate(@Header("Authorization") String authorization, @Path("companyId") Long companyId, @Body Associate associate);

    @PUT(AppConstants.associateURL)
    Call<Associate> updateAssociate(@Header("Authorization") String authorization, @Path("associateId") Long associateId, @Body Associate associate);

    @GET(AppConstants.associateURL)
    Call<Associate> getAssociate(@Header("Authorization") String authorization, @Path("associateId") String associateId);

    @DELETE(AppConstants.associateURL)
    Call<Associate> deleteAssociate(@Header("Authorization") String authorization, @Path("associateId") String associateId);

    @Multipart
    @POST(AppConstants.mediaURL)
    Call<MediaFile> uploadMedia(@Header("Authorization") String authorization, @Part("id") RequestBody companyId, @Part("media_for") RequestBody mediaType, @Part MultipartBody.Part file);

    @POST(AppConstants.userSigninURL)
    Call<User> loginApp(@Header("Authorization") String authorization, @Body UuidModel uuidString);

    @GET(AppConstants.userSignoutURL)
    Call<ResponseBody> logoutApp(@Header("Authorization") String authorization);

    @GET(AppConstants.tenantInfo)
    Call<ResponseBody> getTenantInfo(@Header("Authorization") String authorization);

    @GET(AppConstants.domainExpListURL)
    Call<List<DomainExp>> getDomainExpList(@Header("Authorization") String authorization);

}
