package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editContactNo = (EditText) findViewById(R.id.editText_contact);
        editQuantity = (EditText) findViewById(R.id.editText_quantity);
        btnAddData = (Button) findViewById(R.id.btn7);
        AddData();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.addInfoOrder(editName.getText().toString(),
                                editAddress.getText().toString(),
                                editContactNo.getText().toString(),
                                editQuantity.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(PlaceOrder.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PlaceOrder.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
