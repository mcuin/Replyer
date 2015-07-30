package com.mykal.textback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        EditText emailSubject = (EditText) findViewById(R.id.emailSubject);
        EditText emailMessage = (EditText) findViewById(R.id.emailMessage);
        Button sendButton = (Button) findViewById(R.id.sendButton);


    }
}
