package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class PlaceOrder extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editAddress,editContactNo, editQuantity;
    Button btnAddData;
    Button viewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editContactNo = (EditText) findViewById(R.id.editText_contact);
        editQuantity = (EditText) findViewById(R.id.editText_quantity);
        btnAddData = (Button) findViewById(R.id.order);
        viewOrder =(Button) findViewById(R.id.viewOrder);
        viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feed = new Intent(PlaceOrder.this, EditOrder.class);
                startActivity(feed);
            }
        });
        AddData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        String name = editName.getText().toString();
                        String address = editAddress.getText().toString();
                        String contact = editContactNo.getText().toString();
                        String quantity = editQuantity.getText().toString();

                        if (name.isEmpty() || address.isEmpty() || contact.isEmpty() || quantity.isEmpty()) {
                            Toast.makeText(PlaceOrder.this, "Fill all details", Toast.LENGTH_SHORT).show();
                        } else if(!name.matches("^[a-zA-Z]+$")) {
                            Toast.makeText(PlaceOrder.this, "Name should be letters only", Toast.LENGTH_SHORT).show();
                        } else if(!contact.matches("^[0-9]+$")) {
                            Toast.makeText(PlaceOrder.this, "Contact number should be numbers only", Toast.LENGTH_SHORT).show();
                        }else if(!quantity.matches("^[0-9]+$")) {
                            Toast.makeText(PlaceOrder.this, "Quantity should be numbers only", Toast.LENGTH_SHORT).show();
                        }else{
                            boolean isInserted = myDb.addInfoOrder(name, address, contact, quantity);
                            if (isInserted = true) {
                                Toast.makeText(PlaceOrder.this, "Order placed", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PlaceOrder.this, "Error!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
