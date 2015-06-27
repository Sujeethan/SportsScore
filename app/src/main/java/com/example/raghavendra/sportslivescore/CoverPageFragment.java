package com.example.raghavendra.sportslivescore;


/**
 * Created by Raghavendra on 4/22/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.raghavendra.sportslivescore.Cricket.CricketRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Soccer.SoccerRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Tennis.TennisRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Videos.YouTubeAPIActivity;


public class CoverPageFragment extends Fragment {


    private OnButtonSelectedListener mListener;


    public static CoverPageFragment newInstance(String param1, String param2) {
        CoverPageFragment fragment = new CoverPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;


    }

    public CoverPageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }



    }

    Button button;
    TextView textView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_cover, container, false);

        button = (Button) rootView.findViewById(R.id.button1);
        textView = (TextView) rootView.findViewById(R.id.textView2);

        Button  button4 = (Button) rootView.findViewById(R.id.button4);

        blink(textView);

        button4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent2 = new Intent(getActivity(), YouTubeAPIActivity.class);
               intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //.setFlags
               startActivity(intent2);
           }
       });

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int id = view.getId();
                switch (id){
                    case R.id.button1:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, CricketRecyclerviewFragment.newInstance(0)).addToBackStack(null).commit();
                        break;
                    case R.id.button2:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, SoccerRecyclerviewFragment.newInstance(0)).addToBackStack(null).commit();
                        break;
                    case R.id.button3:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, TennisRecyclerviewFragment.newInstance(0)).addToBackStack(null).commit();
                        break;

                    default:
                        break;
                }


            }
        };
        (rootView.findViewById(R.id.button1)).setOnClickListener(onClickListener);
        (rootView.findViewById(R.id.button2)).setOnClickListener(onClickListener);
        (rootView.findViewById(R.id.button3)).setOnClickListener(onClickListener);
        return rootView;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnButtonSelectedListener) activity;
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

    public interface OnButtonSelectedListener {
        public void onButtonItemSelected(int position);
    }

    private void blink(final TextView textView){
        final Handler handler = new Handler();


        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 1000;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //TextView txt = (TextView) rootViewfindViewById(R.id.usage);
                        if(textView.getVisibility() == View.VISIBLE){
                            textView.setVisibility(View.INVISIBLE);
                        }else{
                            textView.setVisibility(View.VISIBLE);
                        }
                        blink(textView);
                    }
                });
            }
        }).start();
    }
}
