package com.wavelabs.nfund.service;

import android.content.Context;
import android.util.Log;

import com.wavelabs.nfund.model.Event;
import com.wavelabs.nfund.util.RestUtil;

import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.Utils.Prefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class EventAPI {

    public static void getEvents(final Context context, final NBOSCallback appCallback) {
        String token = Prefrences.getClientToken(context);
        Call<List<Event>> eventListCall = RestUtil.getAPIUtil().getEvents(token);

        eventListCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    appCallback.onResponse(response);
                } else {
                    appCallback.onResponse(response);
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

                appCallback.onFailure(t);
            }
        });

    }

}
