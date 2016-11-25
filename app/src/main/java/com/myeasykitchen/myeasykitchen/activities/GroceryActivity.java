package com.myeasykitchen.myeasykitchen.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.myeasykitchen.myeasykitchen.DatabaseClient;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.fragments.ItemMenuFragment;
import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.viewholder.ItemViewHolder;

public class GroceryActivity extends AppCompatActivity {

    Context context = this;

    private DatabaseClient databaseClient;

    private FirebaseRecyclerAdapter<Item, ItemViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);


        databaseClient = DatabaseClient.getInstance();
        mRecycler = (RecyclerView) findViewById(R.id.activity_kitchen_recycler_view);
        Button add_button = (Button) findViewById(R.id.add_kitchen_item);


        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        mAdapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(Item.class, R.layout.kitchen_item_row,
                ItemViewHolder.class, databaseClient.getList(getIntent().getStringExtra("list name"))) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, final Item model, int position) {
                final DatabaseReference itemRef = getRef(position);

                viewHolder.bindToItem(model, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        FragmentManager fm = getSupportFragmentManager();
                        ItemMenuFragment fragment = ItemMenuFragment.newInstance(model, getIntent().getStringExtra("list name"), itemRef.getKey());
                        fragment.show(fm, "fragment_item_menu");

                        return false;
                    }
                });
            }
        };

        mRecycler.setAdapter(mAdapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, AddItemActivity.class);
                myIntent.putExtra("list name", getIntent().getStringExtra("list name"));
                context.startActivity(myIntent);
            }
        });

    }
}