package com.example.nomnom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        viewfeed = (Button) findViewById(R.id.view);
        deletefeed = (Button)findViewById(R.id.deletefeed);
        updatefeed = (Button)findViewById(R.id.updatefeed);
        deletefeedback();
        updatefeedback();
    }

    private void deletefeedback() {
        deletefeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = db.deletefeed(topic.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(FeedbackList.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(FeedbackList.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updatefeedback() {
            updatefeed.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean isUpdate = db.updatefeedback((topic.getText().toString()),(description.getText().toString()));
                            if (isUpdate == true) {
                                Toast.makeText(FeedbackList.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(FeedbackList.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );
        }
}
