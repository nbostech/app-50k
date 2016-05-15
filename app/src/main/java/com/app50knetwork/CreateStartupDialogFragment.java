package com.app50knetwork;

import android.content.Context;
import android.content.DialogInterface;
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

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.Company;
import com.app50knetwork.model.CompanyProfile;
import com.app50knetwork.service.CompanyAPI;

import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateStartupDialogFragment.OnCreateStartupDialogFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateStartupDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateStartupDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Company company;

    EditText startupNameET;
    EditText startupEmailET;
    EditText startupContactET;
    EditText startupFounderNameET;
    Button createStartupBtn;

    private OnCreateStartupDialogFragmentInteractionListener mListener;

    public CreateStartupDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateStartupDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateStartupDialogFragment newInstance(String param1, String param2) {
        CreateStartupDialogFragment fragment = new CreateStartupDialogFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_startup_dialog, container, false);

        getDialog().setTitle("Startup");
        startupNameET = (EditText)view.findViewById(R.id.startUpName);
        startupEmailET = (EditText)view.findViewById(R.id.startupEmail);
        startupContactET = (EditText)view.findViewById(R.id.startupContactNo);
        startupFounderNameET = (EditText)view.findViewById(R.id.startupFounderName);
        createStartupBtn = (Button)view.findViewById(R.id.createStartup);

        createStartupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyProfile companyProfile = new CompanyProfile();
                companyProfile.setEmail(startupEmailET.getText().toString());
                companyProfile.setName(startupNameET.getText().toString());
                companyProfile.setContactNumber(startupContactET.getText().toString());
                //companyProfile.setCategory(industryS.getSelectedItem().toString());
                companyProfile.setFounderName(startupFounderNameET.getText().toString());

                CompanyAPI.createCompany(getActivity(), new AppCallback<Company>() {
                    @Override
                    public void onSuccess(Response<Company> response) {

                        company = response.body();
                        getDialog().dismiss();
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
                }, companyProfile);
            }
        });


        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.d("test","dialog dismiss");

        StartupLandingFragment f = (StartupLandingFragment) getActivity().getSupportFragmentManager().findFragmentByTag("startupLandingFragment");

        f.startupList.add(company);
        f.recyclerView.getAdapter().notifyDataSetChanged();
        dialog.dismiss();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCreateStartupDialogFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCreateStartupDialogFragmentInteractionListener) {
            mListener = (OnCreateStartupDialogFragmentInteractionListener) context;
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
    public interface OnCreateStartupDialogFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCreateStartupDialogFragmentInteraction(Uri uri);
    }
}
