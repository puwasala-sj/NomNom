package com.example.nomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
public class Menu extends AppCompatActivity {

    GridView gridView1;

    String[] fastfoodNames1 = {"BBQ Chicken Pizza","Hot cuttlefish pizza","Tandoori Chicken Pizza","Spicy Seafood Pizza", "Supreme Garlic Prawns","Grilled chicken burger","Spicy chicken burger",
            "Classic hamburger","Crispy chicken burger", "Classic veggie burger","Italian Submarine","Chicken submarine","Grilled chicken submarine","Baked chicken tacos","Chipotle chicken tacos",
            "Healthy Shrimp Tacos","Chicago hot dog","Cooker Chili Dogs","BBQ Chicken Wings","Instant French Fries","Strawberry Cheesecake","Chocolate brownies","Virgin Mojito","Mineral Water"};
    int[] fastfoodImages1 = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,R.drawable.img14,R.drawable.img15,R.drawable.img16,R.drawable.img17,R.drawable.img18,R.drawable.img19,R.drawable.img20,R.drawable.img21,R.drawable.img22,R.drawable.img23,R.drawable.img24};

    String[] fastfoodPrice1 = {"Rs.1999.00","Rs.2150.00","Rs.1850.00","Rs.2280.00","Rs.2099.00","Rs.790.00","Rs.890.00","Rs.500.00","Rs.860.00","Rs.430.00","Rs.520.00","Rs.590.00","Rs.610.00","Rs.630.00","Rs.540.00","Rs.900.00","Rs.390.00","Rs.310.00",
            "Rs.490.00","Rs.290.00","Rs.420.00","Rs.350.00","Rs.510.00","Rs.200.00"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        gridView1 = findViewById(R.id.gridview1);

        CustomAdapter customAdapter1 = new CustomAdapter();
        gridView1.setAdapter(customAdapter1);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),GridItem1.class);
                intent.putExtra("name",fastfoodNames1[i]);
                intent.putExtra("image",fastfoodImages1[i]);
                intent.putExtra("price",fastfoodPrice1[i]);
                startActivity(intent);

            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return fastfoodImages1.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = getLayoutInflater().inflate(R.layout.row_data,null);

            TextView name1 = view2.findViewById(R.id.fastfood1);
            ImageView image1 = view2.findViewById(R.id.images1);
            TextView price1 = view2.findViewById(R.id.fastprice1);
            name1.setText(fastfoodNames1[i]);
            image1.setImageResource(fastfoodImages1[i]);
            price1.setText(fastfoodPrice1[i]);
            return view2;
        }
    }
}
