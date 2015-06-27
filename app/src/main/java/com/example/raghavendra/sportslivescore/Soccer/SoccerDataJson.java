package com.example.raghavendra.sportslivescore.Soccer;

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

public class SoccerDataJson implements Serializable {

    List<Map<String,?>> matchlist;

    public List<Map<String, ?>> getMoviesList() {
        return matchlist;
    }

    public int getSize(){
        return matchlist.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < matchlist.size()){
            return (HashMap) matchlist.get(i);
        } else return null;
    }


    public int findFirst(String query){
        String s;

        for (int i=0;i < matchlist.size();i++ ){
            s =  matchlist.get(i).get("stars").toString();

            if (s.toLowerCase().contains(query.toLowerCase()))
                return i;
        }
        return -1;
    }

    public SoccerDataJson(Context context) throws JSONException {
        String description = null;
		String length = null;
		String stage = null;
		String score = null;
		String stars = null;
		String time = null;
        String name = null;
        String drawablename = null;
        String drawablename2 = null;
        String team1 = null;
        String team2 = null;
        String summary = null;
        int resID = 0;
        int resID2 = 0;
        JSONArray matchesJsonArray = null;
        JSONObject matchesJsonObj = null;
        matchlist = new ArrayList<Map<String,?>>();
        String moviesArray = loadMovieJSONFromAsset(context);
        matchesJsonArray = new JSONArray(moviesArray);
        for(int i = 0; i <matchesJsonArray.length();i++){
            matchesJsonObj = (JSONObject) matchesJsonArray.get(i);
            if(matchesJsonObj != null) {
                name = (String) matchesJsonObj.get("name");
                stage = (String) matchesJsonObj.get("stage");
                length = (String) matchesJsonObj.get("length");
                score = (String) matchesJsonObj.get("score");
                stars = (String) matchesJsonObj.get("stars");
                time = (String) matchesJsonObj.get("time");
                team1 = (String) matchesJsonObj.get("team1");
                team2 = (String) matchesJsonObj.get("team2");
                summary = (String) matchesJsonObj.get("summary");
                description = (String) matchesJsonObj.get("description");
                drawablename = (String) matchesJsonObj.get("image");
                drawablename2 = (String) matchesJsonObj.get("image2");
                resID = context.getResources().getIdentifier(drawablename, "drawable", context.getPackageName());
                resID2 = context.getResources().getIdentifier(drawablename2, "drawable", context.getPackageName());
            }
            matchlist.add(createMovie(name, resID, resID2, description, stage, length, score, team1, team2, stars, time, summary));

        }
    }


    private HashMap createMovie(String name, int image, int image2, String description, String stage,
                                String length, String score, String team1, String team2, String stars, String time, String summary) {
        HashMap match = new HashMap();
        match.put("image",image);
        match.put("image2", image2);
        match.put("name", name);
        match.put("description", description);
        match.put("stage", stage);
        match.put("length",length);
        match.put("score",score);
        match.put("team1", team1);
        match.put("team2", team2);
        match.put("stars",stars);
        match.put("time",time);
        match.put("summary", summary);
        match.put("selection",false);
        return match;
    }

    public String loadMovieJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("soccer.json");
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
