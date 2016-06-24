package com.wavelabs.fundr;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.wavelabs.fundr.model.Associate;
import com.wavelabs.fundr.model.TeamType;
import com.wavelabs.fundr.service.CompanyAPI;
import com.wavelabs.fundr.service.MetadataAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateAssociateDialogFragment.OnCreateAssociateDialogFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateAssociateDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAssociateDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText associateName;
    EditText associateEmail;
    EditText associateContactNo;
    Spinner associateTeamType;
    EditText associateProfileSummary;
    Button createAssociateBtn;
    Associate associate;

    HashMap<Long, String> teamTypeMap = new HashMap<>();
    ArrayList<String> teamTypeList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private OnCreateAssociateDialogFragmentInteractionListener mListener;

    public CreateAssociateDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateAssociateDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAssociateDialogFragment newInstance(String param1, String param2) {
        CreateAssociateDialogFragment fragment = new CreateAssociateDialogFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_associate_dialog, container, false);
        getDialog().setTitle("Add");
        associateName = (EditText) view.findViewById(R.id.associateName);
        associateEmail = (EditText) view.findViewById(R.id.associateEmail);
        associateContactNo = (EditText) view.findViewById(R.id.associateContactNo);
        associateTeamType = (Spinner) view.findViewById(R.id.associateTeamType);
        associateProfileSummary = (EditText) view.findViewById(R.id.associateProfileSummary);
        createAssociateBtn = (Button) view.findViewById(R.id.createAssociateBtn);

        teamTypeList = new ArrayList<>(teamTypeMap.values());
        teamTypeList.add("select industry");
        adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, teamTypeList);
        associateTeamType.setAdapter(adapter);

        if (teamTypeList.size() <= 1) {
            //Activate Progress bar spinner
            getActivity().findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            MetadataAPI.getAssociateTypes(getActivity(), new NBOSCallback<List<TeamType>>() {

                @Override
                public void onResponse(Response<List<TeamType>> response) {
                    List<TeamType> teamTypeListRes = response.body();
                    teamTypeList = new ArrayList<>();
                    for (TeamType t : teamTypeListRes) {
                        teamTypeList.add(t.getName());
                    }
                    teamTypeList.add(0, "select team");
                    adapter =
                            new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, teamTypeList);
                    associateTeamType.setAdapter(adapter);

                    //Deactivate Progress bar spinner
                    ((CompanyProfileActivity) getActivity()).layout.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }

                @Override
                public void onFailure(Throwable t) {

                }


            });
        }
        teamTypeList = new ArrayList<>();
        teamTypeList.add(0, "select team");
        adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, teamTypeList);
        associateTeamType.setAdapter(adapter);


        createAssociateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                associate = new Associate();
                associate.setName(associateName.getText().toString());
                associate.setEmail(associateEmail.getText().toString());
                associate.setContactNumber(associateContactNo.getText().toString());
                associate.setProfileSummary(associateProfileSummary.getText().toString());
                CompanyAPI.createAssoicate(getActivity(), ((CompanyProfileActivity) getActivity()).company.getId(), associate, new NBOSCallback<Associate>() {


                    @Override
                    public void onResponse(Response<Associate> response) {
                        associate = response.body();
                        getDialog().dismiss();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }

                });
            }
        });

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (associate != null) {
            TeamTabFragment f = (TeamTabFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.profileTabcontainer + 3);
            f.associates.add(associate);
            f.recyclerView.getAdapter().notifyDataSetChanged();
        }
        dialog.dismiss();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCreateAssociateDialogFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCreateAssociateDialogFragmentInteractionListener) {
            mListener = (OnCreateAssociateDialogFragmentInteractionListener) context;
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
    public interface OnCreateAssociateDialogFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCreateAssociateDialogFragmentInteraction(Uri uri);
    }
}
