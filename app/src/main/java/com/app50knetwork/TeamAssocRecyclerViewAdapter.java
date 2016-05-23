package com.app50knetwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.app50knetwork.TeamTabFragment.OnTeamAssocListFragmentInteractionListener;
import com.app50knetwork.helper.OnTeamAssocListFragmentInteractionListener;
import com.app50knetwork.dummy.DummyContent.DummyItem;
import com.app50knetwork.model.Associate;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnTeamAssocListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TeamAssocRecyclerViewAdapter extends RecyclerView.Adapter<TeamAssocRecyclerViewAdapter.ViewHolder> {

    Context mContext;
    private final List<Associate> mValues;
    private final OnTeamAssocListFragmentInteractionListener mListener;

    public TeamAssocRecyclerViewAdapter(List<Associate> items, OnTeamAssocListFragmentInteractionListener listener, Context context) {
        mContext = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_team_assco, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).getId()+"");
        Log.d("test11",mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getName());
        Context context = holder.mAssociateProfileIView.getContext();
        //Picasso.with(context).load(mValues.get(position).getProfile().getIdnImageUrl()).into(holder.mInvestorProfileIView);
        //Picasso.with(context).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=60&h=60").into(holder.mAssociateProfileIView);
        if(mValues.get(position).getProfileImage()!=null)
            Picasso.with(context).load(mValues.get(position).getProfileImage().getMediaFileURLStr("medium")).into(holder.mAssociateProfileIView);
        else
            Picasso.with(context).load("https://placeholdit.imgix.net/~text?txtsize=15&txt=Company&w=100&h=100").into(holder.mAssociateProfileIView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onTeamAssocListFragmentInteraction(holder.mItem);
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
        public final ImageView mAssociateProfileIView;
        public Associate mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.associateName);
            mAssociateProfileIView = (ImageView) view.findViewById(R.id.associateProfileIView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.getName() + "'";
        }
    }
}
