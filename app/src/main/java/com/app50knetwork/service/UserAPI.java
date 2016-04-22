package com.app50knetwork.service;

import android.content.Context;
import android.util.Log;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.AppUser;
import com.app50knetwork.model.User;
import com.app50knetwork.util.AppPrefrences;
import com.app50knetwork.util.RestUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class UserAPI {

    public static void createUser(final Context context, final AppCallback appCallback, AppUser user){
        String token = AppPrefrences.getAccessToken(context);
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
        String token = AppPrefrences.getClientToken(context);
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
}
