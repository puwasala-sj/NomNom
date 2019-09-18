package com.example.nomnom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

import java.util.ArrayList;

public class EditOrder extends AppCompatActivity {
    private static final String TAG = "EditOrder";
    DatabaseHelper db;

    private ListView listView;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        listView = (ListView)findViewById(R.id.ListView);
        db = new DatabaseHelper(this);
        listOrders();
    }

    private void listOrders() {
        Log.d(TAG,"listOrders: Displaying data in the ListView");
        Cursor orders = db.getAllData();
        ArrayList<String> listOrders = new ArrayList<>();
        while(orders.moveToNext()){
            listOrders.add("Name :"+orders.getString(1));
            listOrders.add("Address :"+orders.getString(2));
            listOrders.add("Contact Number :"+orders.getString(3));
            listOrders.add("Quantity :"+orders.getString(4));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listOrders);
        listView.setAdapter(adapter);

    }
}


