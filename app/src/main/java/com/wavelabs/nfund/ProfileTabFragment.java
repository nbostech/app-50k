package com.wavelabs.nfund;

/**
 * Created by ashkumar on 5/7/2016.
 */

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.wavelabs.nfund.model.Company;
import com.wavelabs.nfund.model.Metadata;
import com.wavelabs.nfund.service.CompanyAPI;
import com.wavelabs.nfund.service.MetadataAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.Utils.Prefrences;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileTabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";




    EditText companyNameET;
    EditText companyEmailET;
    EditText contactNumberET;
    Spinner industryS;
    Button compSubmitBtn;
    Button logoUploadBtn;
    private ProgressBar spinner;
    Company company;
    ImageView imageView;

    HashMap<Long,String> categoryMap = new HashMap<>();
    ArrayList<String> industryList = new ArrayList<>();
    ArrayAdapter<String> adapter;


    public ProfileTabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProfileTabFragment newInstance(int sectionNumber) {
        ProfileTabFragment fragment = new ProfileTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_profile_tab, container, false);
        company =((CompanyProfileActivity)getActivity()).company;

        industryS = (Spinner) view.findViewById(R.id.industry);
        companyEmailET = (EditText) view.findViewById(R.id.companyEmail);
        companyNameET = (EditText) view.findViewById(R.id.companyName);
        contactNumberET = (EditText) view.findViewById(R.id.contactNumber);
        compSubmitBtn = (Button)view.findViewById(R.id.compSubmitBtn);
        logoUploadBtn = (Button) view.findViewById(R.id.logoUploadBtn);

        imageView = (ImageView) view.findViewById(R.id.companyLogoIV);
        industryList = new ArrayList<>(categoryMap.values());
        industryList.add("select industry");
        adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, industryList);
        industryS.setAdapter(adapter);

        if(industryList.size()<=1) {
            //Activate Progress bar spinner
            ((RelativeLayout)((CompanyProfileActivity)getActivity()).findViewById(R.id.progressBar1)).setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            MetadataAPI.getCompCategories(getActivity(), new NBOSCallback<ArrayList<Metadata>>() {


                @Override
                public void onResponse(Response<ArrayList<Metadata>> response) {
                    categoryMap = Metadata.getMapFromList(response.body());
                    industryList = new ArrayList<>(categoryMap.values());
                    industryList.add(0, "select industry");
                    adapter =
                            new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, industryList);
                    industryS.setAdapter(adapter);
                    if((((CompanyProfileActivity) getActivity()).company.getProfile().getCategory())!=null)
                        industryS.setSelection(adapter.getPosition(((CompanyProfileActivity) getActivity()).company.getProfile().getCategory()));
                    //Deactivate Progress bar spinner
                    ((CompanyProfileActivity) getActivity()).layout.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }

                @Override
                public void onFailure(Throwable t) {

                }

            });
        }



        compSubmitBtn.setText("UPDATE");
        companyNameET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getName());
        companyEmailET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getEmail());
        contactNumberET.setText(((CompanyProfileActivity) getActivity()).company.getProfile().getContactNumber());
        if(company.getLogoImage()!=null)
            Picasso.with(imageView.getContext()).load(company.getLogoImage().getMediaFileURLStr("medium")).into(imageView);
        else
            Picasso.with(imageView.getContext()).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100").into(imageView);


        logoUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                getActivity().startActivityForResult(i, ((CompanyProfileActivity) getActivity()).RESULT_LOAD_IMAGE);


            }
        });




        companyNameET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setName(s.toString()); }
        });
        companyEmailET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setEmail(s.toString()); }
        });
        contactNumberET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { ((CompanyProfileActivity) getActivity()).company.getProfile().setContactNumber(s.toString()); }
        });


        compSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                company.getProfile().setEmail(companyEmailET.getText().toString());
                company.getProfile().setName(companyNameET.getText().toString());
                company.getProfile().setContactNumber(contactNumberET.getText().toString());
                company.getProfile().setCategory(industryS.getSelectedItem().toString());
                company.getProfile().setFounderName(Prefrences.getFirstName(getActivity())+ " "+Prefrences.getLastName(getActivity()));

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

        return view;
    }




}
