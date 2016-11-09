package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class KitchenActivity extends AppCompatActivity {

    Context context = this;

    private ListView listView1;

    private DatabaseClient databaseClient;

    private FirebaseRecyclerAdapter<Item, ItemViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

//        databaseClient = DatabaseClient.getInstance();
//        mRecycler = (RecyclerView) findViewById(R.id.activity_kitchen_recycler_view);
//        Button add_button = (Button)findViewById(R.id.add_kitchen_item);
//
//
//        mManager = new LinearLayoutManager(this);
//        mManager.setReverseLayout(true);
//        mManager.setStackFromEnd(true);
//        mRecycler.setLayoutManager(mManager);
//        mAdapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(Item.class, R.layout.listview_item_row,
//                ItemViewHolder.class, databaseClient.getList("1")) {
//            @Override
//            protected void populateViewHolder(ItemViewHolder viewHolder, Item model, int position) {
//                final DatabaseReference itemRef = getRef(position);
//
//                viewHolder.bindToItem(model);
//            }
//        };
//
//        mRecycler.setAdapter(mAdapter);
//
//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(context, AddItemActivity.class);
//                context.startActivity(myIntent);
//            }
//        });



    }
}
