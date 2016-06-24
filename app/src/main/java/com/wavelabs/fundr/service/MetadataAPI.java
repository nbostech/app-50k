package com.wavelabs.fundr.service;

import android.content.Context;
import android.util.Log;

import com.wavelabs.fundr.model.DomainExp;
import com.wavelabs.fundr.model.Metadata;
import com.wavelabs.fundr.model.TeamType;
import com.wavelabs.fundr.util.RestUtil;

import java.util.List;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.utils.TokenPrefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 5/5/2016.
 */
public class MetadataAPI {

    public static void getCompCategories(final Context context, final NBOSCallback<List<Metadata>> nbosCallback) {
        String token = TokenPrefrences.getClientToken(context);
        Call<List<Metadata>> categoryList = RestUtil.getAPIUtil().getCategories(token);
        categoryList.enqueue(new Callback<List<Metadata>>() {
            @Override
            public void onResponse(Call<List<Metadata>> call, Response<List<Metadata>> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<List<Metadata>> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });
    }


    public static void getCompStages(final Context context, final NBOSCallback<List<Metadata>> nbosCallback) {
        String token = TokenPrefrences.getClientToken(context);
        Call<List<Metadata>> stageList = RestUtil.getAPIUtil().getStages(token);
        stageList.enqueue(new Callback<List<Metadata>>() {
            @Override
            public void onResponse(Call<List<Metadata>> call, Response<List<Metadata>> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<List<Metadata>> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }

    public static void getAssociateTypes(final Context context, final NBOSCallback<List<TeamType>> nbosCallback) {
        String token = TokenPrefrences.getClientToken(context);
        Call<List<TeamType>> stageList = RestUtil.getAPIUtil().getAssociateTypes(token);
        stageList.enqueue(new Callback<List<TeamType>>() {
            @Override
            public void onResponse(Call<List<TeamType>> call, Response<List<TeamType>> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<List<TeamType>> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }


    public static void getDomainExps(final Context context, final NBOSCallback<List<DomainExp>> nbosCallback) {
        String token = TokenPrefrences.getClientToken(context);
        Call<List<DomainExp>> stageList = RestUtil.getAPIUtil().getDomainExpList(token);
        stageList.enqueue(new Callback<List<DomainExp>>() {
            @Override
            public void onResponse(Call<List<DomainExp>> call, Response<List<DomainExp>> response) {
                if (response.code() == 200) {
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<List<DomainExp>> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }


}
