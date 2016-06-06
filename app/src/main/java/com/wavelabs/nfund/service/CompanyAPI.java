package com.wavelabs.nfund.service;

import android.content.Context;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.wavelabs.nfund.model.Associate;
import com.wavelabs.nfund.model.Company;
import com.wavelabs.nfund.model.CompanyProfile;
import com.wavelabs.nfund.model.MediaFile;
import com.wavelabs.nfund.util.RestUtil;

import java.io.File;
import java.util.ArrayList;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.Utils.Prefrences;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/29/2016.
 */
public class CompanyAPI {

    public static void createCompany(final Context context, CompanyProfile companyProfile, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<Company> companyCall = RestUtil.getAPIUtil().createCompany(token, companyProfile);
        companyCall.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                nbosCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });

    }


    public static void updateCompanyProfile(final Context context, Long companyId, CompanyProfile companyProfile, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<Company> companyCall = RestUtil.getAPIUtil().updateCompanyProfile(token, companyId, companyProfile);
        companyCall.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                nbosCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });

    }

    public static void getCompanies(final Context context, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<ArrayList<Company>> companyListCall = RestUtil.getAPIUtil().getCompanies(token);

        companyListCall.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> call, Response<ArrayList<Company>> response) {

                if (response.code() == 200) {

                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Company>> call, Throwable t) {

                nbosCallback.onFailure(t);
            }
        });

    }

    public static void uploadMedia(final Context context, String companyId, String mediaFor, File file, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);

        RequestBody idValue =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), companyId);
        RequestBody mediaForValue =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), mediaFor);

        String extension = MimeTypeMap.getFileExtensionFromUrl(file.getPath());


        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image_file", file.getName(), requestFile);

        Call<MediaFile> mediaUploadCall = RestUtil.getAPIUtil().uploadMedia(token, idValue, mediaForValue, body);
        mediaUploadCall.enqueue(new Callback<MediaFile>() {
            @Override
            public void onResponse(Call<MediaFile> call, Response<MediaFile> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<MediaFile> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }

    public static void createAssoicate(final Context context, Long companyId, Associate associate, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<Associate> companyCall = RestUtil.getAPIUtil().createAssociate(token, companyId, associate);
        companyCall.enqueue(new Callback<Associate>() {
            @Override
            public void onResponse(Call<Associate> call, Response<Associate> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<Associate> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }


    public static void updateAssoicate(final Context context, Long associateId, Associate associate, final NBOSCallback nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<Associate> companyCall = RestUtil.getAPIUtil().updateAssociate(token, associateId, associate);
        companyCall.enqueue(new Callback<Associate>() {
            @Override
            public void onResponse(Call<Associate> call, Response<Associate> response) {
                if (response.code() == 200) {
                    Log.d("response", response.code() + "");
                    Log.d("response", response.body().toString());
                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<Associate> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }

    public static void getAssociates(final Context context, Long companyId, final NBOSCallback<ArrayList<Associate>> nbosCallback) {
        String token = Prefrences.getAccessToken(context);
        Call<ArrayList<Associate>> companyListCall = RestUtil.getAPIUtil().getAssociates(token, companyId);

        companyListCall.enqueue(new Callback<ArrayList<Associate>>() {
            @Override
            public void onResponse(Call<ArrayList<Associate>> call, Response<ArrayList<Associate>> response) {

                if (response.code() == 200) {

                    nbosCallback.onResponse(response);
                } else {
                    nbosCallback.onResponse(response);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Associate>> call, Throwable t) {

                nbosCallback.onFailure(t);
            }
        });

    }


}
