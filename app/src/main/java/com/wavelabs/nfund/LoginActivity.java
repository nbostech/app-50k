package com.wavelabs.nfund;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.wavelabs.nfund.model.AppUser;
import com.wavelabs.nfund.model.User;
import com.wavelabs.nfund.service.UserAPI;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.UUID;

import in.wavelabs.idn.ConnectionAPI.AuthApi;
import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.ConnectionAPI.SocialApi;
import in.wavelabs.idn.DataModel.member.NewMemberApiModel;
import in.wavelabs.idn.Utils.Prefrences;
import retrofit2.Response;

/**
 * A login screen that offers login via AccountKit.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int FRAMEWORK_REQUEST_CODE = 1;
    private String initialStateParam;
    Dialog LoadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(getWindow().FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_login);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button accountKitConnect = (Button) findViewById(R.id.loginWithPhone);
        accountKitConnect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin(LoginType.PHONE);
            }
        });

    }
    private void onLogin(final LoginType loginType) {
        final Intent intent = new Intent(LoginActivity.this, AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(loginType, AccountKitActivity.ResponseType.CODE);
        configurationBuilder.setFacebookNotificationsEnabled(true);
        configurationBuilder.setReadPhoneStateEnabled(true);
        configurationBuilder.setReceiveSMS(true);

        initialStateParam = UUID.randomUUID().toString();
        configurationBuilder.setInitialAuthState(initialStateParam);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, FRAMEWORK_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FRAMEWORK_REQUEST_CODE) {
            final AccountKitLoginResult loginResult =
                    data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            final String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
               // Toast.makeText(LoginActivity.this,toastMessage,Toast.LENGTH_SHORT).show();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
             //   Toast.makeText(LoginActivity.this,toastMessage,Toast.LENGTH_SHORT).show();
            } else {
                final String authorizationCode = loginResult.getAuthorizationCode();
                final long tokenRefreshIntervalInSeconds = loginResult.getTokenRefreshIntervalInSeconds();
                if (authorizationCode != null) {
                    toastMessage = String.format(
                            "Success:%s...",
                            authorizationCode.substring(0, 10));
                   // Toast.makeText(LoginActivity.this,toastMessage,Toast.LENGTH_SHORT).show();

                    accountKitConnect("accountKit",authorizationCode, loginResult.getFinalAuthorizationState());
                } else {
                    toastMessage = "Unknown response type";
                 //   Toast.makeText(LoginActivity.this,toastMessage,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void accountKitConnect(String service, String code,String state){
        SocialApi.authorizeAndConnect(LoginActivity.this, service, code, state, new NBOSCallback<NewMemberApiModel>() {

            @Override
            public void onResponse(Response<NewMemberApiModel> response) {
                Log.d("Register", (response.body().getToken().getAccess_token()));
                Log.d("Register", (response.body().getToken().getRefresh_token()));
                if (response.isSuccessful()) {
                    Prefrences.setAccessToken(getApplicationContext(), "Bearer " + response.body().getToken().getAccess_token());
                    loginTok50k(response.body().getMember().getUuid());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    private void loginTok50k(final String uuid){
        UserAPI.getUserDetails(LoginActivity.this, uuid, new NBOSCallback() {
            @Override
            public void onResponse(Response response) {
                if (response.code() == 200) {
                    Log.d("test", "existing user");
                    existingUserLogin(uuid);
                } else if (response.code() == 404) {
                    Log.d("test", "new user");
                    FragmentManager fm = getSupportFragmentManager();
                    UserTypeFragment userTypeFragmentDialogFrag = new UserTypeFragment();
                    Bundle args = new Bundle();
                    args.putString("uuid", uuid);
                    userTypeFragmentDialogFrag.setArguments(args);
                    userTypeFragmentDialogFrag.show(fm, "userTypeFragment");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
    }

    private void existingUserLogin(String uuid){
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
    }
    public void showLoadingDialog() {

        LoadingDialog = new Dialog(LoginActivity.this);
        LoadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        LoadingDialog.setContentView(R.layout.loading_dialog);

        LoadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LoadingDialog.setCancelable(false);
        LoadingDialog.setCanceledOnTouchOutside(false);
        LoadingDialog.show();
    }
}
