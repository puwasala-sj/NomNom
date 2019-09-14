package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nomnom.Database.DatabaseHelper;

public class RegisterList extends AppCompatActivity {
    DatabaseHelper db;
    EditText username;
    EditText email;
    EditText password;

    Button list;
    Button delete;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_list);

        list = (Button) findViewById(R.id.listUser);
        delete = (Button)findViewById(R.id.deleteUser);
        update = (Button)findViewById(R.id.updateUser);
        viewUsers();
    }

    private void viewUsers() {
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
