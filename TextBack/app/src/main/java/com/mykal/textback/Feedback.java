package com.mykal.textback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    String subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText emailSubject = (EditText) findViewById(R.id.emailSubject);
        final EditText emailMessage = (EditText) findViewById(R.id.emailMessage);
        Button sendButton = (Button) findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = emailSubject.getText().toString();
                message = emailMessage.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mykcuin@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                try {
                    startActivity(Intent.createChooser(intent, "Send mail"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Feedback.this, "No email clients found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
