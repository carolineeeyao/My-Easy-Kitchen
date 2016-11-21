package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.myeasykitchen.myeasykitchen.R;

public class GroceryActivity extends AppCompatActivity {

    Context context = this;
//    private ListView mRecyclerView;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
//    private GroceryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

//        mRecyclerView = (RecyclerView) findViewById(R.id.activity_grocery_recycler_view);
////        mRecyclerView = (ListView) findViewById(R.id.activity_grocery_recycler_view);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//
//        KitchenAdapter adapter = new KitchenAdapter(this, R.layout.listview_item_row, StaticData.kitchen_list);
//
//        mAdapter = new GroceryAdapter(StaticData.grocery_list);
////        mAdapter = new GroceryAdapter(this,R.layout.grocery_item_row, StaticData.grocery_list);
//        mRecyclerView.setAdapter(mAdapter);
//        Button add_button = (Button)findViewById(R.id.add_button);
//
//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(context, AddItemActivity.class);
//                context.startActivity(myIntent);
//                mAdapter.notifyDataSetChanged();
//            }
//        });
    }

    protected void onStart() {
        super.onStart();
//        mAdapter.update();
//        mAdapter.notifyDataSetChanged();
    }
}
