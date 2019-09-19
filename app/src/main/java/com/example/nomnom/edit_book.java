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

public class edit_book extends AppCompatActivity {
    private static final String TAG = "edit_book";
    DatabaseHelper db;

    private ListView listView;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        listView = (ListView)findViewById(R.id.ListView);
        db = new DatabaseHelper(this);

        listBooking();
    }

    private void listBooking() {
        Log.d(TAG,"listBooking: Displaying data in the ListView");
        Cursor bookings = db.getAllBookings();
        ArrayList<String> listBooking = new ArrayList<>();
        while(bookings.moveToNext()){
            listBooking.add("Name :"+bookings.getString(1));
            listBooking.add("Contact Number :"+bookings.getString(2));
            listBooking.add("Description :"+bookings.getString(3));
            listBooking.add("People:"+bookings.getString(4));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listBooking);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = db.getbookID(name); //get the id associated with that name
                int bID = -1;
                while(data.moveToNext()){
                    bID = data.getInt(0);
                }
                if(bID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + bID);
                    Intent editScreenIntent = new Intent(edit_book.this, DeleteBook.class);
                    editScreenIntent.putExtra("bID",bID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    Toast.makeText(edit_book.this, "No ID found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


