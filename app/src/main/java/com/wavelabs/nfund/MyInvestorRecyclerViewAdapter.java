package com.wavelabs.nfund;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wavelabs.nfund.InvestorFragment.OnInvestorListFragmentInteractionListener;
import com.wavelabs.nfund.dummy.DummyContent.DummyItem;
import com.wavelabs.nfund.model.User;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnInvestorListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyInvestorRecyclerViewAdapter extends RecyclerView.Adapter<MyInvestorRecyclerViewAdapter.ViewHolder> {

    Context mContext;
    private final List<User> mValues;
    private final OnInvestorListFragmentInteractionListener mListener;

    public MyInvestorRecyclerViewAdapter(List<User> items, OnInvestorListFragmentInteractionListener listener,Context context) {
        mContext = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_investor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getProfile().getFullName());
        Context context = holder.mInvestorProfileIView.getContext();
        /*
        if(mValues.get(position).getProfile().getIdnImageUrl()!=null)
            Picasso.with(context).load(mValues.get(position).getProfile().getIdnImageUrl()).into(holder.mInvestorProfileIView);
        else
            Picasso.with(context).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100").into(holder.mInvestorProfileIView);
        */
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onInvestorListFragmentInteraction(holder.mItem);
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
        public final ImageView mInvestorProfileIView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.investorName);
            mInvestorProfileIView = (ImageView) view.findViewById(R.id.investorProfileIView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.getProfile().getFullName() + "'";
        }
    }
}
