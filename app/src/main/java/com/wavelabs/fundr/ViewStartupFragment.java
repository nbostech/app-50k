package com.wavelabs.fundr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wavelabs.fundr.helper.OnTeamAssocListFragmentInteractionListener;
import com.wavelabs.fundr.model.Associate;
import com.wavelabs.fundr.model.Company;
import com.wavelabs.fundr.service.CompanyAPI;

import java.util.ArrayList;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewStartupFragment.OnViewStartupFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewStartupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewStartupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "selectedCompany";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Company company;
    private String mParam2;
    private int mColumnCount = 3;
    Snackbar snackbar;

    ImageView companyLogoIV;
    TextView companyNameTV;
    TextView descriptionTV;
    TextView categoryTV;
    TextView websiteTV;
    TextView empStrengthTV;
    TextView foundedDateTV;
    TextView locationTV;
    TextView fundingStageTV;
    TextView previousCapitalTV;
    TextView monthNetBurnTV;
    TextView preMoneyValuationTV;
    TextView businessSummaryTV;
    TextView uspProductUniquenessTV;
    public List<Associate> associates = null;
    RecyclerView recyclerView = null;

    private OnViewStartupFragmentInteractionListener mListener;
    private OnTeamAssocListFragmentInteractionListener mListViewListener;

    public ViewStartupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewStartupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewStartupFragment newInstance(Company param1, String param2) {
        ViewStartupFragment fragment = new ViewStartupFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            company = (Company) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_startup_1, container, false);
        companyLogoIV = (ImageView) view.findViewById(R.id.companyLogoIV);
        companyNameTV = (TextView) view.findViewById(R.id.companyNameTV);
        descriptionTV = (TextView) view.findViewById(R.id.descriptionTV);
        categoryTV = (TextView) view.findViewById(R.id.categoryTV);
        websiteTV = (TextView) view.findViewById(R.id.companyWebsiteTV);
        empStrengthTV = (TextView) view.findViewById(R.id.empStrengthTV);
        foundedDateTV = (TextView) view.findViewById(R.id.foundedDateTV);
        locationTV = (TextView) view.findViewById(R.id.locationTV);
        fundingStageTV = (TextView) view.findViewById(R.id.fundingStageTV);
        previousCapitalTV = (TextView) view.findViewById(R.id.perviouCapitalTV);
        monthNetBurnTV = (TextView) view.findViewById(R.id.monthlyNetBurnTV);
        preMoneyValuationTV = (TextView) view.findViewById(R.id.preMoneyValuation);
        businessSummaryTV = (TextView) view.findViewById(R.id.businessSummaryTV);
        uspProductUniquenessTV = (TextView) view.findViewById(R.id.uspProductUniqueness);


        if (company.getLogoImage() != null)
            Picasso.with(companyLogoIV.getContext()).load(company.getLogoImage().getMediaFileURLStr("medium")).into(companyLogoIV);
        else
            Picasso.with(companyLogoIV.getContext()).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100").into(companyLogoIV);
        companyNameTV.setText(company.getProfile().getName());
        descriptionTV.setText(company.getProfile().getDescription());
        categoryTV.setText(company.getProfile().getCategory());
        websiteTV.setText(company.getProfile().getWebsite());
        empStrengthTV.setText(company.getProfile().getEmpStrength());
        foundedDateTV.setText(company.getProfile().getFoundedDate());
        locationTV.setText(company.getProfile().getLocation());
        fundingStageTV.setText(company.getProfile().getFundingStage());
        previousCapitalTV.setText(company.getProfile().getPreviousCapital());
        monthNetBurnTV.setText(company.getProfile().getMonthlyNetBurn());
        preMoneyValuationTV.setText(company.getProfile().getPerMoneyValuation());
        businessSummaryTV.setText(company.getProfile().getBusinessSummary());
        uspProductUniquenessTV.setText(company.getProfile().getProductUSP());


        RecyclerView teamAssociateListViewRCV = (RecyclerView) view.findViewById(R.id.teamAssoicateListView);

        // Set the adapter
        if (teamAssociateListViewRCV instanceof RecyclerView) {
            Context context = teamAssociateListViewRCV.getContext();
            recyclerView = teamAssociateListViewRCV;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            if (associates == null)
                CompanyAPI.getAssociates(getActivity(), company.getId(), new NBOSCallback<ArrayList<Associate>>() {
                    @Override
                    public void onResponse(Response<ArrayList<Associate>> response) {
                        associates = response.body();
                        Log.d("test", associates.size() + "");
                        if (associates != null) {
                            mListViewListener = (OnTeamAssocListFragmentInteractionListener) getActivity();
                            recyclerView.setAdapter(new TeamAssocRecyclerViewAdapter(associates, mListViewListener, getActivity()));
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }

                });

            else {
                recyclerView.setAdapter(new TeamAssocRecyclerViewAdapter(associates, mListViewListener, getActivity()));
            }

        }



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.editStartupBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */

                Log.d("test", company.getProfile().getName());
                Intent intent = new Intent(getActivity(), CompanyProfileActivity.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);


            }
        });
        if(!mParam2.equalsIgnoreCase("startup")){
            fab.setVisibility(View.INVISIBLE);
        }


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onViewStartupFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mParam2.equalsIgnoreCase("dealbank")){
            snackbar = Snackbar
                    .make(getView(), "", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Add to favourite", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);


// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);

            snackbar.show();
        }
        if(mParam2.equalsIgnoreCase("fundingprogress")){
            snackbar = Snackbar
                    .make(getView(), "", Snackbar.LENGTH_INDEFINITE)
                    .setAction("I'm Interested", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnViewStartupFragmentInteractionListener) {
            mListener = (OnViewStartupFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        snackbar.dismiss();
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
    public interface OnViewStartupFragmentInteractionListener {
        // TODO: Update argument type and name
        void onViewStartupFragmentInteraction(Uri uri);
    }


}
