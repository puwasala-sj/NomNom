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

public class Register extends AppCompatActivity {
    private EditText Username;
    private EditText Email;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button RegisterNow;
    private TextView Login;


    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        Username = (EditText)findViewById(R.id.username);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        ConfirmPassword = (EditText) findViewById(R.id.confirmpassword);
        RegisterNow = (Button) findViewById(R.id.btn6);
        Login = (TextView)findViewById(R.id.btn1);
        Login = setOnClickListner( new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent LoginIntent = new Intent(Register.this, LoginActivity.class);
                                           startActivity(LoginIntent);
                                       }
                                   });


        Register.setOnClickListner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Username.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String confirmpassword = ConfirmPassword.getText().toString().trim();

                if (password.equals(confirmpassword)) {
                    long val = db.addUser(username, password);
                    if (val > 0) {
                        Toast.makeText(Register.this, "Welcome to the Nom Nom Family", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Register.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(Register.this, "Oops Registration error", Toast.LENGTH_SHORT).show();
                    }
                else{
                        Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        }

    }
}