package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GridItem1 extends AppCompatActivity {

    TextView name1;
    ImageView image1;
    TextView price1;

    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item1);

        name1 = findViewById(R.id.griddata1);
        image1 = findViewById(R.id.imageView1);
        price1 = findViewById(R.id.price);
        order = (Button) findViewById(R.id.place);

        Intent intent = getIntent();
        name1.setText(intent.getStringExtra("name"));
        image1.setImageResource(intent.getIntExtra("image",0));
        price1.setText(intent.getStringExtra("price"));
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order = new Intent(GridItem1.this, PlaceOrder.class);
                startActivity(order);
            }
        });
    }
}
