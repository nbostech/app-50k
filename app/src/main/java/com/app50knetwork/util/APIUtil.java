package com.app50knetwork.util;

import com.app50knetwork.model.AppUser;
import com.app50knetwork.model.Associate;
import com.app50knetwork.model.Company;
import com.app50knetwork.model.CompanyProfile;
import com.app50knetwork.model.Event;
import com.app50knetwork.model.MediaFile;
import com.app50knetwork.model.Metadata;
import com.app50knetwork.model.TeamType;
import com.app50knetwork.model.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @Headers("Content-Type: application/json")
    @POST(AppConstants.userAppSignupURL)
    Call<User> createUser(@Header("Authorization") String authorization, @Body AppUser user);


    @GET(AppConstants.aboutUsURL)
    Call<ResponseBody> getAboutUs(@Header("Authorization") String authorization);


    @GET(AppConstants.compCategorieListURL)
    Call<ArrayList<Metadata>> getCategories(@Header("Authorization") String authorization);

    @GET(AppConstants.compStageListURL)
    Call<ArrayList<Metadata>> getStages(@Header("Authorization") String authorization);

    @GET(AppConstants.associateTeamTypesURL)
    Call<ArrayList<TeamType>> getAssociateTypes(@Header("Authorization") String authorization);


    @POST(AppConstants.companyURL)
    Call<Company> createCompany(@Header("Authorization") String authorization, @Body CompanyProfile companyProfile);

    @PUT(AppConstants.companyURL)
    Call<Company> updateCompany(@Header("Authorization") String authorization, @Body Company company);

    @GET(AppConstants.companyURL)
    Call<ArrayList<Company>> getCompanies(@Header("Authorization") String authorization);




    @GET(AppConstants.companyAssociatesURL)
    Call<ArrayList<Associate>> getAssociates(@Header("Authorization") String authorization, @Path("companyId") Long companyId);

    @POST(AppConstants.companyAssociatesURL)
    Call<Associate> createAssociate(@Header("Authorization") String authorization, @Path("companyId") Long companyId,@Body Associate associate);

    @PUT(AppConstants.companyAssociateURL)
    Call<Associate> updateAssociate(@Header("Authorization") String authorization, @Path("companyId") String companyId, @Path("associateId") String associateId);

    @GET(AppConstants.companyAssociateURL)
    Call<Associate> getAssociate(@Header("Authorization") String authorization, @Path("companyId") String companyId, @Path("associateId") String associateId);

    @DELETE(AppConstants.companyAssociateURL)
    Call<Associate> deleteAssociate(@Header("Authorization") String authorization, @Path("companyId") String companyId, @Path("associateId") String associateId);

    @Multipart
    @POST(AppConstants.mediaURL)
    Call<MediaFile> uploadMedia(@Header("Authorization") String authorization, @Part("id") RequestBody companyId, @Part("media_for") RequestBody mediaType, @Part MultipartBody.Part file);

    @POST(AppConstants.userSigninURL)
    Call<List<User>> loginApp(@Header("Authorization") String authorization, @Body JsonObject uuidString);

}
