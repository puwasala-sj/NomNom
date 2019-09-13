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
        Password = (EditText)findViewById(R.id.email);
        Login = (Button) findViewById(R.id.login);
        Notregister = (TextView)findViewById(R.id.text2);
        Notregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this,Register.class);
                startActivity(register);
            }
        });

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                Boolean result = db.checkUser(username, password);
                if (result ==  true)
                {
                    Toast.makeText(LoginActivity.this, "Welcome",Toast.LENGTH_SHORT) .show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Error",Toast.LENGTH_SHORT) .show();
                }
            }
        });

    }
}
