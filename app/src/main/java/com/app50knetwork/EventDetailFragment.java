package com.app50knetwork;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app50knetwork.model.Event;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Event event;
    private String mParam2;

    ImageView eventImageView;
    TextView eventDescription;
    TextView eventTime;
    TextView eventContactDetails;
    TextView eventAddress;
    TextView eventWebSite;
    Button eventKnowMoreBtn;


    public EventDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventDetailFragment newInstance(Event param1, String param2) {
        EventDetailFragment fragment = new EventDetailFragment();
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
            event = (Event) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("test",event.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        DateFormat toFormat = new SimpleDateFormat("K:MM a", Locale.ENGLISH);
        toFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        //String startTimeStr = toFormat.format(event.getStartTime());
        //String endTimeStr = toFormat.format(event.getEndTime());
        eventTime =(TextView)view.findViewById(R.id.eventTime);
        //eventTime.setText(startTimeStr +" "+endTimeStr);
        eventTime.setText(event.getScheduleTime().replace("-", " - "));
        eventAddress =(TextView)view.findViewById(R.id.eventAddress);
        eventAddress.setText(event.getAddress());
        eventContactDetails =(TextView)view.findViewById(R.id.contactDetails);
        eventContactDetails.setText(event.getContactPerson()+System.getProperty("line.separator")+event.getContactNumber());
        eventWebSite =(TextView)view.findViewById(R.id.eventWebSite);
        eventWebSite.setText(event.getWebsite());
        eventDescription =(TextView)view.findViewById(R.id.eventDescription);
        eventDescription.setText(event.getDescription());
        eventKnowMoreBtn = (Button)view.findViewById(R.id.knowMoreBtn);

        eventImageView = (ImageView)view.findViewById(R.id.eventImageView);
        Picasso.with(eventImageView.getContext()).load("http://p-r-i.org/wp-content/uploads/2012/09/Meetings.jpg").into(eventImageView);



        eventKnowMoreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(event.getWebsite()));
                startActivity(intent);
            }
        });

        return view;
    }

}
