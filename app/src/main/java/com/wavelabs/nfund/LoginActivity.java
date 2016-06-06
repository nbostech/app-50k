package com.wavelabs.nfund;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wavelabs.nfund.model.AppUser;
import com.wavelabs.nfund.model.User;
import com.wavelabs.nfund.service.UserAPI;
import com.google.gson.JsonObject;

import java.util.List;

import in.wavelabs.idn.ConnectionAPI.AuthApi;
import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.ConnectionAPI.SocialApi;
import in.wavelabs.idn.DataModel.member.NewMemberApiModel;
import in.wavelabs.idn.Utils.Prefrences;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements UserTypeFragment.OnUserTypeFragmentInteractionListener {


    // UI references.
    private EditText mEmailView, mPasswordView;
    private View mProgressView, mLoginFormView;
    AppUser appUser;
    JsonObject uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(getWindow().FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_login);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().hide();
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        Button linkedInConnect = (Button) findViewById(R.id.linkedInConnect);
        linkedInConnect.setOnClickListener(new OnClickListener() {
            /*
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                UserTypeFragment userTypeFragmentDialogFrag = new UserTypeFragment ();
                userTypeFragmentDialogFrag.show(fm,"userTypeFragment");
            }
            */


            @Override
            public void onClick(View v) {
                linkedInConnect("linkedIn");
            }

        });

        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void linkedInConnect(String service) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);

        SocialApi.socialLogin(LoginActivity.this, service, new NBOSCallback<NewMemberApiModel>() {

            @Override
            public void onResponse(Response<NewMemberApiModel> response) {

                Prefrences.setAccessToken(LoginActivity.this, "Bearer " + response.body().getToken().getAccess_token());
                if (response.isSuccessful()) {

                    Prefrences.setAccessToken(getApplicationContext(), "Bearer " + response.body().getToken().getAccess_token());

                    if (response.code() == 200) {
                        appUser = new AppUser();
                        appUser.setFullName(response.body().getMember().getFirstName() + " " +
                                "" + response.body().getMember().getLastName());
                        appUser.setEmail(response.body().getMember().getEmail());
                        appUser.setContactNumber(response.body().getMember().getPhone() + "");
                        appUser.setUuid(response.body().getMember().getUuid());

                        FragmentManager fm = getSupportFragmentManager();
                        UserTypeFragment userTypeFragmentDialogFrag = new UserTypeFragment();
                        userTypeFragmentDialogFrag.show(fm, "userTypeFragment");


                    } else if (response.code() == 400) {
                        login();
                    }

                    Log.d("test", response.body().getMember().getUuid());


                }
            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }

    @Override

    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {

        String service = data.getStringExtra("service");
        String code = data.getStringExtra("code");
        String state = data.getStringExtra("state");
        SocialApi.authorizeAndConnect(LoginActivity.this, service, code, state, new NBOSCallback<NewMemberApiModel>() {
            @Override
            public void onResponse(Response<NewMemberApiModel> response) {
                if (response.code() == 200) {
                    appUser = new AppUser();
                    appUser.setFullName(response.body().getMember().getFirstName() + " " +
                            "" + response.body().getMember().getLastName());
                    appUser.setEmail(response.body().getMember().getEmail());
                    appUser.setContactNumber(response.body().getMember().getPhone() + "");
                    appUser.setUuid(response.body().getMember().getUuid());
                    uuid = new JsonObject();
                    uuid.addProperty("uuid", response.body().getMember().getUuid());
                    UserAPI.getUserDetails(LoginActivity.this, appUser.getUuid(), new NBOSCallback() {
                        @Override
                        public void onResponse(Response response) {
                            if (response.code() == 200) {
                                Log.d("test", "existing linked in user");
                                UserAPI.login(LoginActivity.this, uuid, new NBOSCallback<List<User>>() {
                                    @Override
                                    public void onResponse(Response<List<User>> response) {
                                        User user = response.body().get(0);

                                        if (user.getUserTypes() != null && user.getUserTypes().size() > 0 &&
                                                user.getUserTypes().get(0).getName().equalsIgnoreCase("startup")) {
                                            Intent i = new Intent(LoginActivity.this, StartupMainActivity.class);
                                            startActivity(i);
                                        } else if (user.getUserTypes() != null && user.getUserTypes().size() > 0 &&
                                                user.getUserTypes().get(0).getName().equalsIgnoreCase("investor")) {
                                            Intent i = new Intent(LoginActivity.this, InvestorMainActivity.class);
                                            startActivity(i);
                                        }

                                    }

                                    @Override
                                    public void onFailure(Throwable t) {

                                    }

                                });
                            } else if (response.code() == 404) {
                                Log.d("test", "new linked in user");
                                FragmentManager fm = getSupportFragmentManager();
                                UserTypeFragment userTypeFragmentDialogFrag = new UserTypeFragment();
                                userTypeFragmentDialogFrag.show(fm, "userTypeFragment");
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }

                    });


                }
            }

            @Override
            public void onFailure(Throwable t) {

            }


        });


    }


    private void login() {

        AuthApi.login(LoginActivity.this, mEmailView.getText().toString(), mPasswordView.getText().toString(),
                new NBOSCallback<NewMemberApiModel>() {
                    @Override
                    public void onResponse(Response<NewMemberApiModel> response) {
                        Log.d("Register", (response.body().getToken().getAccess_token()));
                        Log.d("Register", (response.body().getToken().getRefresh_token()));
                        if (response.isSuccessful()) {

                            Prefrences.setAccessToken(getApplicationContext(), "Bearer " + response.body().getToken().getAccess_token());

                            JsonObject uuid = new JsonObject();
                            uuid.addProperty("uuid", response.body().getMember().getUuid());
                            UserAPI.login(LoginActivity.this, uuid, new NBOSCallback<List<User>>() {

                                @Override
                                public void onResponse(Response<List<User>> response) {
                                    User user = response.body().get(0);

                                    if (user.getUserTypes() != null && user.getUserTypes().size() > 0 &&
                                            user.getUserTypes().get(0).getName().equalsIgnoreCase("startup")) {
                                        Intent i = new Intent(LoginActivity.this, StartupMainActivity.class);
                                        i.putExtra("user", user);
                                        startActivity(i);
                                    } else if (user.getUserTypes() != null && user.getUserTypes().size() > 0 &&
                                            user.getUserTypes().get(0).getName().equalsIgnoreCase("investor")) {
                                        Intent i = new Intent(LoginActivity.this, InvestorMainActivity.class);
                                        startActivity(i);
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Log.d("error", t.getMessage());
                                }


                            });


                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(LoginActivity.this, R.string.networkError, Toast.LENGTH_SHORT).show();

                    }

                });


    }


    @Override
    public void onUserTypeFragmentInteraction(Uri uri) {

    }
}

