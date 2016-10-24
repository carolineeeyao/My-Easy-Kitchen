package com.myeasykitchen.myeasykitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class KitchenActivity extends AppCompatActivity {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        DatabaseClient databaseClient = new DatabaseClient("ali");
        databaseClient.displayList("1", (ListView)findViewById(R.id.listView1),this);
//        KitchenAdapter adapter = new KitchenAdapter(this, R.layout.listview_item_row, StaticData.kitchen_list);
//
//        listView1 = (ListView)findViewById(R.id.listView1);
//        listView1.setAdapter(adapter);
    }
}
