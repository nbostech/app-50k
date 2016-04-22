package com.app50knetwork.model;
import retrofit2.Response;

/**
 * Created by ashkumar on 4/16/2016.
 */
public interface AppCallback<T> {

    void onSuccess(Response<T> response);
    void onFailure(Throwable t);
    void authenticationError(String authenticationError);
    void unknowError(String unknowError);

}
