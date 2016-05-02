package com.mykal.replyer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddReplyActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reply);

        db = new DatabaseHelper(this);
        final EditText replyName = (EditText) findViewById(R.id.replyName);
        final EditText replyMessage = (EditText) findViewById(R.id.replyMessage);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button blankButton = (Button) findViewById(R.id.cancelorDeleteButton);

        if (getIntent().hasExtra("name")) {

            Cursor cursor = db.getReply(getIntent().getStringExtra("name"));
            cursor.moveToFirst();
            String previousName = cursor.getString(cursor.getColumnIndex("name"));
            String previousMessage = cursor.getString(cursor.getColumnIndex("message"));

            blankButton.setText("Delete");
            replyName.setText(previousName);
            replyMessage.setText(previousMessage);

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(replyName.getText());
                    if (replyName.getText().equals(null) || replyMessage.getText().equals(null)) {
                        Toast.makeText(AddReplyActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    } else {

                        String name = replyName.getText().toString();
                        String message = replyMessage.getText().toString();

                        db.updateReply(name, message);

                        setResult(RESULT_OK);
                        finish();
                    }
                }
            });

            blankButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deleteReply(getIntent().getStringExtra("name"));

                    setResult(RESULT_OK);
                    finish();
                }
            });
        } else {

            blankButton.setText("Cancel");

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (replyName.toString().isEmpty() || replyMessage.toString().isEmpty()) {
                        Toast.makeText(AddReplyActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    } else {

                        String name = replyName.getText().toString();
                        String message = replyMessage.getText().toString();

                        db.insertReply(name, message);

                        setResult(RESULT_OK);
                        finish();
                    }
                }
            });

            blankButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            });
        }
    }
}
