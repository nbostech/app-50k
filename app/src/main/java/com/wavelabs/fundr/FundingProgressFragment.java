package com.wavelabs.fundr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wavelabs.fundr.model.Company;
import com.wavelabs.fundr.service.CompanyAPI;

import java.util.ArrayList;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFundProgFragIListener}
 * interface.
 */
public class FundingProgressFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnFundProgFragIListener mListener;
    List<Company> fundingProgList = new ArrayList<Company>();
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FundingProgressFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FundingProgressFragment newInstance(int columnCount) {
        FundingProgressFragment fragment = new FundingProgressFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fundingprogress_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            if (fundingProgList.size() == 0)
                CompanyAPI.getCompanies(getActivity(), new NBOSCallback<ArrayList<Company>>() {

                    @Override
                    public void onResponse(Response<ArrayList<Company>> response) {
                        fundingProgList = response.body();

                        if (fundingProgList.size() > 0) {
                            mListener = (FundingProgressFragment.OnFundProgFragIListener) getActivity();
                            recyclerView.setAdapter(new FundingProgressRecyclerViewAdapter(fundingProgList, mListener));
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }


                },"funding_progress");
            else {
                recyclerView.setAdapter(new FundingProgressRecyclerViewAdapter(fundingProgList, mListener));
            }


        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFundProgFragIListener) {
            mListener = (OnFundProgFragIListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnFundProgFragIListener {
        // TODO: Update argument type and name
        void onFundProgFragInteraction(Company item);
    }
}
