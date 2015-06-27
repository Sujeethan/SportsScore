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

public class CricketDataJson implements Serializable {
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
            s =  matchList.get(i).get("teams").toString();

            if (s.toLowerCase().contains(query.toLowerCase()))
                return i;
        }
        return -1;
    }

    public CricketDataJson(Context context) throws JSONException {
        String description = null;
        String length = null;
        String matchnumber = null;
        String score = null;
        String teams = null;
        String url = null;
        String name = null;
        String detail = null;
        String drawablename = null;
        int resID = 0;
        JSONArray matchesJsonArray = null;
        JSONObject matchesJsonObj = null;
        matchList = new ArrayList<Map<String,?>>();
        String moviesArray = loadMovieJSONFromAsset(context);
        matchesJsonArray = new JSONArray(moviesArray);
        for(int i = 0; i <matchesJsonArray.length();i++){
            matchesJsonObj = (JSONObject) matchesJsonArray.get(i);
            if(matchesJsonObj != null) {
                name = (String) matchesJsonObj.get("name");
                matchnumber = (String) matchesJsonObj.get("matchnumber");
                length = (String) matchesJsonObj.get("length");
                score = (String) matchesJsonObj.get("score");
                teams = (String) matchesJsonObj.get("teams");
                detail = (String) matchesJsonObj.get("detail");
                url = (String) matchesJsonObj.get("url");
                description = (String) matchesJsonObj.get("description");
                drawablename = (String) matchesJsonObj.get("image");
                resID = context.getResources().getIdentifier(drawablename, "drawable", context.getPackageName());
            }
            matchList.add(createMovie(name, resID, description, matchnumber, length, score, detail, teams, url));

        }
    }


    private HashMap createMovie(String name, int image, String description, String matchnumber,
                                String length, String score, String detail, String teams, String url) {
        HashMap match = new HashMap();
        match.put("image",image);
        match.put("name", name);
        match.put("description", description);
        match.put("matchnumber", matchnumber);
        match.put("length",length);
        match.put("score",score);
        match.put("teams",teams);
        match.put("url",url);
        match.put("detail",detail);
        match.put("selection",false);
        return match;
    }

    public String loadMovieJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("cricket.json");
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
