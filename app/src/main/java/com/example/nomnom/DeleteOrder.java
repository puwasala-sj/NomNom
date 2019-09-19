package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class DeleteOrder extends AppCompatActivity {

    Button delete;
    Button update;
    EditText edText;
    DatabaseHelper db;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_order);

        update = (Button) findViewById(R.id.editOrder);
        delete = (Button) findViewById(R.id.deleteOrder);
        edText = (EditText) findViewById(R.id.edText);
        db = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("oID",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        edText.setText(selectedName);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edText.getText().toString();
                if(!name.equals("")){
                    db.updateOrder(name,selectedID,selectedName);
                    Toast.makeText(DeleteOrder.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DeleteOrder.this, "Enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteOrder(selectedID,selectedName);
                edText.setText("");
                Toast.makeText(DeleteOrder.this, "Delete successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
