package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        listBookings();
    }

    private void listBookings() {
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
    }
}
