package com.example.nomnom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomnom.Database.DatabaseHelper;

public class DeleteBook extends AppCompatActivity {
    Button deleteBook;
    Button updateBook;
    EditText eText;
    DatabaseHelper db;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        updateBook = (Button) findViewById(R.id.editBook);
        deleteBook = (Button) findViewById(R.id.deleteBook);
        eText = (EditText) findViewById(R.id.eText);
        db = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("bID",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        eText.setText(selectedName);

        updateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eText.getText().toString();
                if(!name.equals("")){
                    db.updateBook(name,selectedID,selectedName);
                    Toast.makeText(DeleteBook.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DeleteBook.this, "Enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteBook(selectedID,selectedName);
                eText.setText("");
                Toast.makeText(DeleteBook.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

