package com.example.raghavendra.sportslivescore.Tennis;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.example.raghavendra.sportslivescore.R;

import org.json.JSONException;

import java.util.HashMap;


/**
 * Created by Raghavendra on 4/16/2015.
 */


public class TennisScheduleRecyclerviewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_TENNIS_SCHEDULE = "tennisschedule";
    private TennisScheduleDataJson scheduleData;
    private RecyclerView mRecyclerView;
    private TennisScheduleRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnRecyclerViewItemSelectedListener mListener;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        try {
            scheduleData = new TennisScheduleDataJson(getActivity());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setHasOptionsMenu(true);
    }


    public static TennisScheduleRecyclerviewFragment newInstance(int sectionNumber) {
        return newInstance(sectionNumber, null);
    }


    public static TennisScheduleRecyclerviewFragment newInstance(int sectionNumber, TennisScheduleDataJson scheduleData) {
        TennisScheduleRecyclerviewFragment fragment = new TennisScheduleRecyclerviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_TENNIS_SCHEDULE, scheduleData);
        fragment.setArguments(args);
        return fragment;
    }

    public TennisScheduleRecyclerviewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        int option = getArguments().getInt(ARG_SECTION_NUMBER);
        switch(option) {
            case 0:
                rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerViewAdapter = new TennisScheduleRecyclerViewAdapter(getActivity(), scheduleData.getMoviesList(),0);
                break;
            default:
                rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerViewAdapter = new TennisScheduleRecyclerViewAdapter(getActivity(), scheduleData.getMoviesList(),0);
                break;
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter.SetOnItemClickListener(new TennisScheduleRecyclerViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClick( View v, int position){
                HashMap<String,?> match= (HashMap<String,?>)scheduleData.getItem(position);
                mListener.onItemSelected3(position,match);

            }
            @Override
            public void onItemLongClick( View v, int position){
                getActivity().startActionMode(new ActionBarCallBack(position));

            }
            @Override
            public void onOverFlowMenuClick(View v, final int position){
                PopupMenu popup=new PopupMenu(getActivity(),v);
                MenuInflater inflater = popup.getMenuInflater();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item){
                        switch(item.getItemId()){
                            case R.id.item_delete:
                                scheduleData.matchlist.remove(position);
                                mRecyclerViewAdapter.notifyItemRemoved(position);
                                return true;

                            default:
                                return false;

                        }
                    }
                });
                inflater.inflate(R.menu.contextual_menu, popup.getMenu());
                popup.show();
            }

        });
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        return rootView;
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
       if(menu.findItem(R.id.action_search) == null) {
           inflator.inflate(R.menu.menu_recyceler_view, menu);
       }

       SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
       if (search != null){
           search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
               @Override
               public boolean onQueryTextSubmit(String query){
                   int position = scheduleData.findFirst(query);
                   if (position >= 0)
                       mRecyclerView.smoothScrollToPosition(position);
                   return true;
               }
               @Override
               public boolean onQueryTextChange(String query){
                   return true;
               }

           });
       }
       super.onCreateOptionsMenu(menu,inflator);
   }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRecyclerViewItemSelectedListener) activity;
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

    class ActionBarCallBack implements ActionMode.Callback {
        int position;
        public ActionBarCallBack(int position) {this.position = position;}

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            // TODO Auto-generated method stub
            int id = item.getItemId();
            switch (id) {
                 case R.id.item_delete:
                     scheduleData.matchlist.remove(position);
                     mRecyclerViewAdapter.notifyItemRemoved(position);
                     mode.finish();
                     break;

                default:
                    break;
            }
            return false;
        }
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            String name=(String)scheduleData.getItem(position).get("name");
            mode.setTitle(name);
            return false;
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnRecyclerViewItemSelectedListener {
        // TODO: Update argument type and name
        public void onItemSelected3(int position, HashMap<String, ?> match);

    }


}
