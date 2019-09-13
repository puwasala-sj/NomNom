package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button menu;
    Button feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity();
            }
        });
        feedback = (Button) findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedbackActivity();
            }
        });
    }

    public void openMenuActivity(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void openFeedbackActivity(){
        Intent intent = new Intent(this, FeedBack.class);
        startActivity(intent);
    }
}
