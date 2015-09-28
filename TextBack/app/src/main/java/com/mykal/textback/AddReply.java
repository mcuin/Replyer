package com.mykal.textback;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddReply extends AppCompatActivity {

    static TextView nameField, messageField;
    static String name, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reply);

        final DatabaseHandler db = new DatabaseHandler(this);
        nameField = (TextView) findViewById(R.id.replyName);
        messageField = (TextView) findViewById(R.id.replyText);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        if (!getIntent().hasExtra("name")) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.addReplies();

                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            });
        } else {
            nameField.setText(getIntent().getStringExtra(("name")));
            messageField.setText(getIntent().getStringExtra("message"));
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Replies reply = db.getReply(Long.parseLong(getIntent().getStringExtra("id")));
                    db.updateReply(reply);

                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            });
        }
    }

    public static String saveName() {
        name = nameField.getEditableText().toString();

        return name;
    }

    public static String saveMessage() {
        message = messageField.getEditableText().toString();

        return message;
    }
}
