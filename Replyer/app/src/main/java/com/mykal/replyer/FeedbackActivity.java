package com.mykal.replyer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText subjectField = (EditText) findViewById(R.id.emailSubject);
        final EditText messageField = (EditText) findViewById(R.id.emailMessage);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (subjectField.toString().isEmpty() || messageField.toString().isEmpty()) {
                    Toast.makeText(FeedbackActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    String subject = subjectField.getText().toString();
                    String message = messageField.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_SEND);

                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"mykcuin@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    try {
                        startActivity(Intent.createChooser(intent, "Send Mail"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(FeedbackActivity.this, "No email clients found", Toast.LENGTH_SHORT).show();
                    }

                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }
}
