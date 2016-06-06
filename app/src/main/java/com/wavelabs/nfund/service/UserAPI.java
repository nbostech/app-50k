package com.wavelabs.nfund.service;

import android.content.Context;
import android.util.Log;

import com.wavelabs.nfund.model.AppUser;
import com.wavelabs.nfund.model.Profile;
import com.wavelabs.nfund.model.User;
import com.wavelabs.nfund.util.RestUtil;
import com.google.gson.JsonObject;

import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.Utils.Prefrences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class UserAPI {

    public static void createUser(final Context context, AppUser user, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<User> createUserCall = RestUtil.getAPIUtil().createUser(token, user);
        createUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }


    public static void getUsers(final Context context, String userType, final NBOSCallback nbosCallback) {
        String token = Prefrences.getClientToken(context);
        Call<ResponseBody> getUsersCall = RestUtil.getAPIUtil().getUsers(token, userType);
        getUsersCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public static void getUserDetails(final Context context, String userId, final NBOSCallback nbosCallback) {
        String token = Prefrences.getClientToken(context);
        Call<ResponseBody> getUsersCall = RestUtil.getAPIUtil().getUserDetails(token, userId);
        getUsersCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public static void login(final Context context, JsonObject uuid, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<List<User>> appLogin = RestUtil.getAPIUtil().loginApp(token, uuid);
        appLogin.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 200) {
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    public static void updateUserProfile(final Context context, Profile profile, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<Profile> createUserCall = RestUtil.getAPIUtil().updateUser(token, profile.getId(), profile);
        createUserCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }


    public static void logout(final Context context, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<ResponseBody> appLogin = RestUtil.getAPIUtil().logoutApp(token);
        appLogin.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
