package com.app50knetwork.service;

import android.content.Context;
import android.util.Log;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.util.RestUtil;

import in.wavelabs.startersdk.Utils.Prefrences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/29/2016.
 */
public class AppAPI {

    public static void getAboutUs(final Context context, final AppCallback appCallback) {
        String token = Prefrences.getClientToken(context);
        Call<ResponseBody> aboutUsRes = RestUtil.getAPIUtil().getAboutUs(token);
        aboutUsRes.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    appCallback.onSuccess(response);
                } else {
                    appCallback.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                appCallback.onFailure(t);
            }
        });
    }
}
