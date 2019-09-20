package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                Intent feed = new Intent(Book.this, edit_book.class);
                startActivity(feed);
            }
        });
        AddData();
    }

    public void AddData() {
        BbtnBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = Bname.getText().toString();
                String contact = Bcontact.getText().toString();
                String description = Bdes.getText().toString();
                String people = Bpeople.getText().toString();

                if (name.isEmpty() || contact.isEmpty() || description.isEmpty() || people.isEmpty()) {
                    Toast.makeText(Book.this, "Fill all details", Toast.LENGTH_SHORT).show();
                } else if(!name.matches("^[a-zA-Z]+$")) {
                    Toast.makeText(Book.this, "Enter letters only for the name!", Toast.LENGTH_SHORT).show();
                } else if(!contact.matches("^[0-9]+$")) {
                    Toast.makeText(Book.this, "Contact number should be numbers only", Toast.LENGTH_SHORT).show();
                } else if(!description.matches("^[a-zA-Z]+$")) {
                    Toast.makeText(Book.this, "Enter letters only in the description(Ex: Wedding/ Birthday", Toast.LENGTH_SHORT).show();
                }else if(!people.matches("^[0-9]+$")) {
                    Toast.makeText(Book.this, "Enter numbers only for the Number of People", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isInserted = myDb.addInfoBook(name, contact, description, people);
                    if (isInserted = true) {
                        Toast.makeText(Book.this, "Booking Completed", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Book.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }



}
