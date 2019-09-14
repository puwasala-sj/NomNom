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
        viewfeedback();
        deletefeedback();
        updatefeedback();
    }

    private void viewfeedback() {
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
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void deletefeedback() {
        deletefeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = db.deleteFeedback(fID.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(FeedbackList.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(FeedbackList.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updatefeedback() {

    }
}
