package com.mykal.replyer;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ListView versionList = (ListView) findViewById(R.id.versionListView);

        TextView versionTitle = new TextView(this);

        versionTitle.setText("Version");

        versionList.addHeaderView(versionTitle);

        List<String> versionData = new ArrayList<>();

        versionData.add("1.0");

        ArrayAdapter<String> versionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, versionData);

        versionList.setAdapter(versionAdapter);

        ListView defaultReplyList = (ListView) findViewById(R.id.defaultReplyListView);

        TextView defaultReply = new TextView(this);

        defaultReply.setText("Default Reply");

        defaultReplyList.addHeaderView(defaultReply);

        List
    }
}
