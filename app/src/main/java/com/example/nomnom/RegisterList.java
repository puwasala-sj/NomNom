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

        //delete = (Button)findViewById(R.id.deleteUser);
        //DeleteData();
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
                Log.d(TAG,"onItemClick: You clicked on" + username);

                Cursor data = db.getUsernameID(username);
                int usernameID = -1;
                while(data.moveToNext()){
                    usernameID = data.getInt(0);
                }
                if(usernameID > -1){
                    Log.d(TAG,"onItemClick: The ID is: " + usernameID);
                }
            }
        });
    }

    /*public void DeleteData() {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = db.deleteUser(username.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(RegisterList.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegisterList.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/
}
