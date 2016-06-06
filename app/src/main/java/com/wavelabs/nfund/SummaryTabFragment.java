package com.wavelabs.nfund;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wavelabs.nfund.model.Company;
import com.wavelabs.nfund.service.CompanyAPI;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;

/**
 * Created by ashkumar on 5/7/2016.
 */
public class SummaryTabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private EditText businessSummaryET;
    private EditText uspProductUnqinessET;
    Button compSubmitBtn;
    Company company;

    public SummaryTabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SummaryTabFragment newInstance(int sectionNumber) {
        SummaryTabFragment fragment = new SummaryTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_summary_tab, container, false);
        company =((CompanyProfileActivity)getActivity()).company;
        businessSummaryET = (EditText)rootView.findViewById(R.id.businessSummary);
        uspProductUnqinessET = (EditText)rootView.findViewById(R.id.productUniqueness);

        businessSummaryET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getBusinessSummary());
        uspProductUnqinessET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getProductUSP());

        compSubmitBtn = (Button) rootView.findViewById(R.id.compSubmitBtn);

        businessSummaryET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setBusinessSummary(new String(s.toString())); }
        });
        uspProductUnqinessET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setProductUSP(new String(s.toString())); }
        });


        compSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                company.getProfile().setPreviousCapital(businessSummaryET.getText().toString());
                company.getProfile().setMonthlyNetBurn(uspProductUnqinessET.getText().toString());


                CompanyAPI.updateCompanyProfile(getActivity(), new NBOSCallback() {

                    @Override
                    public void onResponse(Response response) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }

                }, company.getId(),company.getProfile());
            }
        });
        return rootView;
    }
}
