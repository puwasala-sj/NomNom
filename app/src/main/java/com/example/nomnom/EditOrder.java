package com.example.nomnom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditOrder extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextid;
    EditText editName, editAddress, editQuantity;

    Button button_View;
    Button button_Update;
    Button button_Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        button_View = (Button) findViewById(R.id.buttonView);
        button_Update = (Button) findViewById(R.id.buttonEdit);
        button_Delete = (Button) findViewById(R.id.buttonDelete);
        editTextid = (EditText) findViewById(R.id.editTextId);
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void viewAll() {
        button_View.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            ///Show message
                            showMessage("ERROR! ", "Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Address :" + res.getString(2) + "\n");
                            buffer.append("Quantity :" + res.getString(3) + "\n\n");
                        }

                        //Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData() {
        button_Update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateData(editTextid.getText().toString(), (editName.getText().toString()),
                                editAddress.getText().toString(),
                                editQuantity.getText().toString());
                        if (isUpdate == true) {
                            Toast.makeText(EditOrder.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(EditOrder.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void DeleteData() {
        button_Delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDb.deleteData(editTextid.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(EditOrder.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditOrder.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}


