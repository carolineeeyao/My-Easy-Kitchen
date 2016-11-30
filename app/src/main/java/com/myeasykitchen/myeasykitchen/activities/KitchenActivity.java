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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.myeasykitchen.myeasykitchen.DatabaseClient;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.fragments.ItemMenuFragment;
import com.myeasykitchen.myeasykitchen.fragments.KitchenItemMenuFragment;
import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.models.ItemList;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;
import com.myeasykitchen.myeasykitchen.viewholder.ItemListViewHolder;
import com.myeasykitchen.myeasykitchen.viewholder.ItemViewHolder;
import com.myeasykitchen.myeasykitchen.viewholder.KitchenItemViewHolder;

import static com.myeasykitchen.myeasykitchen.activities.MainActivity.ANONYMOUS;

public class KitchenActivity extends AppCompatActivity {

    Context context = this;

    private DatabaseClient databaseClient;

    private FirebaseRecyclerAdapter<KitchenItem, KitchenItemViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    private RecyclerView mNavRecycler;
    private LinearLayoutManager mNavManager;
    private FirebaseRecyclerAdapter<ItemList, ItemListViewHolder> mNavAdapter;
    private TextView logoutButton;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        databaseClient = DatabaseClient.getInstance();
        mRecycler = (RecyclerView) findViewById(R.id.activity_kitchen_recycler_view);
        Button add_button = (Button)findViewById(R.id.add_kitchen_item);

        initNavBar();

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        mAdapter = new FirebaseRecyclerAdapter<KitchenItem, KitchenItemViewHolder>(KitchenItem.class, R.layout.kitchen_item_row,
                KitchenItemViewHolder.class, databaseClient.getList(getIntent().getStringExtra(getString(R.string.list_id)))) {
            @Override

            protected void populateViewHolder(KitchenItemViewHolder viewHolder, final KitchenItem model, int position) {
                final DatabaseReference itemRef = getRef(position);

                viewHolder.bindToItem(model, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        FragmentManager fm = getSupportFragmentManager();
                        ItemMenuFragment fragment = KitchenItemMenuFragment.newInstance(model,
                                getIntent().getStringExtra(getString(R.string.list_id)), itemRef.getKey());
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
                Intent myIntent = new Intent(context, AddKitchenItemActivity.class);
                myIntent.putExtra(getString(R.string.list_id), getIntent().getStringExtra(getString(R.string.list_id)));
                myIntent.putExtra(getString(R.string.item_id), "");
                context.startActivity(myIntent);
            }
        });
    }



    private void initNavBar() {
        mNavRecycler = (RecyclerView) findViewById(R.id.user_lists);

        mNavManager = new LinearLayoutManager(this);
        mNavManager.setReverseLayout(true);
        mNavManager.setStackFromEnd(true);
        mNavRecycler.setLayoutManager(mNavManager);
        mNavAdapter = new FirebaseRecyclerAdapter<ItemList, ItemListViewHolder>(ItemList.class, R.layout.user_list_item_row,
                ItemListViewHolder.class, databaseClient.getUserLists()) {
            @Override
            protected void populateViewHolder(ItemListViewHolder viewHolder, final ItemList model, int position) {
                final DatabaseReference itemRef = getRef(position);

                viewHolder.bindToItem(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent;
//                        if(model.getListType().equals("kitchen")) {
//                            myIntent = new Intent(context, KitchenActivity.class);
//                        } else {
                        myIntent = new Intent(context, GroceryActivity.class);
//                        }
                        myIntent.putExtra(getString(R.string.list_id), itemRef.getKey());
                        context.startActivity(myIntent);
                    }
                });
            }
        };

        mNavRecycler.setAdapter(mNavAdapter);

        logoutButton = (TextView) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                mUsername = ANONYMOUS;
                startActivity(new Intent(context, LoginActivity.class));
                finish();
                return;

            }
        });
    }

}
