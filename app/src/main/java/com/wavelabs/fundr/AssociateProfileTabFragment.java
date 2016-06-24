package com.wavelabs.fundr;

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

import com.wavelabs.fundr.model.Associate;
import com.wavelabs.fundr.model.TeamType;
import com.wavelabs.fundr.service.CompanyAPI;
import com.wavelabs.fundr.service.MetadataAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class AssociateProfileTabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    private Spinner associateTeamS;
    private Button associateSubmitBtn;
    private EditText associateNameLabelEV, associateEmailET, associatePositionET, associateLocationET, associateContactNoET, associateWebsiteET;
    private ImageView associateProfileImageView;
    private Button associateProfileImageUploadBtn;
    private ProgressBar spinner;
    Associate associate;
    Long companyId;

    HashMap<Long, String> teamMap = new HashMap<>();
    ArrayList<String> teamList = new ArrayList<>();
    ArrayAdapter<String> adapter;


    public AssociateProfileTabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AssociateProfileTabFragment newInstance(int sectionNumber) {
        AssociateProfileTabFragment fragment = new AssociateProfileTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_associate_profile_tab, container, false);
        associate = ((EditAssociateActivity) getActivity()).associate;
        companyId = ((EditAssociateActivity) getActivity()).companyId;

        associateTeamS = (Spinner) view.findViewById(R.id.associateTypeS);
        associateNameLabelEV = (EditText) view.findViewById(R.id.associateNameLabelEV);
        associateEmailET = (EditText) view.findViewById(R.id.associateEmailET);
        associatePositionET = (EditText) view.findViewById(R.id.associatePositionET);
        associateLocationET = (EditText) view.findViewById(R.id.associateLocationET);
        associateContactNoET = (EditText) view.findViewById(R.id.associateContactNoET);
        associateWebsiteET = (EditText) view.findViewById(R.id.associateWebsiteET);
        associateSubmitBtn = (Button) view.findViewById(R.id.associateSubmitBtn);
        associateProfileImageUploadBtn = (Button) view.findViewById(R.id.associateProfileUploadBtn);

        associateProfileImageView = (ImageView) view.findViewById(R.id.associateProfileIV);

        teamList = new ArrayList<>(teamMap.values());
        teamList.add("select team");
        adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, teamList);
        associateTeamS.setAdapter(adapter);

        if (teamList.size() <= 1) {
            //Activate Progress bar spinner
            getActivity().findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            MetadataAPI.getAssociateTypes(getActivity(), new NBOSCallback<List<TeamType>>() {
                @Override
                public void onResponse(Response<List<TeamType>> response) {
                    teamMap = TeamType.getMapFromList(response.body());
                    teamList = new ArrayList<>(teamMap.values());
                    teamList.add(0, "select team");
                    adapter =
                            new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, teamList);
                    associateTeamS.setAdapter(adapter);
                    if ((((EditAssociateActivity) getActivity()).associate.getAssociateType()) != null)
                        associateTeamS.setSelection(adapter.getPosition(((EditAssociateActivity) getActivity()).associate.getAssociateType()));

                    //Deactivate Progress bar spinner
                    ((EditAssociateActivity) getActivity()).layout.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }

                @Override
                public void onFailure(Throwable t) {

                }

            });
        }


        associateSubmitBtn.setText("UPDATE");
        associateNameLabelEV.setText(((EditAssociateActivity) getActivity()).associate.getName());
        associateEmailET.setText(((EditAssociateActivity) getActivity()).associate.getEmail());
        associatePositionET.setText(((EditAssociateActivity) getActivity()).associate.getPosition());
        associateLocationET.setText(((EditAssociateActivity) getActivity()).associate.getLocation());
        associateContactNoET.setText(((EditAssociateActivity) getActivity()).associate.getContactNumber());
        associateWebsiteET.setText(((EditAssociateActivity) getActivity()).associate.getWebsite());
        if (associate.getProfileImage() != null)
            Picasso.with(associateProfileImageView.getContext()).load(associate.getProfileImage().getMediaFileURLStr("medium")).into(associateProfileImageView);
        else
            Picasso.with(associateProfileImageView.getContext()).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100").into(associateProfileImageView);

        associateProfileImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                getActivity().startActivityForResult(i, ((EditAssociateActivity) getActivity()).RESULT_LOAD_IMAGE);


            }
        });

        associateNameLabelEV.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setName(s.toString());
            }
        });
        associateEmailET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setEmail(s.toString());
            }
        });
        associatePositionET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setPosition(s.toString());
            }
        });
        associateLocationET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setLocation(s.toString());
            }
        });
        associateContactNoET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setContactNumber(s.toString());
            }
        });
        associateWebsiteET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                ((EditAssociateActivity) getActivity()).associate.setWebsite(s.toString());
            }
        });


        associateSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                associate.setAssociateType(associateTeamS.getSelectedItem().toString());


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

        return view;
    }


}
