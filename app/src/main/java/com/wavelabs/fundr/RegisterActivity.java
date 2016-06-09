package com.wavelabs.fundr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wavelabs.fundr.model.AppUser;
import com.wavelabs.fundr.service.UserAPI;

import in.wavelabs.idn.ConnectionAPI.AuthApi;
import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.DataModel.member.NewMemberApiModel;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    String selectedRoleOption;
    RadioGroup roleOptionRG;
    RadioButton investorRadioBtn;
    RadioButton startUpRadioBtn;
    EditText userFullName;
    EditText companyName;
    EditText email;
    EditText password;
    EditText contactNumber;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setElevation(0);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EFEFEF")));

        investorRadioBtn = (RadioButton) findViewById(R.id.investorRB);
        startUpRadioBtn = (RadioButton) findViewById(R.id.startupRB);
        userFullName = (EditText) findViewById(R.id.fullname);
        //companyName = (EditText) findViewById(R.id.company);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        contactNumber = (EditText) findViewById(R.id.contactNo);
        roleOptionRG = (RadioGroup) findViewById(R.id.userRoleRBGroup);
        signUpBtn = (Button) findViewById(R.id.email_sign_up_button);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedRoleOption = extras.getString("selectedRoleOption");

        }

        if (selectedRoleOption.equals("investor")) {
            investorRadioBtn.setChecked(true);

        } else if (selectedRoleOption.equals("startUp")) {
            startUpRadioBtn.setChecked(true);

        }
        roleOptionRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.investorRB) {
                    selectedRoleOption = "investor";

                } else if (checkedId == R.id.startupRB) {
                    selectedRoleOption = "startup";
                }

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(userFullName.getText().toString(), "", email.getText().toString(), email.getText().toString(), password.getText().toString());


            }

        });


    }

    private void createAccount(String firstName, String lastName, String username, String email, String password) {


        AuthApi.createAccount(RegisterActivity.this, email, username, firstName, lastName, password, new NBOSCallback<NewMemberApiModel>() {

            @Override
            public void onResponse(Response<NewMemberApiModel> response) {
                Intent intent = new Intent(RegisterActivity.this, InvestorMainActivity.class);
                startActivity(intent);
                NewMemberApiModel newMember = response.body();

                AppUser appUser = new AppUser();

                appUser.setUuid(newMember.getMember().getUuid());
                appUser.setContactNumber(contactNumber.getText().toString());
                appUser.setEmail(newMember.getMember().getEmail());
                appUser.setFullName(newMember.getMember().getFirstName() + " " + newMember.getMember().getLastName());
                appUser.setUserType(selectedRoleOption);


                if (response.isSuccessful()) {
                    UserAPI.createUser(RegisterActivity.this, appUser, new NBOSCallback() {


                        @Override
                        public void onResponse(Response response) {
                            Log.d("test", "success" + response.code());
                            if (response.isSuccessful()) {
                                if (selectedRoleOption.equalsIgnoreCase("investor")) {
                                    Intent intent = new Intent(RegisterActivity.this, InvestorMainActivity.class);
                                    startActivity(intent);
                                } else if (selectedRoleOption.equalsIgnoreCase("startup")) {
                                    Intent intent = new Intent(RegisterActivity.this, StartupMainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.d("test", "onFailure" + t.getMessage());
                        }

                    });


                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterActivity.this, R.string.networkError, Toast.LENGTH_SHORT).show();

            }


        });

    }

}
