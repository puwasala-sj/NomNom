package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class Register extends AppCompatActivity {
    EditText Username;
    EditText Email;
    EditText Password;
    EditText ConfirmPassword;
    Button RegisterNow;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        Username = (EditText)findViewById(R.id.username);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        ConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        RegisterNow = (Button) findViewById(R.id.register);

        RegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Username.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String confirmpassword = ConfirmPassword.getText().toString();

                if (password.equals(confirmpassword)) {
                    boolean val = db.addUser(username, email, password );
                    if (val == true) {
                        Toast.makeText(Register.this, "Welcome to the Nom Nom Family", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Register.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(Register.this, "Oops Registration error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}