package com.app50knetwork.service;

import android.content.Context;
import android.util.Log;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.AppUser;
import com.app50knetwork.model.User;
import com.app50knetwork.util.RestUtil;
import com.google.gson.JsonObject;

import java.util.List;

import in.wavelabs.startersdk.Utils.Prefrences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class UserAPI {

    public static void createUser(final Context context, final AppCallback appCallback, AppUser user){
        String token = Prefrences.getAccessToken(context);
        Call<User> createUserCall = RestUtil.getAPIUtil().createUser(token,user);
        createUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    Log.d("response",response.code()+"");
                    Log.d("response",response.body().toString());
                    appCallback.onSuccess(response);
                }else{
                    appCallback.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }


    public static void getUsers(final Context context,final AppCallback appCallback,String userType){
        String token = Prefrences.getClientToken(context);
        Call<ResponseBody>getUsersCall = RestUtil.getAPIUtil().getUsers(token,userType);
        getUsersCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Log.d("response",response.code()+"");
                    Log.d("response",response.body().toString());
                    appCallback.onSuccess(response);
                }else{
                    appCallback.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public static void login(final Context context, final AppCallback appCallback, JsonObject uuid){
        String token =  Prefrences.getAccessToken(context);
        Call<List<User>>appLogin = RestUtil.getAPIUtil().loginApp(token,uuid);
        appLogin.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 200) {
                    appCallback.onSuccess(response);
                }else{
                    appCallback.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
