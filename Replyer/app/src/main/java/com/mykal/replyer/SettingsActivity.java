package com.mykal.replyer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ListView versionList = (ListView) findViewById(R.id.versionListView);

        ListView defaultReplyList = (ListView) findViewById(R.id.defaultReplyListView);
    }
}
