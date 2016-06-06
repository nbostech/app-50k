package com.wavelabs.nfund;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wavelabs.nfund.helper.OnTeamAssocListFragmentInteractionListener;
import com.wavelabs.nfund.model.Associate;
import com.wavelabs.nfund.model.User;
import com.wavelabs.nfund.service.CompanyAPI;

import java.util.ArrayList;
import java.util.List;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;

/**
 * Created by ashkumar on 5/7/2016.
 */
public class TeamTabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    private OnTeamAssocListFragmentInteractionListener mListener;
    public List<User> users = null;
    public List<Associate> associates = null;
    RecyclerView recyclerView = null;

    public TeamTabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TeamTabFragment newInstance(int sectionNumber) {
        TeamTabFragment fragment = new TeamTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static TeamTabFragment newInstance(int columnCount, int sectionNumber) {
        TeamTabFragment fragment = new TeamTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_assco_list, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        RecyclerView teamAssociateListViewRCV = (RecyclerView) view.findViewById(R.id.teamAssoicateListView);

        // Set the adapter
        if (teamAssociateListViewRCV instanceof RecyclerView) {
            Context context = teamAssociateListViewRCV.getContext();
            recyclerView = (RecyclerView) teamAssociateListViewRCV;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            if (associates == null)
                CompanyAPI.getAssociates(getActivity(), ((CompanyProfileActivity) getActivity()).company.getId(), new NBOSCallback<ArrayList<Associate>>() {
                    @Override
                    public void onResponse(Response<ArrayList<Associate>> response) {
                        associates = response.body();
                        Log.d("test", associates.size() + "");
                        if (associates != null) {
                            mListener = (OnTeamAssocListFragmentInteractionListener) getActivity();
                            recyclerView.setAdapter(new TeamAssocRecyclerViewAdapter(associates, mListener, getActivity()));
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }


                });

            else {
                recyclerView.setAdapter(new TeamAssocRecyclerViewAdapter(associates, mListener, getActivity()));
            }
                /*
                UserAPI.getUsers(getActivity(), new AppCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(Response<ResponseBody> response) {

                        UserDeserializer<User> myDeserializer = new UserDeserializer<User>(Profile.class,
                                new UserDeserializer<Profile>(null,null));
                        Gson gson = new GsonBuilder().registerTypeAdapter(User.class, myDeserializer).create();
                        try {
                            String userJsonStr =response.body().string();
                            User[] userArr = gson.fromJson(userJsonStr,User[].class);
                            users = Arrays.asList(userArr);
                            if(users!=null) {
                                mListener = (OnTeamAssocListFragmentInteractionListener) getActivity();
                                recyclerView.setAdapter(new TeamAssocRecyclerViewAdapter(users, mListener,getActivity()));
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
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
                },"investor");
            else {
                recyclerView.setAdapter(new TeamAssocRecyclerViewAdapter(users, mListener,getActivity()));
            }
            */
        }


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addTeamAssociate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */

                FragmentManager fm = getActivity().getSupportFragmentManager();
                CreateAssociateDialogFragment dialogFragment = new CreateAssociateDialogFragment();
                dialogFragment.show(fm, "fragment_create_assoiciate_dialog");


            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTeamAssocListFragmentInteractionListener) {
            mListener = (OnTeamAssocListFragmentInteractionListener) context;
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


}
