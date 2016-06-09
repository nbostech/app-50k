package com.wavelabs.fundr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ashkumar on 6/6/2016.
 */
public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        String refreshToken = Prefrences.getRefreshToken(this);
        if(refreshToken!=null && refreshToken.length()>0) {
            UserAPI.getUserAccessToken(this, WaveLabsSdk.getClientId(this,
                    WaveLabsSdk.APPLICATION_ID_PROPERTY),
                    WaveLabsSdk.getClientSecret(this, WaveLabsSdk.APPLICATION_SECRET_PROPERTY),
                    refreshToken,
                    new NBOSCallback<TokenApiModel>() {
                        @Override
                        public void onResponse(Response<TokenApiModel> response) {
                            Prefrences.setAccessToken(getApplicationContext(),
                                    "Bearer " + response.body().getAccess_token());
                            Prefrences.setAccessToken(getApplicationContext(),
                                    response.body().getRefresh_token());

                            UsersApi.getUserProfile(SplashActivity.this, new NBOSCallback<MemberApiModel>() {
                                @Override
                                public void onResponse(Response<MemberApiModel> response) {
                                    JsonObject uuid = new JsonObject();
                                    uuid.addProperty("uuid", response.body().getUuid());
                                    UserAPI.login(SplashActivity.this, new NBOSCallback<User>() {
                                        @Override
                                        public void onResponse(Response<User> response) {
                                            User user = response.body();

                                            if (user.getUserTypes() != null && user.getUserTypes().size() > 0 &&
                                                    user.getUserTypes().get(0).getName().equalsIgnoreCase("startup")) {
                                                Intent i = new Intent(SplashActivity.this, StartupMainActivity.class);
                                                i.putExtra("user", user);
                                                startActivity(i);
                                            } else if (user.getUserTypes() != null && user.getUserTypes().size() > 0 &&
                                                    user.getUserTypes().get(0).getName().equalsIgnoreCase("investor")) {
                                                Intent i = new Intent(SplashActivity.this, InvestorMainActivity.class);
                                                startActivity(i);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Throwable t) {

                                        }
                                    }, uuid);
                                }

                                @Override
                                public void onFailure(Throwable t) {

                                }
                            });


                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });


                    Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
