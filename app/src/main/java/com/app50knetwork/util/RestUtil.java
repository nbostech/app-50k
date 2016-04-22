package com.app50knetwork.util;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class RestUtil {

    private static final HashMap<Class, Object> apiClients = new HashMap<>();
    private static final String ROOT = AppConstants.appURL;
    static {
        setupRestClient(APIUtil.class);
    }

    public static APIUtil getAPIUtil(){
        return (APIUtil) apiClients.get(APIUtil.class);
    }

    private static void setupRestClient(Class type) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClients.put(type, retrofit.create(type));

    }
}
