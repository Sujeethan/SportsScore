package com.example.raghavendra.sportslivescore.Soccer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raghavendra.sportslivescore.R;

import java.util.HashMap;

/**
 * Created by Raghavendra on 4/22/2015.
 */

public class SoccerDetailFragment extends Fragment {
    private static final String ARG_MATCH= "match";
    private static HashMap<String,?> match;
    private ShareActionProvider mShareActionProvider;
    private Intent intentShare;
    public ImageView vIcon;
    public ImageView vIcon2;
    public TextView vDescription;
    public ImageView vMenu;
    public TextView vStars;
    public TextView vStage;
    public TextView vScore;
    public TextView vTeam1;
    public TextView vTeam2;
    public TextView vTime;
    public TextView vSummary;

    public static SoccerDetailFragment newInstance(HashMap<String,?> match) {
        SoccerDetailFragment fragment = new SoccerDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MATCH, match);
        fragment.setArguments(args);
        return fragment;
    }

    public SoccerDetailFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(getArguments() != null)
            match = (HashMap<String,?>)getArguments().getSerializable(ARG_MATCH);
        setHasOptionsMenu(true);
    }


    Button button;
    ViewGroup viewGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_detailview01, container, false);

        button = (Button) rootView.findViewById(R.id.button);
        viewGroup = (ViewGroup) rootView.findViewById(R.id.viewgroup);

        button.setOnClickListener(new MyOnClickListener());


        vIcon = (ImageView)rootView.findViewById(R.id.image);
        vIcon2 = (ImageView)rootView.findViewById(R.id.image2);
        vDescription = (TextView)rootView.findViewById(R.id.description);
        vMenu = (ImageView)rootView.findViewById(R.id.menu_list);
        vStars = (TextView)rootView.findViewById(R.id.stars);
        vStage = (TextView)rootView.findViewById(R.id.year);
        vScore= (TextView)rootView.findViewById(R.id.director);
        vTeam1 = (TextView)rootView.findViewById(R.id.team1);
        vTeam2 = (TextView)rootView.findViewById(R.id.team2);
        vTime = (TextView)rootView.findViewById(R.id.url);
        vSummary = (TextView)rootView.findViewById(R.id.summary);
        Button button = (Button) rootView.findViewById(R.id.button);

        vTeam1.setText((String) match.get("team1"));
        vTeam2.setText((String) match.get("team2"));
        vTime.setText((String) match.get("time"));
        vSummary.setText((String) match.get("summary"));
        vDescription.setText((String) match.get("description"));
        vIcon.setImageResource((Integer) match.get("image"));
        vIcon2.setImageResource((Integer) match.get("image2"));
        vStars.setText((String)match.get("stars"));
        vStage.setText((String)match.get("stage"));
        vScore.setText((String)match.get("score"));

        if(!(match.get("length").equals("Live")))
        {
            button.setVisibility(View.GONE);
        }

        return rootView;

    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    viewGroup.animate().setDuration(500)
                            .alpha(0f);

                    button.animate().setDuration(1000)
                            .x(button.getLeft())
                            .y(button.getTop())
                            .rotationYBy(720)
                            .scaleX(1.0F).scaleY(1.0F);
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Data Refreshed", Toast.LENGTH_SHORT).show();

                    new CountDownTimer(2000,1000)
                    {

                        @Override
                        public void onTick(long l) {
                            //Refresh data
                        }

                        @Override
                        public void onFinish() {
                            viewGroup.animate().setDuration(500)
                                    .alpha(1f);

                        }
                    }.start();
                   /* viewGroup.animate().setDuration(200).setStartDelay(5000)
                            .alpha(1f);*/
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                System.exit(0);
                break;
            default: break;
        }    return true;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflator){

        inflator.inflate(R.menu.fragment_detail_menu, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        String data = "http://www.espncricinfo.com/ci/engine/match/index.html?view=calendar";
        intentShare.putExtra(Intent.EXTRA_TEXT, data);
        mShareActionProvider.setShareIntent(intentShare);


        super.onCreateOptionsMenu(menu,inflator);
    }

}
