package com.app50knetwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app50knetwork.StartupLandingFragment.OnStartupListFragmentInteractionListener;
import com.app50knetwork.dummy.DummyContent.DummyItem;
import com.app50knetwork.model.Company;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnStartupListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StartupRecyclerViewAdapter extends RecyclerView.Adapter<StartupRecyclerViewAdapter.ViewHolder> {


    private final ArrayList<Company> mValues;
    private final OnStartupListFragmentInteractionListener mListener;

    public StartupRecyclerViewAdapter(ArrayList<Company> items, StartupLandingFragment.OnStartupListFragmentInteractionListener listener) {

        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_startup, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mContentView.setText(mValues.get(position).getProfile().getName());
        Context context = holder.mStartupProfileIView.getContext();
        //Picasso.with(context).load(mValues.get(position).getLogoImage().getMediaFileDetailList().getMediaPath()).into(holder.mStartupProfileIView);
        if(mValues.get(position).getLogoImage()!=null)
            Picasso.with(holder.mStartupProfileIView.getContext()).load(mValues.get(position).getLogoImage().getMediaFileURLStr("medium")).into(holder.mStartupProfileIView);
        else
            Picasso.with(context).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100").into(holder.mStartupProfileIView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onStartupListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mStartupProfileIView;
        public Company mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.startupName);
            mStartupProfileIView = (ImageView) view.findViewById(R.id.startupProfileIView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.getProfile().getName() + "'";
        }
    }
}
