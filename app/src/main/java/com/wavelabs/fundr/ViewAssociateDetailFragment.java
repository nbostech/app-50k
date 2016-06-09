package com.wavelabs.fundr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wavelabs.fundr.model.Associate;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * Use the {@link ViewAssociateDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAssociateDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "associate";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView associateImageView;
    TextView associateNameVT;
    TextView associateCompanyNameVT;
    TextView associateLinkedInLinkVT;
    TextView associateProfileSummaryVT;
    TextView associateLocationVT;
    TextView associateWebsiteVT;
    TextView associateExpAndExpVT;

    Associate associate;


    public ViewAssociateDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewAssociateDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewAssociateDetailFragment newInstance(String param1, String param2) {
        ViewAssociateDetailFragment fragment = new ViewAssociateDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    // TODO: Rename and change types and number of parameters
    public static ViewAssociateDetailFragment newInstance(Associate associate) {
        ViewAssociateDetailFragment fragment = new ViewAssociateDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, associate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            associate = (Associate) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_associate_detail, container, false);
        //associate = ((AssociateActivity)getActivity()).associate;

        associateImageView = (ImageView) view.findViewById(R.id.assoicateImageView);

        if (associate.getProfileImage() != null)
            Picasso.with(associateImageView.getContext()).load(associate.getProfileImage().getMediaFileURLStr("medium")).into(associateImageView);
        else
            Picasso.with(associateImageView.getContext()).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100").into(associateImageView);


        associateNameVT = (TextView) view.findViewById(R.id.associateNameTV);
        associateNameVT.setText(associate.getName());
        associateCompanyNameVT = (TextView) view.findViewById(R.id.associateCompanyNameTV);
        associateCompanyNameVT.setText(associate.getAssociateType());
        associateLinkedInLinkVT = (TextView) view.findViewById(R.id.associateLinkedInLinkTV);
        associateLinkedInLinkVT.setText(associate.getLinkedinProfile());
        associateProfileSummaryVT = (TextView) view.findViewById(R.id.associateProfileSummaryTV);
        associateProfileSummaryVT.setText("");
        associateLocationVT = (TextView) view.findViewById(R.id.associateLocationTV);
        associateLocationVT.setText(associate.getLocation());
        associateWebsiteVT = (TextView) view.findViewById(R.id.associateWebsiteTV);
        associateWebsiteVT.setText(associate.getWebsite());
        associateExpAndExpVT = (TextView) view.findViewById(R.id.associateExpAndExpTV);
        associateExpAndExpVT.setText(associate.getExperience());


        return view;
    }


}
