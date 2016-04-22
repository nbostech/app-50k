package com.app50knetwork.util;

import com.app50knetwork.model.AppUser;
import com.app50knetwork.model.Event;
import com.app50knetwork.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
    @POST(AppConstants.userAppSignup)
    Call<User> createUser(@Header("Authorization") String authorization, @Body AppUser user);
}
