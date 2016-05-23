package com.app50knetwork;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.Company;
import com.app50knetwork.service.CompanyAPI;

import java.util.ArrayList;

import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StartupLandingFragment.OnStartupListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StartupLandingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartupLandingFragment extends Fragment {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public ArrayList<Company> startupList = new ArrayList<Company>();
    RecyclerView recyclerView = null;

    private OnStartupListFragmentInteractionListener mListener;

    public StartupLandingFragment() {
        // Required empty public constructor
    }



    public static StartupLandingFragment newInstance(int columnCount) {
        StartupLandingFragment fragment = new StartupLandingFragment();
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


        View view = inflater.inflate(R.layout.fragment_startup_landing, container, false);

        RecyclerView startupListViewRCV= (RecyclerView) view.findViewById(R.id.startupListView);


        // Set the adapter
        if (startupListViewRCV instanceof RecyclerView) {
            Context context = startupListViewRCV.getContext();
            recyclerView = (RecyclerView) startupListViewRCV;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            if(startupList.size()==0)
                CompanyAPI.getCompanies(getActivity(), new AppCallback<ArrayList<Company>>() {
                    @Override
                    public void onSuccess(Response<ArrayList<Company>> response) {

                        startupList = response.body();

                        if(startupList.size()>0) {
                            mListener = (OnStartupListFragmentInteractionListener) getActivity();
                            recyclerView.setAdapter(new StartupRecyclerViewAdapter(startupList, mListener));
                        }
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
            else{
                recyclerView.setAdapter(new StartupRecyclerViewAdapter(startupList, mListener));
            }


        }
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addStartup);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getActivity().getSupportFragmentManager();
                CreateStartupDialogFragment dialogFragment = new CreateStartupDialogFragment ();
                dialogFragment.setTargetFragment(StartupLandingFragment.this,0);
                dialogFragment.show(fm,"fragment_create_startup_dialog");
            }
        });
        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartupListFragmentInteractionListener) {
            mListener = (OnStartupListFragmentInteractionListener) context;

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
    public interface OnStartupListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onStartupListFragmentInteraction(Company item);
    }
}
