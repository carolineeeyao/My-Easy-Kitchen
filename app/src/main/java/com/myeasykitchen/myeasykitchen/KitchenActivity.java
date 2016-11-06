package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class KitchenActivity extends AppCompatActivity {

    Context context = this;
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        DatabaseClient databaseClient = DatabaseClient.getInstance();
        databaseClient.displayList("1", (ListView)findViewById(R.id.listView1),this);

        Button add_button = (Button)findViewById(R.id.add_grocery_item);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, AddItemActivity.class);
                context.startActivity(myIntent);
            }
        });
//        KitchenAdapter adapter = new KitchenAdapter(this, R.layout.listview_item_row, StaticData.kitchen_list);
//
//        listView1 = (ListView)findViewById(R.id.listView1);
//        listView1.setAdapter(adapter);

    }
}
