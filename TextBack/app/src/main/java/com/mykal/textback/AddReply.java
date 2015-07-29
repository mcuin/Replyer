package com.mykal.textback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AddReply extends AppCompatActivity {

    static TextView nameField, messageField;
    static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reply);

        nameField = (TextView) findViewById(R.id.replyName);
        messageField = (TextView) findViewById(R.id.replyText);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplyList.entries.add(nameField.getText().toString());
                ReplyList.adapterEntries = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ReplyList.entries);
                System.out.println(ReplyList.adapterEntries.getCount() + "Click");
                Intent intent = new Intent(AddReply.this, ReplyList.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_reply, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getName() {
        return name;
    }
}
