package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

import java.util.ArrayList;

public class ListFeedback extends AppCompatActivity {
    private static final String TAG = "ListFeedback";
    DatabaseHelper db;

    private ListView listView;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_feedback);
        listView = (ListView)findViewById(R.id.ListView);
        db = new DatabaseHelper(this);

        listFeedback();
    }

    private void listFeedback() {
        Log.d(TAG,"listUsers: Displaying data in the ListView");
        Cursor feed = db.getAllfeedback();
        ArrayList<String> listfeedback = new ArrayList<>();
        while(feed.moveToNext()){
            listfeedback.add(feed.getString(1));
            listfeedback.add(feed.getString(2));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listfeedback);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String topic = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + topic);

                Cursor data = db.getfeedID(topic); //get the id associated with that name
                int id = -1;
                while(data.moveToNext()){
                    id = data.getInt(0);
                }
                if(id > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + id);
                    Intent editScreenIntent = new Intent(ListFeedback.this, EditFeedback.class);
                    editScreenIntent.putExtra("id",id);
                    editScreenIntent.putExtra("topic",topic);
                    startActivity(editScreenIntent);
                }
                else{
                    Toast.makeText(ListFeedback.this, "No ID found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
