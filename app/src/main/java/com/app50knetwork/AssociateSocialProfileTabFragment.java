package com.app50knetwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.Associate;
import com.app50knetwork.service.CompanyAPI;

import retrofit2.Response;

/**
 * Created by ashkumar on 5/7/2016.
 */
public class AssociateSocialProfileTabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private EditText associateSocialFBET;
    private EditText associateSocialLIET;
    private EditText associateSocialTET;
    private EditText associateSocialOET;

    Associate associate;
    Long companyId;

    Button associateSubmitBtn;


    public AssociateSocialProfileTabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AssociateSocialProfileTabFragment newInstance(int sectionNumber) {
        AssociateSocialProfileTabFragment fragment = new AssociateSocialProfileTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_associate_socialprofile_tab, container, false);
        associate =((EditAssociateActivity)getActivity()).associate;
        companyId = ((EditAssociateActivity) getActivity()).companyId;

        associateSocialFBET = (EditText)rootView.findViewById(R.id.associateSocialFBET);
        associateSocialLIET = (EditText)rootView.findViewById(R.id.associateSocialLIET);
        associateSocialTET = (EditText)rootView.findViewById(R.id.associateSocialTET);
        associateSocialOET = (EditText)rootView.findViewById(R.id.associateSocialOET);

        associateSocialFBET.setText(((EditAssociateActivity) getActivity()).associate.getFacebookProfile());
        associateSocialLIET.setText(((EditAssociateActivity) getActivity()).associate.getLinkedinProfile());
        associateSocialTET.setText(((EditAssociateActivity) getActivity()).associate.getTwitterProfile());
        associateSocialOET.setText(((EditAssociateActivity) getActivity()).associate.getOtherProfile());

        associateSubmitBtn = (Button) rootView.findViewById(R.id.associateSubmitBtn);

        associateSocialFBET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((EditAssociateActivity) getActivity()).associate.setFacebookProfile(new String(s.toString())); }
        });
        associateSocialLIET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((EditAssociateActivity) getActivity()).associate.setLinkedinProfile(new String(s.toString())); }
        });
        associateSocialTET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((EditAssociateActivity) getActivity()).associate.setTwitterProfile(new String(s.toString())); }
        });
        associateSocialOET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((EditAssociateActivity) getActivity()).associate.setOtherProfile(new String(s.toString())); }
        });


        associateSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                associate.setFacebookProfile(associateSocialFBET.getText().toString());
                associate.setLinkedinProfile(associateSocialLIET.getText().toString());
                associate.setTwitterProfile(associateSocialTET.getText().toString());
                associate.setOtherProfile(associateSocialOET.getText().toString());


                CompanyAPI.updateAssoicate(getActivity(), new AppCallback() {
                    @Override
                    public void onSuccess(Response response) {

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
                }, associate.getId(),associate);
            }
        });
        return rootView;
    }
}
