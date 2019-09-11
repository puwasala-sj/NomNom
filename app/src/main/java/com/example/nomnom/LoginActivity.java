package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Login = (Button) findViewById(R.id.btn1);
    }

    private void validate(String Username, String Password){
        if((Username == "Admin") && (Password == "1234")){

        }

    }
    public void onClick(View view){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

}
