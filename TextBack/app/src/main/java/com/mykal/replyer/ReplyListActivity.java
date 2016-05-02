package com.mykal.replyer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ReplyListActivity extends AppCompatActivity {

    SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReplyListActivity.this, AddReplyActivity.class);

                startActivityForResult(intent, 0);
            }
        });


    }

    @Override
    protected void onResume() {

        super.onResume();

        final ListView listView = (ListView) findViewById(R.id.replyList);
        final DatabaseHelper db = new DatabaseHelper(this);
        final Cursor cursor = db.getAllReplies();

        String[] columns = new String[] {
                DatabaseHelper.REPLY_COLUMN_NAME
        };
        int[] labels = new int[] {
                android.R.id.text1
        };

        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_single_choice, cursor, columns, labels, 0);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(cursorAdapter);

        db.close();

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ReplyListActivity.this, AddReplyActivity.class);

                TextView nameView = (TextView) findViewById(android.R.id.text1);
                TextView messageView = (TextView) findViewById(android.R.id.text2);

                intent.putExtra("name", nameView.getText());
                intent.putExtra("message", messageView.getText());


                startActivityForResult(intent, 1);
            }
        });*/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ReplyListActivity.this, AddReplyActivity.class);

                TextView nameView = (TextView) findViewById(android.R.id.text1);

                intent.putExtra("name", nameView.getText());


                startActivityForResult(intent, 1);

                return true;

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
            Intent intent = new Intent(ReplyListActivity.this, FeedbackActivity.class);

            startActivityForResult(intent, 1);
        }

        if (id == R.id.action_settings) {
            Intent intent = new Intent(ReplyListActivity.this, SettingsActivity.class);

            startActivityForResult(intent, 2);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            cursorAdapter.notifyDataSetChanged();
        }
    }
}
