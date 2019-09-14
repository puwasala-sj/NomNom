package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.nomnom.Database.DatabaseHelper;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])"+          //at least one digit
                    "(?=.*[a-zA-Z])"+       //enter letters
                    "(?=\\s+$)"+            //no white spaces
                    ".{3,}" +             //at least three characters
                    "$");

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
        Username = (EditText) findViewById(R.id.username);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        ConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        RegisterNow = (Button) findViewById(R.id.registerNow);
        AddUser();

    }

    public void AddUser() {
            RegisterNow.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String username = Username.getText().toString();
                    String email = Email.getText().toString();
                    String password = Password.getText().toString();
                    String confirmpassword = ConfirmPassword.getText().toString();

                    if(username.isEmpty()|| email.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()) {
                        Toast.makeText(Register.this, "Fill all details", Toast.LENGTH_SHORT).show();
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Toast.makeText(Register.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    }else if(!PASSWORD_PATTERN.matcher(password).matches()){
                        Toast.makeText(Register.this, "Password should include 3 characters and numbers,", Toast.LENGTH_SHORT).show();
                    }else if (password.equals(confirmpassword)) {
                        boolean val = db.addUser(username, email, password);
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