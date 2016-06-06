package com.wavelabs.nfund.service;

import android.content.Context;
import android.util.Log;

import com.wavelabs.nfund.model.Metadata;
import com.wavelabs.nfund.model.TeamType;
import com.wavelabs.nfund.util.RestUtil;

import java.util.ArrayList;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.Utils.Prefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 5/5/2016.
 */
public class MetadataAPI {

    public static void getCompCategories(final Context context, final NBOSCallback<ArrayList<Metadata>> nbosCallback){
        String token = Prefrences.getClientToken(context);
        Call<ArrayList<Metadata>> categoryList = RestUtil.getAPIUtil().getCategories(token);
        categoryList.enqueue(new Callback<ArrayList<Metadata>>() {
            @Override
            public void onResponse(Call<ArrayList<Metadata>> call, Response<ArrayList<Metadata>> response) {
                if (response.code() == 200) {
                    Log.d("response",response.code()+"");
                    Log.d("response",response.body().toString());
                    nbosCallback.onResponse(response);
                }else{
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Metadata>> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });
    }


    public static void getCompStages(final Context context, final NBOSCallback nbosCallback){
        String token = Prefrences.getClientToken(context);
        Call<ArrayList<Metadata>> stageList = RestUtil.getAPIUtil().getStages(token);
        stageList.enqueue(new Callback<ArrayList<Metadata>>() {
            @Override
            public void onResponse(Call<ArrayList<Metadata>> call, Response<ArrayList<Metadata>> response) {
                if (response.code() == 200) {
                    Log.d("response",response.code()+"");
                    Log.d("response",response.body().toString());
                    nbosCallback.onResponse(response);
                }else{
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Metadata>> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }

    public static void getAssociateTypes(final Context context, final NBOSCallback nbosCallback){
        String token = Prefrences.getClientToken(context);
        Call<ArrayList<TeamType>> stageList = RestUtil.getAPIUtil().getAssociateTypes(token);
        stageList.enqueue(new Callback<ArrayList<TeamType>>() {
            @Override
            public void onResponse(Call<ArrayList<TeamType>> call, Response<ArrayList<TeamType>> response) {
                if (response.code() == 200) {
                    Log.d("response",response.code()+"");
                    Log.d("response",response.body().toString());
                    nbosCallback.onResponse(response);
                }else{
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TeamType>> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }



}
