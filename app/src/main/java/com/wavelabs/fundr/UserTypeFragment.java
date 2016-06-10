package com.wavelabs.fundr;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wavelabs.fundr.helper.MultiSelectionSpinner;
import com.wavelabs.fundr.model.AppUser;
import com.wavelabs.fundr.model.DomainExp;
import com.wavelabs.fundr.model.MetaDataNameModel;
import com.wavelabs.fundr.model.Metadata;
import com.wavelabs.fundr.service.MetadataAPI;
import com.wavelabs.fundr.service.UserAPI;

import java.util.ArrayList;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;


public class UserTypeFragment extends DialogFragment {

    private RadioButton startUpRadioBtn;
    private EditText userNameET;
    private MultiSelectionSpinner areaOfInterestS,domainExpS0,domainExpS1;
    private TextView areaOfInterestLabel,domExpLabel;
    private List<DomainExp> domainExps = new ArrayList<>();
    private String selectedRoleOption;


    public UserTypeFragment() {
        // Required empty public constructor
    }

    public static UserTypeFragment newInstance() {
        UserTypeFragment fragment = new UserTypeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_type, container, false);
        final String uuid = getArguments().getString("uuid");
        areaOfInterestLabel = (TextView)view.findViewById(R.id.areaOfInterestLabel);
        domExpLabel = (TextView)view.findViewById(R.id.domainExpLabel);
        userNameET = (EditText)view.findViewById(R.id.fullname);
        final EditText phoneNumber = (EditText) view.findViewById(R.id.contactNumber);
        final EditText emailId = (EditText) view.findViewById(R.id.email);
        areaOfInterestS = (MultiSelectionSpinner)view.findViewById(R.id.areaOfInterest);
        domainExpS0 = (MultiSelectionSpinner)view.findViewById(R.id.domainExp0);
        domainExpS1 = (MultiSelectionSpinner)view.findViewById(R.id.domainExp1);
        RadioButton investorRadioBtn = (RadioButton) view.findViewById(R.id.investorRB);
        startUpRadioBtn = (RadioButton) view.findViewById(R.id.startupRB);
        RadioGroup roleOptionRG = (RadioGroup) view.findViewById(R.id.userRoleRBGroup);
        Button signUpBtn = (Button) view.findViewById(R.id.email_sign_up_button);
        roleOptionRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.investorRB) {
                    selectedRoleOption = "investor";
                    areaOfInterestLabel.setVisibility(View.VISIBLE);
                    domExpLabel.setVisibility(View.VISIBLE);
                    areaOfInterestS.setVisibility(View.VISIBLE);
                    domainExpS0.setVisibility(View.VISIBLE);
                    domainExpS1.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.startupRB) {
                    selectedRoleOption = "startup";
                    areaOfInterestLabel.setVisibility(View.GONE);
                    domExpLabel.setVisibility(View.GONE);
                    areaOfInterestS.setVisibility(View.GONE);
                    domainExpS0.setVisibility(View.GONE);
                    domainExpS1.setVisibility(View.GONE);
                }

            }
        });
        roleOptionRG.check(R.id.investorRB);
        selectedRoleOption = "investor";
//        if(((LoginActivity) getActivity()).appUser != null)
//            userNameET.setText(((LoginActivity) getActivity()).appUser.getFullName());
        MetadataAPI.getCompCategories(getActivity(), new NBOSCallback<ArrayList<Metadata>>() {
            @Override
            public void onResponse(Response<ArrayList<Metadata>> response) {

                areaOfInterestS.setItems(Metadata.getListValues(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        MetadataAPI.getDomainExps(getActivity(), new NBOSCallback<List<DomainExp>>() {
            @Override
            public void onResponse(Response<List<DomainExp>> response) {
                if(response.isSuccessful()){
                    domainExps = response.body();
                    if (domainExps != null && domainExps.size() > 0) {
                        domainExpS0.setItems(DomainExp.getRelatedListValues(domainExps,null));
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        domainExpS1.setVisibility(View.GONE);
        domainExpS0.getAdapter().registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {

                if(domainExpS0.getSelectedStrings()!=null && domainExpS0.getSelectedStrings().size()>0) {

                    domainExpS1.setItems(
                            DomainExp.getRelatedListValues(
                                    domainExps, DomainExp.getRelatedIds(
                                            domainExps, domainExpS0.getSelectedStrings())));
                    domainExpS1.setVisibility(View.VISIBLE);
                }else{
                    domainExpS1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        });


        investorRadioBtn.setChecked(true);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedRoleOption.equalsIgnoreCase("investor")) {

                    List<MetaDataNameModel> aoi = new ArrayList<>();
                    for (String selectedOptioin : areaOfInterestS.getSelectedStrings()) {
                        MetaDataNameModel metaDataNameModel = new MetaDataNameModel();
                        metaDataNameModel.setName(selectedOptioin);
                        aoi.add(metaDataNameModel);
                    }
                    List<MetaDataNameModel> de = new ArrayList<>();
                    for (String selectedOptioin : domainExpS1.getSelectedStrings()) {
                        MetaDataNameModel metaDataNameModel = new MetaDataNameModel();
                        metaDataNameModel.setName(selectedOptioin);
                        de.add(metaDataNameModel);
                    }

                    createAccountForInvestor(userNameET.getText().toString(),
                            uuid, emailId.getText().toString(),
                            phoneNumber.getText().toString(), aoi, de);


                } else {
                    createAcountForStartup(userNameET.getText().toString(),
                            uuid, emailId.getText().toString(),
                            phoneNumber.getText().toString());

                }
            }

        });

        return view;
    }
    private void createAcountForStartup(String fullName, String uuid, String email, String contactNumber){
        AppUser appUser = new AppUser();
        appUser.setUuid(uuid);
        appUser.setContactNumber(appUser.getContactNumber());
        appUser.setEmail(email);
        appUser.setFullName(fullName);
        appUser.setContactNumber(contactNumber);
        appUser.setUserType("startup");
        UserAPI.createUser(getActivity(), appUser, new NBOSCallback() {
            @Override
            public void onResponse(Response response) {
                Log.d("test", "success" + response.code());
                        Intent intent = new Intent((getActivity()), StartupMainActivity.class);
                        startActivity(intent);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("test", "onFailure" + t.getMessage());
            }

        });

    }

    private void createAccountForInvestor(String fullName, String uuid, String email, String contactNumber,
                                          List<MetaDataNameModel> aoiJO,List<MetaDataNameModel> deJO) {


        AppUser appUser = new AppUser();

        appUser.setUuid(uuid);
        appUser.setContactNumber(appUser.getContactNumber());
        appUser.setEmail(email);
        appUser.setFullName(fullName);
        appUser.setContactNumber(contactNumber);
        appUser.setUserType("investor");
        appUser.setAreaofInterests(aoiJO);
        appUser.setDomainExpertises(deJO);
        UserAPI.createUser(getActivity(), appUser, new NBOSCallback() {
            @Override
            public void onResponse(Response response) {
                Log.d("test", "success" + response.code());
                if (response.isSuccessful()) {
                        Intent intent = new Intent((getActivity()), InvestorMainActivity.class);
                        startActivity(intent);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("test", "onFailure" + t.getMessage());
            }

        });


    }
}
