package com.wavelabs.nfund;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wavelabs.nfund.model.AboutUs;
import com.wavelabs.nfund.service.AppAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;

public class About50kFragment extends Fragment {


    private TextView about50KTextView;

    public About50kFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about50k, container, false);
        about50KTextView = (TextView) view.findViewById(R.id.aboutUsTextView);
        AppAPI.getAboutUs(getActivity(), new NBOSCallback<AboutUs>() {

            @Override
            public void onResponse(Response<AboutUs> response) {
                Log.d("test", response.body().getContent().getRendered());
                Document doc = Jsoup.parseBodyFragment(response.body().getContent().getRendered());
                Elements p = doc.getElementsByTag("p");
                String pConcatenated = "";
                for (Element x : p) {
                    pConcatenated += x.text() + "\n\n";
                }

                System.out.println(pConcatenated);
                about50KTextView.setText(pConcatenated);
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });

        return view;
    }

}
