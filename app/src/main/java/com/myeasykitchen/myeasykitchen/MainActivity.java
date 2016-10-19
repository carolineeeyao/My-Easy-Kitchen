package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populate a little of kitchen_data
        StaticData.kitchen_data.add(new Item("bread", "1"));
        StaticData.kitchen_data.add(new Item("eggs", "1"));
        StaticData.kitchen_data.add(new Item("milk", "1"));
        StaticData.kitchen_data.add(new Item("cheese", "1"));
        StaticData.kitchen_data.add(new Item("cereal", "1"));
        StaticData.kitchen_data.add(new Item("popcorn", "1"));
        StaticData.kitchen_data.add(new Item("cookies", "1"));
        StaticData.kitchen_data.add(new Item("cake", "1"));
        StaticData.kitchen_data.add(new Item("candy", "1"));

        Button add_button = (Button)findViewById(R.id.add_button);
        Button kitchen_button = (Button)findViewById(R.id.kitchen_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, AddItemActivity.class);
                context.startActivity(myIntent);
            }
        });

        kitchen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, KitchenActivity.class);
                context.startActivity(myIntent);
            }
        });


    }
}
