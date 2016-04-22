package com.wavelabs.usermodule.ConnectionAPI;

/**
 * Created by vineelanalla on 16/03/16.
 */
public interface LoginCallback {
    void onSuccess(String html);

    void onFailure(String error);

}
