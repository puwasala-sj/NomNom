package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText Username;
    EditText Password;
    Button Login;
    TextView Notregister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        Username = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Notregister = (TextView)findViewById(R.id.register1);
        Notregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerintent = new Intent(LoginActivity.this, Register.class);
                startActivity(registerintent);
            }
        });

        Login = (Button) findViewById(R.id.btn1);
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                String username = Username.getText().toString();
                String password = Password.getText().toString();

                if(username.isEmpty()||password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean result = db.checkUser(username, password);
                    if (result == true) {
                        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
