package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
    private final String USER_LIST = "list users";
    private final String LIST_CHILD = "list item";


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

        ItemList itemList = new ItemList();
//        mFirebaseDatabaseReference.addChildEventListener(itemList);
        return itemList;
    }

    /**
     * Stores lists of items in given list of given user
     * @param listID id of list to update
     * @param itemList updated lists
     */
    public void storeUserList(String listID, ItemList itemList) {

    }

    public void displayList(String listID, final ListView listView1, final Context context ) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child("list items").child(listID);
        mListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Item> list = new ArrayList<>();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()) {
                    String value = iterator.next().getKey();
                    list.add(new Item(value,"0"));
                }
                KitchenAdapter adapter = new KitchenAdapter(context, R.layout.listview_item_row, list);
                listView1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
