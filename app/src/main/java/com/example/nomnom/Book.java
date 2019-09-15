package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nomnom.Database.DatabaseHelper;

public class Book extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText Bname, Bcontact,Bdes, Bpeople;
    Button BbtnBook;
    Button viewBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        myDb = new DatabaseHelper(this);

        Bname = (EditText) findViewById(R.id.name1);
        Bcontact = (EditText) findViewById(R.id.contact1);
        Bdes = (EditText) findViewById(R.id.des);
        Bpeople = (EditText) findViewById(R.id.people);
        BbtnBook = (Button) findViewById(R.id.btnBook);
        viewBook =(Button) findViewById(R.id.viewBook);
        viewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
     //           Intent feed = new Intent(Book.this, EditBook.class);
     //           startActivity(feed);
            }
        });



    }
}
