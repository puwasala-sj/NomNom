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

    String[] foodDescription = {"Pizza crust is layered with smokey bbq sauce, chicken pieces, creamy mozzarella, sweet pineapple, and flavorful red onion and cilantro. The best pizza recipe ever? It just might be!",
            "Hot cuttlefish tossed with chili flakes and butter, accompanied by spring onion and capsicum, blanketed with mozzarella cheese on a light, airy crust glazed in garlic butter.",
            "This Tandoori Chicken Pizza is made from scratch and loaded with intense flavor in every bite!",
            "A fiery mix of prawns, devilled fish, olives, bell peppers and onions with a double layer of mozzarella cheese.",
            "Succulent prawns, garlic, diced tomato, green capsicum and mozzarella finished with aioli drizzle.",
            "A nice break from typical hamburgers and much healthier for you. These chicken burgers are flavorful, simple to make, and delicious.",
            "Chicken burgers don’t have to be boring! This Spicy Chicken Burger is packed with a flavorful kick, covered in a delicious cheese sauce, and served on a toasted brioche bun.",
            "Sink your teeth into a delicious Nom Nom-style, hamburger recipe made from lean beef.",
            "This burger consists of a super crispy & flakey chicken patty made out of the special blend of Knorr Coating Mix top with Hellmann's Classic Mayonnaise brings it all together. Try out and titillate your taste buds.",
            "Unleash your inner herbivore with this hearty veggie burger.",
            "Craving a classic Italian sub? Try out the Nom Nom's special submarine and you'll be ecstatic!",
            "Yes, you can always grab a sub from any of the restaurants out there, but we ensure that we have the creamy and cheesy chicken sub that you can whip up at anytime.",
            "Strips of grilled chicken breast made to a Southern USA recipe. Topped with onion, tomato, lettuce and mayonnaise.",
            "Chicken Tacos are the PERFECT quick weeknight dinner. This Spicy Baked Chicken Tacos recipe has lots of flavor & none of the stress.",
            "A spicy, smoky chicken filling in soft tortillas, this moreish, low-calorie dinner is on the table in just 20 minutes.",
            "Make a weeknight meal or last minute entertainment a breeze with Easy Shrimp Tacos. Spicy, creamy and healthy!",
            "The Chicago Dog is a Windy City classic, and a big favorite with sports fans!",
            "These slow cooker hot dogs are swimming in a chili cheese hot tub of deliciousness, and only have THREE ingredients.Winner winner hot dog dinner!",
            "Chicken wings are a favorite any time of year. Grill some and believe us, you won’t have any left. You won’t need any paper napkins, they’re finger-licking good.",
            "Don't think twice to try out Our best Nom Nom French fries for hot and crispy, yet fluffy potato fries, every time!",
            "Smooth and creamy, it’s topped with delicious strawberries and strawberry sauce – the perfect summer treat!",
            "Cakey on the outside and fudgy in the middle, You’ll never buy chocolate brownies again from anywhere else!",
            "Virgin Mojito is a perfect blend of mint and lemon flavours into a refreshing mix which rejuvenates the senses and makes you calm at once.",
            "Purified water from a mineral spring that contains various minerals, such as salts and sulfur compounds."};
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
                intent.putExtra("description",foodDescription[i]);
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
