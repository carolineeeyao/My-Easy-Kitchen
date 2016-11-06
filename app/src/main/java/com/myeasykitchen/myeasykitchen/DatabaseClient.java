package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static final DatabaseClient instance = new DatabaseClient();

    private final String USER_CHILD = "users";
    private final String USER_LIST = "list users";
    private final String LIST_CHILD = "list items";

    private static final String TAG = "DatabaseClient";

    private DatabaseClient() {
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseClient getInstance() {
        return instance;
    }

    public void setUser(String userUID) {
        userListsReference = mFirebaseDatabaseReference.child(USER_CHILD).child(userUID);
        userListsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                if(!iterator.hasNext()) {
                    String newListID = mFirebaseDatabaseReference.child(USER_LIST).push().getKey();
                    userListsReference.child("lists").child(newListID).setValue(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void displayList(String listID, final ListView listView1, final Context context ) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Item> list = new ArrayList<>();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()) {
                    String value = iterator.next().getKey();
                    list.add(new Item(value,"0"));
//                    list.add(iterator.next().getValue(Item.class));
                }
                // TODO: change to firebase recycler adapter
                KitchenAdapter adapter = new KitchenAdapter(context, R.layout.listview_item_row, list);
                listView1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addItem(String listID, Item newItem) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        String key = mListRef.push().getKey();
        newItem.setKey(key);
        mListRef.child(key).setValue(newItem);
    }

    public void removeItem(String listID, Item item) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(item.getKey()).removeValue();
    }
}
