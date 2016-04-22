package com.app50knetwork;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app50knetwork.model.AppCallback;
import com.app50knetwork.model.Profile;
import com.app50knetwork.model.User;
import com.app50knetwork.service.UserAPI;
import com.app50knetwork.util.UserDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnInvestorListFragmentInteractionListener}
 * interface.
 */
public class InvestorFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    private OnInvestorListFragmentInteractionListener mListener;
    public List<User> users = null;
    RecyclerView recyclerView = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InvestorFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InvestorFragment newInstance(int columnCount) {
        InvestorFragment fragment = new InvestorFragment();
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
        View view = inflater.inflate(R.layout.fragment_investor_list, container, false);

        ((MainActivity)getActivity()).resetActionBar(false,
                DrawerLayout.LOCK_MODE_UNLOCKED);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Investors");
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            if(users == null)
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
                            mListener = (OnInvestorListFragmentInteractionListener) getActivity();
                            recyclerView.setAdapter(new MyInvestorRecyclerViewAdapter(users, mListener,getActivity()));
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
                recyclerView.setAdapter(new MyInvestorRecyclerViewAdapter(users, mListener,getActivity()));
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInvestorListFragmentInteractionListener) {
            mListener = (OnInvestorListFragmentInteractionListener) context;
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
    public interface OnInvestorListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onInvestorListFragmentInteraction(User item);
    }
}
