package com.example.raghavendra.sportslivescore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raghavendra on 4/22/2015.
 */
public class DrawerData {
    List<Map<String,?>> drawerList;
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;
    public static final int TYPE3 = 3;

    public List<Map<String, ?>> getDrawerList() {
        return drawerList;
    }

    public int getSize(){
        return drawerList.size();
    }

    public HashMap getItem(int i){
        return (HashMap) drawerList.get(i);
    }

    public DrawerData(){
        HashMap item;
        drawerList =new ArrayList<Map<String,?>>();

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.icon1); item.put("title", "Home");
        drawerList.add(item);
        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.icon2); item.put("title", "Cricket Schedule");
        drawerList.add(item);
        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.icon3); item.put("title", "Soccer Schedule");
        drawerList.add(item);
        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.icon4); item.put("title", "Tennis Schedule");
        drawerList.add(item);
        item = new HashMap();
        /*item.put("type",TYPE1); item.put("icon", R.drawable.video); item.put("title", "Highlights");
        drawerList.add(item);*/

        item = new HashMap();
        item.put("type",TYPE2); item.put("icon", R.drawable.simple_line);
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE3); item.put("title", "About me");
        drawerList.add(item);
        item = new HashMap();
        item.put("type",TYPE3);  item.put("title", "Settings");
        drawerList.add(item);
        item = new HashMap();
        /*item.put("type",TYPE3);  item.put("title", "Log out");
        drawerList.add(item);*/




    }



}