package com.myeasykitchen.myeasykitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import static com.myeasykitchen.myeasykitchen.R.id.listView1;

public class GroceryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private GroceryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_grocery_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        KitchenAdapter adapter = new KitchenAdapter(this, R.layout.listview_item_row, StaticData.kitchen_list);

        mAdapter = new GroceryAdapter(StaticData.grocery_list);
        mRecyclerView.setAdapter(mAdapter);
    }
}
