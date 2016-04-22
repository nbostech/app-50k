package com.app50knetwork.service;

import android.content.Context;
import android.util.Log;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.Event;
import com.app50knetwork.util.AppPrefrences;
import com.app50knetwork.util.RestUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class EventAPI {

    public static void getEvents(final Context context, final AppCallback appCallback){
        String token = AppPrefrences.getClientToken(context);
        Call<List<Event>> eventListCall = RestUtil.getAPIUtil().getEvents(token);

        eventListCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.code() == 200) {
                    Log.d("response",response.code()+"");
                    Log.d("response",response.body().toString());
                    appCallback.onSuccess(response);
                }else{
                    appCallback.onSuccess(response);
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

                appCallback.onFailure(t);
            }
        });

    }

}
