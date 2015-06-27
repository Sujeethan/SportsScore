package com.example.raghavendra.sportslivescore.Tennis;

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

public class TennisDataJson implements Serializable {
    List<Map<String, ?>> matchlist;

    public List<Map<String, ?>> getMoviesList() {
        return matchlist;
    }

    public int getSize() {
        return matchlist.size();
    }

    public HashMap getItem(int i) {
        if (i >= 0 && i < matchlist.size()) {
            return (HashMap) matchlist.get(i);
        } else return null;
    }


    public int findFirst(String query) {
        String s;

        for (int i = 0; i < matchlist.size(); i++) {
            s = matchlist.get(i).get("stars").toString() + matchlist.get(i).get("stars2");

            if (s.toLowerCase().contains(query.toLowerCase()))
                return i;
        }
        return -1;
    }

    public TennisDataJson(Context context) throws JSONException {
        String description = null;
        String length = null;
        String stage = null;
        String score = null;
        String score2 = null;
        String score3 = null;
        String score4 = null;
        String score5 = null;
        String score6 = null;
        String score7 = null;
        String score8 = null;
        String score9 = null;
        String score10 = null;
        String stars = null;
        String stars2 = null;
        String name = null;
        String drawablename = null;
        String drawablename2 = null;
        int resID = 0;
        int resID2 = 0;
        JSONArray matchesJsonArray = null;
        JSONObject matchesJsonObj = null;
        matchlist = new ArrayList<Map<String, ?>>();
        String moviesArray = loadMovieJSONFromAsset(context);
        matchesJsonArray = new JSONArray(moviesArray);
        for (int i = 0; i < matchesJsonArray.length(); i++) {
            matchesJsonObj = (JSONObject) matchesJsonArray.get(i);
            if (matchesJsonObj != null) {
                name = (String) matchesJsonObj.get("name");
                stage = (String) matchesJsonObj.get("stage");
                length = (String) matchesJsonObj.get("length");
                score = (String) matchesJsonObj.get("score");
                score2 = (String) matchesJsonObj.get("score2");
                score3 = (String) matchesJsonObj.get("score3");
                score4 = (String) matchesJsonObj.get("score4");
                score5 = (String) matchesJsonObj.get("score5");
                score6 = (String) matchesJsonObj.get("score6");
                score7 = (String) matchesJsonObj.get("score7");
                score8 = (String) matchesJsonObj.get("score8");
                score9 = (String) matchesJsonObj.get("score9");
                score10 = (String) matchesJsonObj.get("score10");
                stars = (String) matchesJsonObj.get("stars");
                stars2 = (String) matchesJsonObj.get("stars2");
                description = (String) matchesJsonObj.get("description");
                drawablename = (String) matchesJsonObj.get("image");
                drawablename2 = (String) matchesJsonObj.get("image2");
                resID = context.getResources().getIdentifier(drawablename, "drawable", context.getPackageName());
                resID2 = context.getResources().getIdentifier(drawablename2, "drawable", context.getPackageName());
            }
            matchlist.add(createMovie(name, resID, resID2, description, stage, length, score, score2, score3, score4, score5,
                    score6, score7, score8, score9, score10, stars, stars2));

        }
    }


    private HashMap createMovie(String name, int image, int image2, String description, String stage,
                                String length, String score, String score2, String score3, String score4, String score5,
                                String score6, String score7, String score8, String score9, String score10, String stars, String stars2) {
        HashMap match = new HashMap();
        match.put("image", image);
        match.put("image2", image2);
        match.put("name", name);
        match.put("description", description);
        match.put("stage", stage);
        match.put("length", length);
        match.put("score", score);
        match.put("score2", score2);
        match.put("score3", score3);
        match.put("score4", score4);
        match.put("score5", score5);
        match.put("score6", score6);
        match.put("score7", score7);
        match.put("score8", score8);
        match.put("score9", score9);
        match.put("score10", score10);
        match.put("stars", stars);
        match.put("stars2", stars2);
        match.put("selection", false);
        return match;
    }

    public String loadMovieJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("tennis.json");
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