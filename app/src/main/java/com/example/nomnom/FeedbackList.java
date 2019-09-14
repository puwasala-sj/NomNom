package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.nomnom.Database.DatabaseHelper;

public class FeedbackList extends AppCompatActivity {
    DatabaseHelper db;
    EditText topic;
    EditText description;

    Button viewfeed;
    Button deletefeed;
    Button updatefeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
    }
}
