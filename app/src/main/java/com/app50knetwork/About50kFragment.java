package com.app50knetwork;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app50knetwork.model.AboutUs;
import com.app50knetwork.model.AppCallback;
import com.app50knetwork.service.AppAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class About50kFragment extends Fragment {


    TextView about50KTextView;

    public About50kFragment() {
        // Required empty public constructor
    }


    public static About50kFragment newInstance() {
        About50kFragment fragment = new About50kFragment();

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about50k, container, false);
        about50KTextView = (TextView) view.findViewById(R.id.aboutUsTextView);
        AppAPI.getAboutUs(getActivity(), new AppCallback<AboutUs>() {
            @Override
            public void onSuccess(Response<AboutUs> response) {

                Log.d("test", response.body().getContent().getRendered());
                Document doc = Jsoup.parseBodyFragment(response.body().getContent().getRendered());
                Elements p= doc.getElementsByTag("p");
                String pConcatenated="";
                for (Element x: p) {
                    pConcatenated+= x.text()+"\n\n";
                }

                System.out.println(pConcatenated);
                about50KTextView.setText(pConcatenated);
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

        return view;
    }

}
