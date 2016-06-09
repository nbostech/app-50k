package com.wavelabs.fundr;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wavelabs.fundr.model.Profile;
import com.wavelabs.fundr.service.UserAPI;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText userNameET;
    EditText userContactNameET;
    EditText userLocationET;
    EditText websiteET;
    EditText userProfileSummaryET;
    EditText userLinkedInProfileET;
    EditText userFacebookProfileET;
    EditText userTwitterProfileET;
    EditText userOtherProfileET;
    EditText userSocialAccountET;
    Button userUdpateBtn;
    Profile userProfile;
    View view;


    public EditUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditUserFragment newInstance(String param1, String param2) {
        EditUserFragment fragment = new EditUserFragment();
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
        view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        userProfile = ((StartupMainActivity) getActivity()).user.getProfile();

        userNameET = (EditText) view.findViewById(R.id.userNameET);
        userNameET.setText(userProfile.getFullName());
        userContactNameET = (EditText) view.findViewById(R.id.userContactNameET);
        userContactNameET.setText(userProfile.getContactNumber());
        userLocationET = (EditText) view.findViewById(R.id.userLocationET);
        userLocationET.setText(userProfile.getLocation());
        websiteET = (EditText) view.findViewById(R.id.websiteET);
        websiteET.setText(userProfile.getWebsite());
        userProfileSummaryET = (EditText) view.findViewById(R.id.userProfileSummaryET);
        userProfileSummaryET.setText(userProfile.getProfileSummary());
        userLinkedInProfileET = (EditText) view.findViewById(R.id.userLinkedInProfileET);
        userLinkedInProfileET.setText(userProfile.getLinkedInProfile());
        userFacebookProfileET = (EditText) view.findViewById(R.id.userFacebookProfileET);
        userFacebookProfileET.setText(userProfile.getFacebookProfile());
        userTwitterProfileET = (EditText) view.findViewById(R.id.userTwitterProfileET);
        userTwitterProfileET.setText(userProfile.getTwitterProfile());
        userOtherProfileET = (EditText) view.findViewById(R.id.userOtherProfileET);
        userOtherProfileET.setText(userProfile.getOtherProfile());
        userSocialAccountET = (EditText) view.findViewById(R.id.userSocialAccountET);
        userSocialAccountET.setText(userProfile.getSocialAccounts());
        userUdpateBtn = (Button) view.findViewById(R.id.userUpdateBtn);

        userUdpateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfile.setFullName(userNameET.getText().toString());
                userProfile.setContactNumber(userContactNameET.getText().toString());
                userProfile.setLocation(userLocationET.getText().toString());
                userProfile.setWebsite(websiteET.getText().toString());
                userProfile.setProfileSummary(userProfileSummaryET.getText().toString());
                userProfile.setLinkedInProfile(userLinkedInProfileET.getText().toString());
                userProfile.setFacebookProfile(userFacebookProfileET.getText().toString());
                userProfile.setTwitterProfile(userTwitterProfileET.getText().toString());
                userProfile.setOtherProfile(userOtherProfileET.getText().toString());
                userProfile.setSocialAccounts(userSocialAccountET.getText().toString());
                UserAPI.updateUserProfile(getActivity(), userProfile, new NBOSCallback() {


                    @Override
                    public void onResponse(Response response) {
                        if (response.code() == 200) {
                            Snackbar snackbar = Snackbar
                                    .make(view, "User successfully upated", Snackbar.LENGTH_LONG);

                            snackbar.show();
                        }
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
