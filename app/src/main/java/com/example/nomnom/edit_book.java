package com.example.nomnom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class edit_book extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name1, contact1,des,people;

    Button button_View1;
    Button button_Update1;
    Button button_Delete1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        button_View1 = (Button) findViewById(R.id.viewBook);
        button_Update1 = (Button) findViewById(R.id.editBook);
        button_Delete1 = (Button) findViewById(R.id.deleteBook);
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void viewAll() {
        button_View1.setOnClickListener(
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
                            buffer.append("ContactNumber :" + res.getString(2) + "\n");
                            buffer.append("Description :" + res.getString(3) + "\n");
                            buffer.append("People :" + res.getString(4) + "\n\n");
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
        button_Update1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateOrder(name1.getText().toString(),contact1.getText().toString(),des.getText().toString(),people.getText().toString());
                        if (isUpdate == true) {
                            Toast.makeText(edit_book.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(edit_book.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void DeleteData() {
        button_Delete1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDb.deleteOrder(name1.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(edit_book.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(edit_book.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



}
