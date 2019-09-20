package com.example.nomnom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    Button deletefeed;
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
                Intent RList = new Intent(FeedBack.this,ListFeedback.class);
                startActivity(RList);
            }
        });
        AddFeed();
    }
    public void AddFeed(){
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = Topic.getText().toString();
                String description = Description.getText().toString();

                if(topic.isEmpty()|| description.isEmpty()) {
                    Toast.makeText(FeedBack.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }else if(!topic.matches("^[a-zA-Z]+$")){
                    Toast.makeText(FeedBack.this, "Topic should be letters only", Toast.LENGTH_SHORT).show();
                }else{
                    boolean result = db.addFeedback(topic,description);
                    if (result == true) {
                         Toast.makeText(FeedBack.this, "Thank you", Toast.LENGTH_SHORT).show();
                     } else {
                        Toast.makeText(FeedBack.this, "Sorry something went wrong", Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });
    }

}
