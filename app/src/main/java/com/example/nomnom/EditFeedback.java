package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class EditFeedback extends AppCompatActivity {

    private static final String TAG = "EditFeedback";
    DatabaseHelper db;

    Button delete;
    Button update;
    EditText editText;

    private String selectedTopic;
    private int selectedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feedback);
        delete = (Button)findViewById(R.id.deletefeed);
        update = (Button)findViewById(R.id.updatefeed);
        editText = (EditText)findViewById(R.id.edText1);
        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedTopic = receivedIntent.getStringExtra("topic");
        editText.setText(selectedTopic);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = editText.getText().toString();
                if(!topic.equals("")){
                    db.updateFeed(topic,selectedID,selectedTopic);
                    Toast.makeText(EditFeedback.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditFeedback.this, "You must enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteFeed(selectedID,selectedTopic);
                editText.setText("");
                Toast.makeText(EditFeedback.this, "Delete successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
