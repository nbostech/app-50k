package com.app50knetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.AppUser;
import com.app50knetwork.model.AppUserDetails;
import com.app50knetwork.service.UserAPI;

import java.util.List;

import in.wavelabs.startersdk.ConnectionAPI.AuthApi;
import in.wavelabs.startersdk.ConnectionAPI.NBOSCallback;
import in.wavelabs.startersdk.DataModel.member.NewMemberApiModel;
import in.wavelabs.startersdk.DataModel.validation.ValidationMessagesApiModel;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setElevation(0);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EFEFEF")));

        investorRadioBtn = (RadioButton) findViewById(R.id.investorRB);
        startUpRadioBtn = (RadioButton) findViewById(R.id.startupRB);
        userFullName = (EditText) findViewById(R.id.fullname);
        companyName = (EditText) findViewById(R.id.company);
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
            userFullName.setHint("Full name");
            companyName.setHint("Company name");
        } else if (selectedRoleOption.equals("startUp")) {
            startUpRadioBtn.setChecked(true);
            userFullName.setHint("Founder name");
            companyName.setHint("Startup name");
        }
        roleOptionRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.investorRB) {
                    userFullName.setHint("Full name");
                    companyName.setHint("Company name");

                } else if (checkedId == R.id.startupRB) {
                    userFullName.setHint("Founder name");
                    companyName.setHint("Startup name");
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
            public void onSuccess(Response<NewMemberApiModel> response) {
                Intent intent = new Intent(RegisterActivity.this, InvestorMainActivity.class);
                startActivity(intent);
                NewMemberApiModel newMember = response.body();

                AppUser appUser = new AppUser();

                appUser.setIdnId(newMember.getMember().getId());
                appUser.setUserType("investor");
                AppUserDetails details = new AppUserDetails();
                details.setFullName(newMember.getMember().getFirstName()+" "+newMember.getMember().getLastName());
                details.setCompanyName(companyName.getText().toString());
                details.setEmail(newMember.getMember().getEmail());
                details.setContactNumber(newMember.getMember().getPhone()+"");
                appUser.setDetails(details);

                String requestJson = "{ \"user\":{ " +
                        "\"user_type\":\"investor\", " +
                        "\"idn_id\": "+newMember.getMember().getId()+", " +
                        "\"details\":{ \"email\":\""+newMember.getMember().getEmail()+"\", " +
                        "\"contact_number\":\""+newMember.getMember().getPhone()+"\", " +
                        "\"company_name\":\""+companyName.getText().toString()+"\", " +
                        "\"full_name\":\""+newMember.getMember().getFirstName()+" "+newMember.getMember().getLastName()+"\" } } }";




                if (response.isSuccessful()) {
                    UserAPI.createUser(RegisterActivity.this, new AppCallback() {
                        @Override
                        public void onSuccess(Response response) {
                            if (response.isSuccessful()){
                                Intent intent = new Intent(RegisterActivity.this, InvestorMainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }

                        @Override
                        public void authenticationError(String authenticationError) {

                        }

                        @Override
                        public void unknowError(String unknowError) {

                        }
                    },appUser);


                }


            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterActivity.this, R.string.networkError, Toast.LENGTH_SHORT).show();

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
