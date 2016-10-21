package com.myeasykitchen.myeasykitchen;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Ali on 10/21/2016.
 */

public class DatabaseClient {
    // Firebase instance variables
    private DatabaseReference mFirebaseDatabaseReference;
    private DatabaseReference userListsReference;

    private final String USER_CHILD = "users";
    private final String USER_LIST = "lists";
    private final String LIST_CHILD = "lists";
    private final String LIST_ITEMS = "items";


    public DatabaseClient(String userUID) {
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        userListsReference = mFirebaseDatabaseReference.child(USER_CHILD).child(userUID);
    }

    /**
     * Gets list of items in given list of given user
     * @param listID id of list requested
     * @return list of items
     */
    public ItemList getUserList(String listID) {
        List<Item> lists = new ArrayList<>();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Map<String,Object> post = (Map<String, Object>) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
//                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        return new ItemList();
    }

    /**
     * Stores lists of items in given list of given user
     * @param listID id of list to update
     * @param itemList updated lists
     */
    public void storeUserList(String listID, ItemList itemList) {

    }
}
