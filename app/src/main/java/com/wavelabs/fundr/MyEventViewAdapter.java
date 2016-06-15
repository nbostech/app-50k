package com.wavelabs.fundr;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wavelabs.fundr.dummy.DummyContent.DummyItem;
import com.wavelabs.fundr.model.Event;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link EventFragment.OnEventListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEventViewAdapter extends RecyclerView.Adapter<MyEventViewAdapter.ViewHolder> {

    private final List<Event> mValues;
    private final EventFragment.OnEventListFragmentInteractionListener mListener;

    public MyEventViewAdapter(List<Event> items, EventFragment.OnEventListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).name);
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mEventAddress.setText(mValues.get(position).getAddress());
        /*
        String dateStr = mValues.get(position).getStartDate();
        try {
            DateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            DateFormat toFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
            Date parsedDate = fromFormat.parse(dateStr);
            //String month = new DateFormatSymbols().getMonths()[parsedDate.getMonth()-1];
            String month = toFormat.format(parsedDate);
            Log.d("test",month);
            Log.d("test",parsedDate.getDate()+"");
            holder.mEventDate.setText(parsedDate.getDate()+"");
            holder.mEventMonth.setText(month);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
        String dateStr = mValues.get(position).getScheudleDate();
        String[] dateStrArr = dateStr.split(" ");


        holder.mEventDate.setText(dateStrArr[1]);
        holder.mEventMonth.setText(dateStrArr[0]);


        Context context = holder.cardView.getContext();
        //Picasso.with(context).load(mValues.get(position).getImageUrl()).into(new Target() {
        if(mValues.get(position).getImageUrl() != null){
            Picasso.with(context).load(mValues.get(position).getImageUrl()).into(holder.imageView);

        } else {
            Picasso.with(context).load("http://p-r-i.org/wp-content/uploads/2012/09/Meetings.jpg").into(holder.imageView);

        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("View", "clicked");
                if (null != mListener) {
                    Log.d("View", "clicked1");

                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onEventListFragmentInteraction(holder.mItem);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         final View mView;
         CardView cardView;
        ImageView imageView;
        //public final TextView mIdView;
         final TextView mContentView;
         final TextView mEventAddress;
         final TextView mEventDate;
         final TextView mEventMonth;
         Event mItem;

         public ViewHolder(View view) {
            super(view);
            mView = view;
            cardView = (CardView) view.findViewById(R.id.card_view);
            //mIdView = mContentView(TextView) view.findViewById(R.id.id);
            imageView = (ImageView) view.findViewById(R.id.eventBackground);
            mContentView = (TextView) view.findViewById(R.id.content);
            mEventAddress = (TextView) view.findViewById(R.id.eventAddress);
            mEventDate = (TextView) view.findViewById(R.id.eventDate);
            mEventMonth = (TextView) view.findViewById(R.id.eventMonth);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
