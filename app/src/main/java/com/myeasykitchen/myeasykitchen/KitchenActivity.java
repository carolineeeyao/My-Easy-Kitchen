package com.myeasykitchen.myeasykitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class KitchenActivity extends AppCompatActivity {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        ItemAdapter adapter = new ItemAdapter(this, R.layout.listview_item_row, StaticData.kitchen_list);

        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
    }
}
