package com.wavelabs.fundr.service;

import android.content.Context;
import android.util.Log;

import com.wavelabs.fundr.util.RestUtil;
import com.wavelabs.fundr.model.AboutUs;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/29/2016.
 */
public class AppAPI {

    public static void getAboutUs(final Context context, final NBOSCallback<AboutUs> appCallback) {
        Call<AboutUs> aboutUsRes = RestUtil.getAPIUtil().getAboutUs();
        aboutUsRes.enqueue(new Callback<AboutUs>() {
            @Override
            public void onResponse(Call<AboutUs> call, Response<AboutUs> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    appCallback.onResponse(response);
                } else {
                    appCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<AboutUs> call, Throwable t) {
                appCallback.onFailure(t);
            }
        });
    }
}
