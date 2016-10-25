package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    public static final String ANONYMOUS = "anonymous";

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;

    private String mUsername;
    private String mPhotoUrl;
    private SharedPreferences mSharedPreferences;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        // Set default username is anonymous.
//        mUsername = ANONYMOUS;
//
//        // Initialize Firebase Auth
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mFirebaseUser = mFirebaseAuth.getCurrentUser();
//        if (mFirebaseUser == null) {
//            // Not signed in, launch the Sign In activity
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//            return;
//        } else {
//            mUsername = mFirebaseUser.getDisplayName();
//            if (mFirebaseUser.getPhotoUrl() != null) {
//                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
//            }
//        }

        //populate a little of kitchen_data
        StaticData.kitchen_data.add(new Item("bread", "1"));
        StaticData.kitchen_data.add(new Item("eggs", "1"));
        StaticData.kitchen_data.add(new Item("milk", "1"));
        StaticData.kitchen_data.add(new Item("cheese", "1"));
        StaticData.kitchen_data.add(new Item("cereal", "1"));
        StaticData.kitchen_data.add(new Item("popcorn", "1"));
        StaticData.kitchen_data.add(new Item("cookies", "1"));
        StaticData.kitchen_data.add(new Item("cake", "1"));
        StaticData.kitchen_data.add(new Item("candy", "1"));

        //populate a little of kitchen_data
        StaticData.grocery_data.add(new Item("bread", "1"));
        StaticData.grocery_data.add(new Item("eggs", "1"));
        StaticData.grocery_data.add(new Item("milk", "1"));
        StaticData.grocery_data.add(new Item("cheese", "1"));
        StaticData.grocery_data.add(new Item("cereal", "1"));
        StaticData.grocery_data.add(new Item("popcorn", "1"));
        StaticData.grocery_data.add(new Item("cookies", "1"));
        StaticData.grocery_data.add(new Item("cake", "1"));
        StaticData.grocery_data.add(new Item("candy", "1"));

        StaticData.kitchen_list.setList(StaticData.kitchen_data);
        StaticData.grocery_list.setList(StaticData.grocery_data);

        Button kitchen_button = (Button)findViewById(R.id.kitchen_button);
        Button grocery_button = (Button)findViewById(R.id.grocery_button);

        kitchen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, KitchenActivity.class);
                context.startActivity(myIntent);
            }
        });

        grocery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, GroceryActivity.class);
                context.startActivity(myIntent);
            }
        });


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.sign_out_menu:
//                mFirebaseAuth.signOut();
//                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
//                mUsername = ANONYMOUS;
//                startActivity(new Intent(this, LoginActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }
}
