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
                Cursor res = db.getAllfeedback();
                if (res.getCount() == 0) {
                    ///Show message
                    showMessage("ERROR! ", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Topic :" + res.getString(1) + "\n");
                    buffer.append("Description :" + res.getString(2) + "\n\n");
                }

                //Show all data
                showMessage("Data", buffer.toString());
            }
        });
        deletefeed =(Button)findViewById(R.id.deletefeed);
        deletefeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Integer deletedRows = db.deletefeed(Topic.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(FeedBack.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(FeedBack.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                    }

        });
        AddFeed();
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
