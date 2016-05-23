package com.app50knetwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.Company;
import com.app50knetwork.model.Metadata;
import com.app50knetwork.service.CompanyAPI;
import com.app50knetwork.service.MetadataAPI;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by ashkumar on 5/7/2016.
 */
public  class FinancialTabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    EditText previousCapitalET;
    EditText monthlyNetBurnET;
    EditText preMoneyEvaluationET;
    Spinner companyStageS;
    Button compSubmitBtn;
    Company company;
    HashMap<Long,String> stageMap = new HashMap<Long,String>();
    ArrayList<String> stageList = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public FinancialTabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FinancialTabFragment newInstance(int sectionNumber) {
        FinancialTabFragment fragment = new FinancialTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_company_finfo_tab, container, false);
        company =((CompanyProfileActivity)getActivity()).company;
        previousCapitalET = (EditText) rootView.findViewById(R.id.previousCapital);
        monthlyNetBurnET = (EditText) rootView.findViewById(R.id.monthlyNetBurn);
        preMoneyEvaluationET = (EditText) rootView.findViewById(R.id.perMoneyValution);
        compSubmitBtn = (Button)rootView.findViewById(R.id.compSubmitBtn);
        companyStageS = (Spinner) rootView.findViewById(R.id.fundingStage);

        stageList = new ArrayList<String>(stageMap.values());
        stageList.add(0, "select stage");
        adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stageList);
        companyStageS.setAdapter(adapter);

        if(stageList.size()<=1) {
            //Activate Progress bar spinner
            ((RelativeLayout)((CompanyProfileActivity)getActivity()).findViewById(R.id.progressBar1)).setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            MetadataAPI.getCompStages(getActivity(), new AppCallback<ArrayList<Metadata>>() {
                @Override
                public void onSuccess(Response<ArrayList<Metadata>> response) {
                    stageMap = Metadata.getMapFromList(response.body());
                    stageList = new ArrayList<String>(stageMap.values());
                    stageList.add(0, "select stage");
                    adapter =
                            new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stageList);
                    companyStageS.setAdapter(adapter);
                    if((((CompanyProfileActivity) getActivity()).company.getProfile().getFundingStage()!=null))
                        companyStageS.setSelection(adapter.getPosition(((CompanyProfileActivity) getActivity()).company.getProfile().getFundingStage()));
                    //Deactivate Progress bar spinner
                    ((CompanyProfileActivity) getActivity()).layout.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
            });
        }




        previousCapitalET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getPreviousCapital());
        monthlyNetBurnET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getMonthlyNetBurn());
        preMoneyEvaluationET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getPerMoneyValuation());

        previousCapitalET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setPreviousCapital(new String(s.toString())); }
        });
        monthlyNetBurnET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setMonthlyNetBurn(new String(s.toString())); }
        });
        preMoneyEvaluationET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setPerMoneyValuation(new String(s.toString())); }
        });


        compSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                company.getProfile().setPreviousCapital(previousCapitalET.getText().toString());
                company.getProfile().setMonthlyNetBurn(monthlyNetBurnET.getText().toString());
                company.getProfile().setPerMoneyValuation(preMoneyEvaluationET.getText().toString());
                company.getProfile().setFundingStage(companyStageS.getSelectedItem().toString());

                CompanyAPI.updateCompanyProfile(getActivity(), new AppCallback() {
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
                }, company.getId(),company.getProfile());
            }
        });


        return rootView;
    }
}
