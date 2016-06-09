package com.wavelabs.fundr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wavelabs.fundr.model.Associate;
import com.wavelabs.fundr.service.CompanyAPI;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;

/**
 * Created by ashkumar on 5/7/2016.
 */
public class AssociateSummaryTabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private EditText associateExpAndExpET;
    private EditText associateSummaryEV;
    Button associateSubmitBtn;
    Associate associate;
    Long companyId;

    public AssociateSummaryTabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AssociateSummaryTabFragment newInstance(int sectionNumber) {
        AssociateSummaryTabFragment fragment = new AssociateSummaryTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_associate_summary_tab, container, false);
        associate = ((EditAssociateActivity) getActivity()).associate;
        companyId = ((EditAssociateActivity) getActivity()).companyId;

        associateExpAndExpET = (EditText) rootView.findViewById(R.id.associateExpAndExpET);
        associateSummaryEV = (EditText) rootView.findViewById(R.id.associateSummaryEV);

        associateExpAndExpET.setText(((EditAssociateActivity) getActivity()).associate.getExperience());
        associateSummaryEV.setText(((EditAssociateActivity) getActivity()).associate.getProfileSummary());

        associateSubmitBtn = (Button) rootView.findViewById(R.id.associateSubmitBtn);

        associateExpAndExpET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setExperience(s.toString());
            }
        });
        associateSummaryEV.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setProfileSummary(s.toString());
            }
        });


        associateSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                associateExpAndExpET.setText(((EditAssociateActivity) getActivity()).associate.getExperience());
                associateSummaryEV.setText(((EditAssociateActivity) getActivity()).associate.getProfileSummary());


                CompanyAPI.updateAssoicate(getActivity(), associate.getId(), associate, new NBOSCallback() {


                    @Override
                    public void onResponse(Response response) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }

                });
            }
        });
        return rootView;
    }
}
