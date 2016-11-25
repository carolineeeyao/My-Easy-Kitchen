package com.myeasykitchen.myeasykitchen;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.myeasykitchen.myeasykitchen.models.Item;

import java.util.Iterator;

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

    public void setUser(String userUID, final String username) {
        userListsReference = mFirebaseDatabaseReference.child(USER_CHILD).child(userUID);
        userListsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                if(!iterator.hasNext()) {
                    String newKitchenList = mFirebaseDatabaseReference.child(USER_LIST).push().getKey();
                    userListsReference.child("lists").child(newKitchenList).child("name").setValue("Kitchen List");
                    userListsReference.child("lists").child(newKitchenList).child("kitchenList").setValue("kitchen");

                    String newGroceryList = mFirebaseDatabaseReference.child(USER_LIST).push().getKey();
                    userListsReference.child("lists").child(newGroceryList).child("name").setValue("Grocery List");
                    userListsReference.child("lists").child(newGroceryList).child("kitchenList").setValue("grocery");

                    userListsReference.child("name").setValue(username);
                }
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

    public void removeItem(String listID, String itemKey) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(itemKey).removeValue();
    }

    public void editItem(String listID, final String itemKey, final EditText editText) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Item item = dataSnapshot.getValue(Item.class);
                editText.setText(item.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Query getList(String listID) {
        return mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
    }

    public Query getUserLists() {
        return userListsReference.child("lists");
    }
}
