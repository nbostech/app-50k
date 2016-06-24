package com.wavelabs.fundr.service;

import android.content.Context;
import android.util.Log;

import com.thefinestartist.utils.content.Res;
import com.wavelabs.fundr.model.AppUser;
import com.wavelabs.fundr.model.Profile;
import com.wavelabs.fundr.model.User;
import com.wavelabs.fundr.model.UuidModel;
import com.wavelabs.fundr.util.RestUtil;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.utils.TokenPrefrences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class UserAPI {

    public static void createUser(final Context context, AppUser user, final NBOSCallback<User> nbosCallback) {
        String token = TokenPrefrences.getAccessToken(context);
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


    public static void getUsers(final Context context, String userType, final NBOSCallback<ResponseBody> nbosCallback) {
        String token = TokenPrefrences.getClientToken(context);
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

    public static void getUserDetails(final Context context, String userId, final NBOSCallback<ResponseBody> nbosCallback) {
        String token = TokenPrefrences.getClientToken(context);
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

    public static void login(final Context context, UuidModel uuid, final NBOSCallback<User> nbosCallback) {
        String token = TokenPrefrences.getAccessToken(context);
        Call<User> appLogin = RestUtil.getAPIUtil().loginApp(token, uuid);
        appLogin.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public static void updateUserProfile(final Context context, Profile profile, final NBOSCallback<Profile> nbosCallback) {
        String token = TokenPrefrences.getAccessToken(context);
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


    public static void logout(final Context context, final NBOSCallback<ResponseBody> nbosCallback) {
        String token = TokenPrefrences.getAccessToken(context);
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
