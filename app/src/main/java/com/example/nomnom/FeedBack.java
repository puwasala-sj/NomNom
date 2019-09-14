package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class FeedBack extends AppCompatActivity {
    EditText Topic;
    EditText Description;
    Button Send;
    Button viewfeed;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        db = new DatabaseHelper(this);
        Topic = (EditText)findViewById(R.id.topic);
        Description = (EditText)findViewById(R.id.description);
        Send = (Button) findViewById(R.id.send);
        viewfeed = (Button) findViewById(R.id.view);
        viewfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feed = new Intent(FeedBack.this, FeedbackList.class);
                startActivity(feed);
            }
        });
        AddFeed();
    }
    public void AddFeed(){
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean result = db.addFeedback(Topic.getText().toString(),Description.getText().toString());
                if (result == true) {
                    Toast.makeText(FeedBack.this, "Thank you", Toast.LENGTH_SHORT).show();
                    Intent feed = new Intent(FeedBack.this, FeedbackList.class);
                    startActivity(feed);
                } else {
                    Toast.makeText(FeedBack.this, "Sorry something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
