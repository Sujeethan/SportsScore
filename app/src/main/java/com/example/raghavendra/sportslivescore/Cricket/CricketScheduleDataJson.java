package com.example.raghavendra.sportslivescore.Cricket;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raghavendra on 4/16/2015.
 */

public class CricketScheduleDataJson implements Serializable {
    List<Map<String,?>> matchList;

    public List<Map<String, ?>> getMoviesList() {
        return matchList;
    }

    public int getSize(){
        return matchList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < matchList.size()){
            return (HashMap) matchList.get(i);
        } else return null;
    }


    public int findFirst(String query){
        String s;

        for (int i=0;i < matchList.size();i++ ){
            s =  matchList.get(i).get("series").toString();

            if (s.toLowerCase().contains(query.toLowerCase()))
                return i;
        }
        return -1;
    }

    public CricketScheduleDataJson(Context context) throws JSONException {
        String description = null;
        String series = null;
        String match1 = null;
        String match2 = null;
        String match3 = null;
        String match4 = null;
        String match5 = null;
        String venue1 = null;
        String venue2 = null;
        String venue3 = null;
        String venue4 = null;
        String venue5 = null;
        String drawablename = null;
        int resID = 0;
        JSONArray matchJsonArray = null;
        JSONObject matchJsonObj = null;
        matchList = new ArrayList<Map<String,?>>();
        String moviesArray = loadMovieJSONFromAsset(context);
        matchJsonArray = new JSONArray(moviesArray);
        for(int i = 0; i <matchJsonArray.length();i++){
            matchJsonObj = (JSONObject) matchJsonArray.get(i);
            if(matchJsonObj != null) {
                series = (String) matchJsonObj.get("series");
                match1 = (String) matchJsonObj.get("match1");
                match2 = (String) matchJsonObj.get("match2");
                match3 = (String) matchJsonObj.get("match3");
                match4 = (String) matchJsonObj.get("match4");
                match5 = (String) matchJsonObj.get("match5");
                venue1 = (String) matchJsonObj.get("venue1");
                venue2 = (String) matchJsonObj.get("venue2");
                venue3 = (String) matchJsonObj.get("venue3");
                venue4 = (String) matchJsonObj.get("venue4");
                venue5 = (String) matchJsonObj.get("venue5");
                description = (String) matchJsonObj.get("description");
                drawablename = (String) matchJsonObj.get("image");
                resID = context.getResources().getIdentifier(drawablename, "drawable", context.getPackageName());
            }
            matchList.add(createMovie(series, resID, description, match1, match2, match3,
                    match4, match5, venue1, venue2, venue3, venue4, venue5));

        }
    }


    private HashMap createMovie(String series, int image, String description, String match1,
                                String match2, String match3, String match4, String match5,
                                String venue1, String venue2, String venue3, String venue4, String venue5) {
        HashMap match = new HashMap();
        match.put("image",image);
        match.put("series", series);
        match.put("description", description);
        match.put("match1", match1);
        match.put("match2",match2);
        match.put("match3",match3);
        match.put("match4",match4);
        match.put("match5",match5);
        match.put("venue1",venue1);
        match.put("venue2",venue2);
        match.put("venue3",venue3);
        match.put("venue4",venue4);
        match.put("venue5",venue5);
        return match;
    }

    public String loadMovieJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("cricketschedule.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
