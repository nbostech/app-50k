package com.wavelabs.fundr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wavelabs.fundr.model.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InvestorDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InvestorDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestorDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private User user;
    private String mParam2;

    ImageView investorImageView;
    TextView investorFullName;
    TextView investorCompanyName;
    TextView investorLinkedInLink;
    TextView investorProfileSummary;
    Context context;

    private OnFragmentInteractionListener mListener;

    public InvestorDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user   Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvestorDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvestorDetailFragment newInstance(User user, String param2) {
        InvestorDetailFragment fragment = new InvestorDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("test", user.getProfile().getEmail());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investor_detail, container, false);
        // Inflate the layout for this fragment

        if (getActivity() instanceof MainActivity) {
            context = ((MainActivity) getActivity());
            ((MainActivity) getActivity()).resetActionBar(false,
                    DrawerLayout.LOCK_MODE_UNLOCKED);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(user.getProfile().getFullName());
        } else if (getActivity() instanceof StartupMainActivity) {
            context = ((StartupMainActivity) getActivity());
            ((StartupMainActivity) getActivity()).resetActionBar(false,
                    DrawerLayout.LOCK_MODE_UNLOCKED);
            ((StartupMainActivity) getActivity()).getSupportActionBar().setTitle(user.getProfile().getFullName());
        } else if (getActivity() instanceof InvestorMainActivity) {
            context = ((InvestorMainActivity) getActivity());
            ((InvestorMainActivity) getActivity()).resetActionBar(false,
                    DrawerLayout.LOCK_MODE_UNLOCKED);
            ((InvestorMainActivity) getActivity()).getSupportActionBar().setTitle(user.getProfile().getFullName());
        }

        investorImageView = (ImageView) view.findViewById(R.id.investorImageView);
        Context context = investorImageView.getContext();

        investorFullName = (TextView) view.findViewById(R.id.investorName);
        investorFullName.setText(user.getProfile().getFullName());
        investorCompanyName = (TextView) view.findViewById(R.id.investorCompanyName);

        investorLinkedInLink = (TextView) view.findViewById(R.id.investorLinkedInLink);
        investorLinkedInLink.setText("http://linkedIn.in");
        investorProfileSummary = (TextView) view.findViewById(R.id.investorProfileSummary);
        investorProfileSummary.setText(user.getProfile().getProfileSummary());




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
