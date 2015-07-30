package com.mykal.textback;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ReplyList extends AppCompatActivity {

    ListView replies;
    static ArrayList<String> entries;
    static ArrayAdapter<String> adapterEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_list);

        replies = (ListView) findViewById(R.id.replyList);
        entries = new ArrayList<>();
        adapterEntries = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, entries);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReplyList.this, AddReply.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reply_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            Intent intent = new Intent(ReplyList.this, Feedback.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(adapterEntries.getCount() + "Resume");
        if (adapterEntries.isEmpty()) {
            replies.setEmptyView(replies);
        } else {
            replies.setAdapter(adapterEntries);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                entries = data.getStringArrayListExtra("newEntry");
                adapterEntries = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, entries);
            }
        }
    }
}
