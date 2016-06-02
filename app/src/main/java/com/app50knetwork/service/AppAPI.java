package com.app50knetwork.service;

import android.content.Context;
import android.util.Log;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.util.RestUtil;
import com.app50knetwork.model.AboutUs;

import in.wavelabs.startersdk.Utils.Prefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/29/2016.
 */
public class AppAPI {

    public static void getAboutUs(final Context context, final AppCallback appCallback) {
        Call<AboutUs> aboutUsRes = RestUtil.getAPIUtil().getAboutUs();
        aboutUsRes.enqueue(new Callback<AboutUs>() {
            @Override
            public void onResponse(Call<AboutUs> call, Response<AboutUs> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    appCallback.onSuccess(response);
                } else {
                    appCallback.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<AboutUs> call, Throwable t) {
                appCallback.onFailure(t);
            }
        });
    }
}
