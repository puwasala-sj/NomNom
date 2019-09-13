package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nomnom.Database.DatabaseHelper;

public class FeedBack extends AppCompatActivity {
    private EditText Topic;
    private EditText Description;
    private Button Send;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        db = new DatabaseHelper(this);
        Topic = (EditText)findViewById(R.id.topic);
        Description = (EditText)findViewById(R.id.description);
        Send = (Button) findViewById(R.id.send);
    }
}
