package com.app50knetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import in.wavelabs.startersdk.ConnectionAPI.AuthApi;
import in.wavelabs.startersdk.ConnectionAPI.NBOSCallback;
import in.wavelabs.startersdk.ConnectionAPI.SocialApi;
import in.wavelabs.startersdk.DataModel.member.NewMemberApiModel;
import in.wavelabs.startersdk.DataModel.validation.ValidationMessagesApiModel;
import in.wavelabs.startersdk.Utils.Prefrences;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {




    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

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






    private void linkedInConnect(String service){
        SocialApi.socialLogin(LoginActivity.this,LoginActivity.this, service, new NBOSCallback<NewMemberApiModel>() {

            @Override
            public void onSuccess(Response<NewMemberApiModel> response) {

                Prefrences.setAccessToken(LoginActivity.this, "Bearer " + response.body().getToken().getAccess_token());
                Prefrences.setAccessToken(LoginActivity.this, "Bearer " + response.body().getToken().getAccess_token());

            }

            @Override
            public void onFailure(Throwable t) {

            }

            @Override
            public void onValidationError(List<ValidationMessagesApiModel> validationError) {

            }

            @Override
            public void authenticationError(String authenticationError) {

            }

            @Override
            public void unknownError(String unknownError) {

            }
        });
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }




    private void login(){

        AuthApi.login(LoginActivity.this, mEmailView.getText().toString(),mPasswordView.getText().toString(),
                new NBOSCallback<NewMemberApiModel>() {
                    @Override
                    public void onSuccess(Response<NewMemberApiModel> response) {
                        Log.d("Register",(response.body().getToken().getAccess_token()));
                        Log.d("Register",(response.body().getToken().getRefresh_token()));
                        if(response.isSuccessful()){

                            Intent i = new Intent(LoginActivity.this, InvestorMainActivity.class);

                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(LoginActivity.this,R.string.networkError, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onValidationError(List<ValidationMessagesApiModel> validationError) {

                    }

                    @Override
                    public void authenticationError(String authenticationError) {

                    }

                    @Override
                    public void unknownError(String unknownError) {

                    }


                });


    }



}

