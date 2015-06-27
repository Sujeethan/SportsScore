package com.example.raghavendra.sportslivescore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.raghavendra.sportslivescore.R;

import java.util.List;

/**
 * Created by Raghavendra on 4/22/2015.
 */
public final class Array_Adapter extends ArrayAdapter<ListViewItem> {

    private final LayoutInflater inflater;

    public Array_Adapter(Context context, int textViewResourceId, List<ListViewItem> objects) {
        super(context, textViewResourceId, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text);
        textView.setText(getItem(position).getTitle());
        TextView disabledText = (TextView) view.findViewById(R.id.list_item_disabled_text);
        disabledText.setText(getItem(position).getDisabledText());

        if (isEnabled(position)) {
            disabledText.setVisibility(View.INVISIBLE);
            textView.setTextColor(Color.WHITE);
        } else {
            disabledText.setVisibility(View.VISIBLE);
            textView.setTextColor(Color.GRAY);
        }

        return view;
    }

    @Override
    public boolean areAllItemsEnabled() {
        // have to return true here otherwise disabled items won't show a divider in the list.
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnabled();
    }

    public boolean anyDisabled() {
        for (int i = 0; i < getCount(); i++) {
            if (!isEnabled(i)) {
                return true;
            }
        }
        return false;
    }

}