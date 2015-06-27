package com.example.raghavendra.sportslivescore.Cricket;

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

public class CricketDetailFragment extends Fragment {
    private static final String ARG_MATCH= "match";
    private static HashMap<String,?> match;
    private ShareActionProvider mShareActionProvider;
    private Intent intentShare;


    public static CricketDetailFragment newInstance(HashMap<String,?> match) {
        CricketDetailFragment fragment = new CricketDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MATCH, match);
        fragment.setArguments(args);
        return fragment;
    }

    public CricketDetailFragment() {
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


        View rootView = inflater.inflate(R.layout.fragment_detailview, container, false);
        button = (Button) rootView.findViewById(R.id.button);
        viewGroup = (ViewGroup) rootView.findViewById(R.id.viewgroup);

        button.setOnClickListener(new MyOnClickListener());


        TextView name = (TextView) rootView.findViewById(R.id.name);
        TextView year = (TextView) rootView.findViewById(R.id.year);
        TextView director = (TextView) rootView.findViewById(R.id.director);
        TextView cast = (TextView) rootView.findViewById(R.id.stars);
        ImageView image = (ImageView) rootView.findViewById(R.id.image);
        TextView description = (TextView) rootView.findViewById(R.id.description);
        TextView url = (TextView) rootView.findViewById(R.id.url);
        TextView detail = (TextView) rootView.findViewById(R.id.detail);
        Button button = (Button) rootView.findViewById(R.id.button);



        name.setText((String) match.get("name"));
        description.setText((String) match.get("description"));
        image.setImageResource((Integer) match.get("image"));
        year.setText((String) match.get("matchnumber"));
        director.setText((String) match.get("score"));
        cast.setText((String) match.get("teams"));
        url.setText((String) match.get("url"));
        detail.setText((String) match.get("detail"));

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

        //int id = menu..getItemId();
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        String data = match.get("score") + " " + match.get("url");
        intentShare.putExtra(Intent.EXTRA_TEXT, data);
        mShareActionProvider.setShareIntent(intentShare);


        super.onCreateOptionsMenu(menu,inflator);
    }

}