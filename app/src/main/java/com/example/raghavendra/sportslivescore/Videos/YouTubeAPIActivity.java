package com.example.raghavendra.sportslivescore.Videos;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.raghavendra.sportslivescore.R;
import com.example.raghavendra.sportslivescore.adapter.Array_Adapter;
import com.example.raghavendra.sportslivescore.adapter.ListViewItem;

import java.util.ArrayList;
import java.util.List;

public class YouTubeAPIActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<ListViewItem> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_home);

        PackageInfo packageInfo = null;
        PackageManager pm = getPackageManager();
        try {
            packageInfo = pm.getPackageInfo(
                    getPackageName(), PackageManager.GET_ACTIVITIES | PackageManager.GET_META_DATA);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(getString(R.string.loggingTag), "Could not find package with name " + getPackageName());
            finish();
        }

        int appMinVersion = packageInfo.applicationInfo.targetSdkVersion;

        activities = new ArrayList<ListViewItem>();
        for (ActivityInfo activityInfo : packageInfo.activities) {
            String name = activityInfo.name;
            Bundle metaData = activityInfo.metaData;

            if (metaData != null
                    && metaData.getBoolean(getString(R.string.isLaunchableActivity), false)) {
                String label = getString(activityInfo.labelRes);
                int minVersion = metaData.getInt(getString(R.string.minVersion), appMinVersion);
                activities.add(new Demo(label, name, minVersion));
            }
        }

        ListView listView = (ListView) findViewById(R.id.demo_list);
        Array_Adapter adapter = new Array_Adapter(this, R.layout.list_item, activities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        TextView disabledText = (TextView) findViewById(R.id.some_demos_disabled_text);
        disabledText.setText(getString(R.string.some_demos_disabled, android.os.Build.VERSION.SDK_INT));

        if (adapter.anyDisabled()) {
            disabledText.setVisibility(View.VISIBLE);
        } else {
            disabledText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Demo clickedDemo = (Demo) activities.get(position);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getPackageName(), clickedDemo.className));
        startActivity(intent);
    }

    private final class Demo implements ListViewItem {

        public final String title;
        public final int minVersion;
        public final String className;

        public Demo(String title, String className, int minVersion) {
            this.className = className;
            this.title = title;
            this.minVersion = minVersion;
        }

        @Override
        public boolean isEnabled() {
            return android.os.Build.VERSION.SDK_INT >= minVersion;
        }

        @Override
        public String getDisabledText() {
            String itemDisabledText = getString(R.string.list_item_disabled);
            return String.format(itemDisabledText, minVersion);
        }

        @Override
        public String getTitle() {
            return title;
        }

    }

}