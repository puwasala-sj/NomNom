package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nomnom.Database.DatabaseHelper;

public class EditRegister extends AppCompatActivity {
    private static final String TAG = "EditRegister";
    DatabaseHelper db;

    Button delete;
    Button update;
    EditText editText;

    private String selectedUsername;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register);

        delete = (Button)findViewById(R.id.deleteUser);
        update = (Button)findViewById(R.id.updateUser);
        editText = (EditText)findViewById(R.id.editText);
        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedUsername = receivedIntent.getStringExtra("username");
        editText.setText(selectedUsername);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                if(!name.equals("")){
                    db.updateUser(name,selectedID,selectedUsername);
                    Toast.makeText(EditRegister.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditRegister.this, "You must enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteUser(selectedID,selectedUsername);
                editText.setText("");
                Toast.makeText(EditRegister.this, "Delete successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
