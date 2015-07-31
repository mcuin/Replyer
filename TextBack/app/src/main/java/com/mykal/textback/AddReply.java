package com.mykal.textback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                ReplyList.names.add(nameField.toString());
                ReplyList.messages.add(messageField.toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("newNameEntry", ReplyList.names);
                returnIntent.putExtra("newMessageEntry", ReplyList.messages);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}
