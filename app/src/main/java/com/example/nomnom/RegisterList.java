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

import java.sql.Array;
import java.util.ArrayList;

public class RegisterList extends AppCompatActivity {
    private static final String TAG = "RegisterList";
    DatabaseHelper db;

    private ListView listView;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_list);
        listView = (ListView)findViewById(R.id.ListView);
        db = new DatabaseHelper(this);

        listUsers();
    }

    public void listUsers(){
        Log.d(TAG,"listUsers: Displaying data in the ListView");
        Cursor user = db.getAllUser();
        ArrayList<String> listUsers = new ArrayList<>();
        while(user.moveToNext()){
            listUsers.add("Username :"+user.getString(1));
            listUsers.add("Email :"+user.getString(2));
            listUsers.add("Password :"+user.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listUsers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String username = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + username);

                Cursor data = db.getUserID(username); //get the id associated with that name
                int userID = 0;
                while(data.moveToNext()){
                    userID = data.getInt(0);
                }
                if(userID > 0){
                    Log.d(TAG, "onItemClick: The ID is: " + userID);
                    Intent editScreenIntent = new Intent(RegisterList.this, EditRegister.class);
                    editScreenIntent.putExtra("userId",userID);
                    editScreenIntent.putExtra("Username",username);
                    startActivity(editScreenIntent);
                }
                else{
                    Toast.makeText(RegisterList.this, "No ID found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
