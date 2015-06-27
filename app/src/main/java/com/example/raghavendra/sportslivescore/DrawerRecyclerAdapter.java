package com.example.raghavendra.sportslivescore;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Map;

/**
 * Created by Raghavendra on 4/22/2015.
 */
public class DrawerRecyclerAdapter extends RecyclerView.Adapter<DrawerRecyclerAdapter.ViewHolder> {
    private final Context mcontext;
    private final List<Map<String,?>> mDataSet;
    OnItemClickListener mItemClickListener;
    private int mCurrentPosition;
    public DrawerRecyclerAdapter(Context mycontext, List<Map<String, ?>> myDataSet) {
        mcontext = mycontext;
        mDataSet = myDataSet;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView vIcon;
        public TextView vTitle;
        public TextView vDescription;
        public CheckBox vCheckbox;
        public ImageView vLine;
        public View vView;
        public int vViewType;


        public ViewHolder(View v, int viewtype){
            super(v);
            vView = v;
            vViewType = viewtype;
            vIcon = (ImageView)v.findViewById(R.id.icon);
            vTitle = (TextView)v.findViewById(R.id.title);
            vLine = (ImageView)v.findViewById(R.id.line);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                    mCurrentPosition = getPosition();
                    notifyDataSetChanged();
                }
            });

        }

        public void bindMovieData(Map<String, ?> item, int position){
            if (position == mCurrentPosition)
                vView.setBackgroundColor(Color.LTGRAY);
            else
                vView.setBackgroundColor(0x00000000);
            switch(vViewType){
                case DrawerData.TYPE1:
                    if (vIcon != null)
                        vIcon.setImageResource( ( (Integer)item.get("icon")).intValue());
                    if (vTitle !=null)
                        vTitle.setText((String)item.get("title"));
                case DrawerData.TYPE2:
                    if (vLine !=null)
                        vLine.setImageResource(( (Integer)item.get("icon")).intValue());
                case DrawerData.TYPE3:
                    if (vTitle !=null)
                        vTitle.setText((String)item.get("title"));
            }

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v;
        ViewHolder vh;
        switch(viewType){
            case DrawerData.TYPE1:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_list_item_type1, parent, false);
                break;
            case DrawerData.TYPE2:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_list_item_type2, parent, false);
                break;
            case DrawerData.TYPE3:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_list_item_type3, parent, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_list_item_type3, parent, false);
                break;
        }

        vh = new ViewHolder(v, viewType);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, ?>item = mDataSet.get(position);
        holder.bindMovieData(item,position);

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemViewType(int position){
        Map<String, ?> item = mDataSet.get(position);
        return (Integer) item.get("type");

    }
}