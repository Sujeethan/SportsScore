package com.example.raghavendra.sportslivescore.Soccer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raghavendra.sportslivescore.R;

import java.util.List;
import java.util.Map;


/**
 * Created by Raghavendra on 4/16/2015.
 */


public class SoccerScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mcontext;
    private final List<Map<String,?>> mDataSet;
    private final int mType;
    OnItemClickListener mItemClickListener;
    public SoccerScheduleRecyclerViewAdapter(Context mycontext, List<Map<String, ?>> myDataSet, int type) {
       mcontext = mycontext;
      mDataSet = myDataSet;
      mType = type;
    }
    public class ListViewHolder extends RecyclerView.ViewHolder{
        public ImageView vIcon;
        public TextView vDescription;
        public ImageView vMenu;
        public TextView vSeries;

        public ListViewHolder(View v){
            super(v);
            vIcon = (ImageView)v.findViewById(R.id.icon);
            vDescription = (TextView)v.findViewById(R.id.description);
            vMenu = (ImageView)v.findViewById(R.id.menu_list);
            vSeries = (TextView)v.findViewById(R.id.series);

            v.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){
                    if( mItemClickListener != null){
                        mItemClickListener.onItemClick(v,getPosition());
                    }
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    if(mItemClickListener != null){
                        mItemClickListener.onItemLongClick(v, getPosition());
                    }
                    return true;
                }
            });
            vMenu.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){
                    if( mItemClickListener != null){
                        mItemClickListener.onOverFlowMenuClick(v,getPosition());
                    }
                }

            });

        }

        public void bindMovieData(Map<String, ?> match){
            vDescription.setText((String) match.get("description"));
            vIcon.setImageResource((Integer) match.get("image"));
            vSeries.setText((String) match.get("series"));
        }


    }
    public class GridViewHolder extends RecyclerView.ViewHolder {
        public ImageView vIcon;


        public GridViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.icon);


            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemLongClick(v, getPosition());
                    }
                    return true;
                }
            });

        }

        public void bindMovieData(Map<String, ?> match) {
            vIcon.setImageResource((Integer) match.get("image"));

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v;
        RecyclerView.ViewHolder vh;
        if(viewType == 0) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview1, parent, false);
            vh = new ListViewHolder(v);
        }
        else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview1, parent, false);
            vh = new GridViewHolder(v);
        }

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Map<String, ?> match = mDataSet.get(position);
        switch(getItemViewType(position)){
            case 0:
                ListViewHolder listviewholder = (ListViewHolder) holder;
                listviewholder.bindMovieData(match);
                break;
            case 1:
                GridViewHolder gridviewholder = (GridViewHolder) holder;
                gridviewholder.bindMovieData(match);
                break;
        }

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
        public void onOverFlowMenuClick(View view, final int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemViewType(int position){
        int viewType;
        viewType = mType;
        return viewType;
    }
}
