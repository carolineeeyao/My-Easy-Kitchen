package com.myeasykitchen.myeasykitchen.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.myeasykitchen.myeasykitchen.fragments.ItemMenuFragment;
import com.myeasykitchen.myeasykitchen.fragments.KitchenItemMenuFragment;
import com.myeasykitchen.myeasykitchen.models.ItemList;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;
import com.myeasykitchen.myeasykitchen.viewholder.ItemListViewHolder;
import com.myeasykitchen.myeasykitchen.viewholder.KitchenItemViewHolder;

import static com.myeasykitchen.myeasykitchen.activities.MainActivity.ANONYMOUS;

public class KitchenActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Context context = this;

    private DatabaseClient databaseClient;

    private FirebaseRecyclerAdapter<KitchenItem, KitchenItemViewHolder> mAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();


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

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                mAdapter.getRef(viewHolder.getAdapterPosition()).removeValue();
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d("GroceryActivity", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
