package com.wavelabs.nfund;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wavelabs.nfund.helper.MultiSelectionSpinner;
import com.wavelabs.nfund.model.AppUser;
import com.wavelabs.nfund.model.DomainExp;
import com.wavelabs.nfund.model.Metadata;
import com.wavelabs.nfund.service.MetadataAPI;
import com.wavelabs.nfund.service.UserAPI;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserTypeFragment.OnUserTypeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserTypeFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RadioGroup roleOptionRG;
    RadioButton investorRadioBtn;
    RadioButton startUpRadioBtn;
    EditText userNameET;
    MultiSelectionSpinner areaOfInterestS;
    MultiSelectionSpinner domainExpS0;
    MultiSelectionSpinner domainExpS1;
    TextView areaOfInterestLabel;
    TextView domExpLabel;
    Button signUpBtn;

    List<DomainExp> domainExps = new ArrayList<DomainExp>();
    String selectedRoleOption;

    private OnUserTypeFragmentInteractionListener mListener;

    public UserTypeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserTypeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserTypeFragment newInstance(String param1, String param2) {
        UserTypeFragment fragment = new UserTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_type, container, false);

        areaOfInterestLabel = (TextView)view.findViewById(R.id.areaOfInterestLabel);
        domExpLabel = (TextView)view.findViewById(R.id.domainExpLabel);
        userNameET = (EditText)view.findViewById(R.id.fullname);
        areaOfInterestS = (MultiSelectionSpinner)view.findViewById(R.id.areaOfInterest);
        domainExpS0 = (MultiSelectionSpinner)view.findViewById(R.id.domainExp0);
        domainExpS1 = (MultiSelectionSpinner)view.findViewById(R.id.domainExp1);
        investorRadioBtn = (RadioButton) view.findViewById(R.id.investorRB);
        startUpRadioBtn = (RadioButton) view.findViewById(R.id.startupRB);
        roleOptionRG = (RadioGroup) view.findViewById(R.id.userRoleRBGroup);
        signUpBtn = (Button) view.findViewById(R.id.email_sign_up_button);
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
        if(((LoginActivity) getActivity()).appUser != null)
            userNameET.setText(((LoginActivity) getActivity()).appUser.getFullName());
        MetadataAPI.getCompCategories((LoginActivity) getActivity(), new NBOSCallback<ArrayList<Metadata>>() {
            @Override
            public void onResponse(Response<ArrayList<Metadata>> response) {

                areaOfInterestS.setItems(Metadata.getListValues(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        MetadataAPI.getDomainExps((LoginActivity)getActivity(), new NBOSCallback<List<DomainExp>>() {
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

                JSONArray aoiJsonArray = new JSONArray();
                for(String selectedOptioin: areaOfInterestS.getSelectedStrings()) {
                    JsonObject name = new JsonObject();
                    name.addProperty("name", selectedOptioin);
                    aoiJsonArray.put(name);
                }
                JSONArray deJsonArray = new JSONArray();
                for(String selectedOptioin: domainExpS1.getSelectedStrings()) {
                    JsonObject name = new JsonObject();
                    name.addProperty("name", selectedOptioin);
                    deJsonArray.put(name);
                }

                createAccount(userNameET.getText().toString(),
                        ((LoginActivity) getActivity()).appUser.getUuid(), ((LoginActivity) getActivity()).appUser.getEmail(),
                        ((LoginActivity) getActivity()).appUser.getContactNumber(),aoiJsonArray,deJsonArray);


            }

        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onUserTypeFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserTypeFragmentInteractionListener) {
            mListener = (OnUserTypeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnUserTypeFragmentInteractionListener {
        // TODO: Update argument type and name
        void onUserTypeFragmentInteraction(Uri uri);
    }

    private void createAccount(String fullName, String uuid, String email, String contactNumber, JSONArray aoiJO,JSONArray deJO) {


        AppUser appUser = new AppUser();

        appUser.setUuid(uuid);
        appUser.setContactNumber(((LoginActivity) getActivity()).appUser.getContactNumber());
        appUser.setEmail(((LoginActivity) getActivity()).appUser.getEmail());
        appUser.setFullName(((LoginActivity) getActivity()).appUser.getFullName());
        appUser.setUserType(selectedRoleOption);
        appUser.setAreaofInterests(aoiJO);
        appUser.setDomainExpertises(deJO);
        UserAPI.createUser(getActivity(), appUser, new NBOSCallback() {
            @Override
            public void onResponse(Response response) {
                Log.d("test", "success" + response.code());
                if (response.isSuccessful()) {
                    if (selectedRoleOption.equalsIgnoreCase("investor")) {
                        Intent intent = new Intent(((LoginActivity) getActivity()), InvestorMainActivity.class);
                        startActivity(intent);
                    } else if (selectedRoleOption.equalsIgnoreCase("startup")) {
                        Intent intent = new Intent(((LoginActivity) getActivity()), StartupMainActivity.class);
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
