package com.example.raghavendra.sportslivescore;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;


public class SettingsFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public SettingsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    NotificationManager NM;
    private SeekBar seekBar;
    private Switch myswitch;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_settings, container, false);
        seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
        final TextView text = (TextView) rootView.findViewById(R.id.textView2);

        myswitch = (Switch) rootView.findViewById(R.id.switch1);
        myswitch.setOnClickListener(this);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                text.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onClick(View view) {
        if(myswitch.isChecked())
        {
            Toast.makeText(getActivity(),"Push notification is ON",Toast.LENGTH_SHORT).show();

            String s1 = "Alert!";
            String s2 = "Notification Turned On!";
            String s3 = "This is demo!";
            NM =(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification(android.R.drawable.
                    stat_notify_more,s1,System.currentTimeMillis());
            PendingIntent pending=PendingIntent.getActivity(
                    getActivity().getApplicationContext(),0, new Intent(),0);
            notify.setLatestEventInfo(getActivity().getApplicationContext(), s2, s3, pending);
            NM.notify(0, notify);
        }
        else {
            Toast.makeText(getActivity(),"Push notification is ON",Toast.LENGTH_SHORT).show();

            String s1 = "Alert!";
            String s2 = "Notification Turned Off!";
            String s3 = "This is demo!";
            NM =(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification(android.R.drawable.
                    stat_notify_more,s1,System.currentTimeMillis());
            PendingIntent pending=PendingIntent.getActivity(
                    getActivity().getApplicationContext(),0, new Intent(),0);
            notify.setLatestEventInfo(getActivity().getApplicationContext(),s2,s3,pending);
            NM.notify(0, notify);
        }

    }


    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(Uri uri);
    }

    /*@SuppressWarnings("deprecation")
    public void notify(View view) {

        if(myswitch.isChecked())
        {
            Toast.makeText(context.getApplicationContext(), "Push notification is ON",
                    Toast.LENGTH_SHORT).show();
            String s1 = "Alert!";
            String s2 = "Notification Turned On!";
            String s3 = "This is demo!";
            NM =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification(android.R.drawable.
                    stat_notify_more,s1,System.currentTimeMillis());
            PendingIntent pending=PendingIntent.getActivity(
                    context.getApplicationContext(),0, new Intent(),0);
            notify.setLatestEventInfo(context.getApplicationContext(), s2, s3, pending);
            NM.notify(0, notify);
        }
        else {
            Toast.makeText(context.getApplicationContext(),
                    "Push notification is OFF", Toast.LENGTH_SHORT).show();
            String s1 = "Alert!";
            String s2 = "Notification Turned Off!";
            String s3 = "This is demo!";
            NM =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification(android.R.drawable.
                    stat_notify_more,s1,System.currentTimeMillis());
            PendingIntent pending=PendingIntent.getActivity(
                    context.getApplicationContext(),0, new Intent(),0);
            notify.setLatestEventInfo(context.getApplicationContext(),s2,s3,pending);
            NM.notify(0, notify);
        }

    }*/

}


/*
NotificationManager NM;
    private SeekBar seekBar;
    private Switch myswitch;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView text = (TextView) findViewById(R.id.textView2);
        myswitch = (Switch) findViewById(R.id.switch1);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                text.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    public void notify(View view) {

        if(myswitch.isChecked())
        {
            Toast.makeText(getApplicationContext(), "Push notification is ON",
                    Toast.LENGTH_SHORT).show();
            String s1 = "Alert!";
            String s2 = "Notification Turned On!";
            String s3 = "This is demo!";
            NM =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification(android.R.drawable.
                    stat_notify_more,s1,System.currentTimeMillis());
            PendingIntent pending=PendingIntent.getActivity(
                    getApplicationContext(),0, new Intent(),0);
            notify.setLatestEventInfo(getApplicationContext(), s2, s3, pending);
            NM.notify(0, notify);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Push notification is OFF", Toast.LENGTH_SHORT).show();
            String s1 = "Alert!";
            String s2 = "Notification Turned Off!";
            String s3 = "This is demo!";
            NM =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification(android.R.drawable.
                    stat_notify_more,s1,System.currentTimeMillis());
            PendingIntent pending=PendingIntent.getActivity(
                    getApplicationContext(),0, new Intent(),0);
            notify.setLatestEventInfo(getApplicationContext(),s2,s3,pending);
            NM.notify(0, notify);
        }

    }


}

 */
