package com.example.raghavendra.sportslivescore;

import java.util.HashMap;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raghavendra.sportslivescore.Cricket.CricketDetailFragment;
import com.example.raghavendra.sportslivescore.Cricket.CricketRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Cricket.CricketScheduleRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Soccer.SoccerDetailFragment;
import com.example.raghavendra.sportslivescore.Soccer.SoccerRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Soccer.SoccerScheduleRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Tennis.TennisRecyclerviewFragment;
import com.example.raghavendra.sportslivescore.Tennis.TennisScheduleRecyclerviewFragment;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends ActionBarActivity implements CoverPageFragment.OnButtonSelectedListener, SettingsFragment.OnFragmentInteractionListener
        ,CricketRecyclerviewFragment.OnRecyclerViewItemSelectedListener,
        SoccerRecyclerviewFragment.OnRecyclerViewItemSelectedListener,TennisRecyclerviewFragment.OnRecyclerViewItemSelectedListener,
        CricketScheduleRecyclerviewFragment.OnRecyclerViewItemSelectedListener,SoccerScheduleRecyclerviewFragment.OnRecyclerViewItemSelectedListener,
        TennisScheduleRecyclerviewFragment.OnRecyclerViewItemSelectedListener{
    private RelativeLayout mDrawer;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private  DrawerRecyclerAdapter mDrawerRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawer = (RelativeLayout) findViewById(R.id.drawer);
        mDrawerList = (RecyclerView) findViewById(R.id.drawer_list);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawerRecyclerViewAdapter = new DrawerRecyclerAdapter(this,  (new DrawerData()).getDrawerList());
        mDrawerRecyclerViewAdapter.SetOnItemClickListener(new DrawerRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                selectItem(position);
            }

        });
        mDrawerList.setAdapter(mDrawerRecyclerViewAdapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                mToolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        switch(position) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new CoverPageFragment()).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, CricketScheduleRecyclerviewFragment.newInstance(0)).addToBackStack(null).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, SoccerScheduleRecyclerviewFragment.newInstance(0)).addToBackStack(null).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, TennisScheduleRecyclerviewFragment.newInstance(0)).addToBackStack(null).commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(0)).addToBackStack(null).commit();
                break;
            case 6:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).addToBackStack(null).commit();
                /*Intent intent2 = new Intent(this, SettingsActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //.setFlags
                startActivity(intent2);*/
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new CoverPageFragment()).addToBackStack(null).commit();
                break;
        }
        mDrawerLayout.closeDrawer(mDrawer);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onButtonItemSelected(int id){
        Intent intent;

    }
    @Override
    public void onItemSelected(int position,HashMap<String,?> match){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, CricketDetailFragment.newInstance(match))
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onItemSelected3(int position, HashMap<String, ?> match) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ScheduleDetailFragment.newInstance(match))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemSelected4(int position, HashMap<String, ?> match) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ScheduleDetailFragment.newInstance(match))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemSelected1(int position, HashMap<String, ?> match) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SoccerDetailFragment.newInstance(match))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Intent intent;
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";


        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;
            int option= (Integer)getArguments().get(ARG_SECTION_NUMBER);
            switch(option) {
                case 0:
                    rootView = inflater.inflate(R.layout.about_me, container, false);
                    break;
                /*case 1:
                    rootView = inflater.inflate(R.layout.luffy_image, container, false);
                    final SeekBar seek = (SeekBar) rootView.findViewById(R.id.seekBar);
                    final TextView text = (TextView) rootView.findViewById(R.id.textView2);
                    final Switch mySwitch = (Switch) rootView.findViewById(R.id.switch1);

                    seek.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            text.setText(Integer.toString(progress));
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {

                            if (isChecked) {

                                Toast.makeText(getActivity().getApplicationContext(), "Push notification is ON",
                                        Toast.LENGTH_SHORT).show();
                                notify();


                            } else {

                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Push notification is OFF", Toast.LENGTH_SHORT).show();
                            }

                        }


                    });

                    break;*/

                case R.id.action_settings:
                    Toast.makeText(getActivity().getApplicationContext(), "You Clicked it!", Toast.LENGTH_LONG).show();
                    rootView = inflater.inflate(R.layout.fragment_cover, container, false);
                    //finish();
                    System.exit(0);

                    break;

                default:
                    rootView = inflater.inflate(R.layout.about_me, container, false);
                    break;


            }
            return rootView;
        }
    }
}
