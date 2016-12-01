package com.myeasykitchen.myeasykitchen.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.myeasykitchen.myeasykitchen.DatabaseClient;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.fragments.GroceryItemMenuFragment;
import com.myeasykitchen.myeasykitchen.fragments.ItemMenuFragment;
import com.myeasykitchen.myeasykitchen.models.GroceryItem;
import com.myeasykitchen.myeasykitchen.models.ItemList;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;
import com.myeasykitchen.myeasykitchen.viewholder.GroceryItemViewHolder;
import com.myeasykitchen.myeasykitchen.viewholder.ItemListViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.myeasykitchen.myeasykitchen.activities.MainActivity.ANONYMOUS;

public class GroceryActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Context context = this;

    private DatabaseClient databaseClient;

    private FirebaseRecyclerAdapter<GroceryItem, GroceryItemViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    private RecyclerView mNavKitchenRecycler;
    private LinearLayoutManager mNavKitchenManager;
    private FirebaseRecyclerAdapter<ItemList, ItemListViewHolder> mNavKitchenAdapter;
    private RecyclerView mNavGroceryRecycler;
    private LinearLayoutManager mNavGroceryManager;
    private FirebaseRecyclerAdapter<ItemList, ItemListViewHolder> mNavGroceryAdapter;
    private TextView logoutButton;
    private String mUsername;
    private GoogleApiClient mGoogleApiClient;

    private Map<Integer,CompoundButton> checked;
    private boolean removing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        databaseClient = DatabaseClient.getInstance();
        mRecycler = (RecyclerView) findViewById(R.id.activity_grocery_recycler_view);
        Button add_button = (Button) findViewById(R.id.add_grocery_item);
        Button remove_all = (Button) findViewById(R.id.remove_all);

        initNavBar();

        checked = new HashMap<>();

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        mAdapter = new FirebaseRecyclerAdapter<GroceryItem, GroceryItemViewHolder>(GroceryItem.class, R.layout.grocery_item_row,
                GroceryItemViewHolder.class, databaseClient.getList(getIntent().getStringExtra(getString(R.string.list_id)))) {
            @Override
            protected void populateViewHolder(GroceryItemViewHolder viewHolder, final GroceryItem model, final int position) {
                final DatabaseReference itemRef = getRef(position);

                viewHolder.bindToItem(model, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        FragmentManager fm = getSupportFragmentManager();
                        ItemMenuFragment fragment = GroceryItemMenuFragment.newInstance(model,
                                getIntent().getStringExtra(getString(R.string.list_id)), itemRef.getKey());
                        fragment.show(fm, "fragment_item_menu");

                        return false;
                    }
                }, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(!removing) {
                            if (isChecked)
                                checked.put(position, buttonView);
                            else
                                checked.remove(position);
                        }
                    }
                });
            }
        };

        mRecycler.setAdapter(mAdapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, AddGroceryItemActivity.class);
                myIntent.putExtra(getString(R.string.list_id), getIntent().getStringExtra(getString(R.string.list_id)));
                myIntent.putExtra(getString(R.string.item_id), "");
                context.startActivity(myIntent);
            }
        });

        remove_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removing = true;
                for(int i: checked.keySet()) {
                    checked.get(i).setChecked(false);
                    databaseClient.exportToKitchen(mAdapter.getRef(i));
                }
                removing = false;
                mAdapter.notifyDataSetChanged();
                checked.clear();
                Toast.makeText(context, "Items Moved to Kitchen List",Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                databaseClient.exportToKitchen(mAdapter.getRef(viewHolder.getAdapterPosition()));
                Toast.makeText(context, "Item Moved to Kitchen List",Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecycler);

    }




    private void initNavBar() {
        mNavKitchenRecycler = (RecyclerView) findViewById(R.id.kitchen_lists);

        mNavKitchenManager = new LinearLayoutManager(this);
        mNavKitchenManager.setReverseLayout(true);
        mNavKitchenManager.setStackFromEnd(true);
        mNavKitchenRecycler.setLayoutManager(mNavKitchenManager);
        mNavKitchenAdapter = new FirebaseRecyclerAdapter<ItemList, ItemListViewHolder>(ItemList.class, R.layout.user_list_item_row,
                ItemListViewHolder.class, databaseClient.getUserKitchenLists()) {
            @Override
            protected void populateViewHolder(ItemListViewHolder viewHolder, final ItemList model, int position) {
                final DatabaseReference itemRef = getRef(position);

                viewHolder.bindToItem(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent;
                        myIntent = new Intent(context, KitchenActivity.class);
                        myIntent.putExtra(getString(R.string.list_id), itemRef.getKey());
                        context.startActivity(myIntent);
                        finish();
                    }
                });
            }
        };

        mNavKitchenRecycler.setAdapter(mNavKitchenAdapter);

        mNavGroceryRecycler = (RecyclerView) findViewById(R.id.grocery_lists);

        mNavGroceryManager = new LinearLayoutManager(this);
        mNavGroceryManager.setReverseLayout(true);
        mNavGroceryManager.setStackFromEnd(true);
        mNavGroceryRecycler.setLayoutManager(mNavGroceryManager);
        mNavGroceryAdapter = new FirebaseRecyclerAdapter<ItemList, ItemListViewHolder>(ItemList.class, R.layout.user_list_item_row,
                ItemListViewHolder.class, databaseClient.getUserGroceryLists()) {
            @Override
            protected void populateViewHolder(ItemListViewHolder viewHolder, final ItemList model, int position) {
                final DatabaseReference itemRef = getRef(position);

                viewHolder.bindToItem(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent;
                        myIntent = new Intent(context, GroceryActivity.class);
                        myIntent.putExtra(getString(R.string.list_id), itemRef.getKey());
                        context.startActivity(myIntent);
                        finish();
                    }
                });
            }
        };

        mNavGroceryRecycler.setAdapter(mNavGroceryAdapter);

        logoutButton = (TextView) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mUsername = ANONYMOUS;
                startActivity(new Intent(context, LoginActivity.class));
                finish();
                return;

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_grocery_drawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d("GroceryActivity", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}