package com.wavelabs.fundr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wavelabs.fundr.FundingProgressFragment.OnFundProgFragIListener;
import com.wavelabs.fundr.dummy.DummyContent.DummyItem;
import com.wavelabs.fundr.model.Company;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnFundProgFragIListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FundingProgressRecyclerViewAdapter extends RecyclerView.Adapter<FundingProgressRecyclerViewAdapter.ViewHolder> {

    private final List<Company> mValues;
    private final OnFundProgFragIListener mListener;

    public FundingProgressRecyclerViewAdapter(List<Company> items, OnFundProgFragIListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fundingprogress, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).get);
        holder.mContentView.setText(mValues.get(position).getProfile().getDescription());
        Context context = holder.mCompImageView.getContext();
        if (mValues.get(position).getLogoImage().getMediaFileURLStr("medium") != null)
            Picasso.with(context).load(mValues.get(position).getLogoImage().getMediaFileURLStr("medium"))
                    .into(holder.mCompImageView);
        else
            Picasso.with(context).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100")
                    .into(holder.mCompImageView);
        holder.mIndustry.setText(mValues.get(position).getProfile().getCategory());
        holder.mLocation.setText(mValues.get(position).getProfile().getLocation());
        holder.mWebsite.setText(mValues.get(position).getProfile().getWebsite());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFundProgFragInteraction(holder.mItem);
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
        public final TextView mIndustry;
        public final TextView mLocation;
        public final ImageView mCompImageView;
        public final TextView mContentView;
        public final TextView mWebsite;
        public Company mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mIndustry =  (TextView) view.findViewById(R.id.industry);
            mLocation = (TextView)view.findViewById(R.id.location);
            mWebsite = (TextView)view.findViewById(R.id.website);
            mCompImageView = (ImageView)view.findViewById(R.id.companyLogoIV);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
