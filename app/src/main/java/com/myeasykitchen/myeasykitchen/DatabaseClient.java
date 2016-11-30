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
import com.myeasykitchen.myeasykitchen.activities.AddGroceryItemActivity;
import com.myeasykitchen.myeasykitchen.activities.AddKitchenItemActivity;
import com.myeasykitchen.myeasykitchen.models.GroceryItem;
import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

import java.util.Calendar;
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

    public void setKitchenItem(String listID, String itemID, KitchenItem item) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(itemID).setValue(item);
    }

    public void setGroceryItem(String listID, String itemID, GroceryItem item) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(itemID).setValue(item);
    }

    public void removeItem(String listID, String itemKey) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(itemKey).removeValue();
    }

    public void displayGroceryItem(String listID, final String itemKey, final AddGroceryItemActivity activity) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GroceryItem item = dataSnapshot.getValue(GroceryItem.class);
                EditText nameText = (EditText)  activity.findViewById(R.id.name_text);
                EditText quantityText = (EditText)  activity.findViewById(R.id.quantity_text);
                nameText.setText(item.getName());
                quantityText.setText(Double.toString(item.getAmount()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void displayKitchenItem(String listID, final String itemKey, final AddKitchenItemActivity activity) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(LIST_CHILD).child(listID);
        mListRef.child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                KitchenItem item = dataSnapshot.getValue(KitchenItem.class);
                EditText nameText = (EditText)  activity.findViewById(R.id.name_text);
                EditText quantityText = (EditText)  activity.findViewById(R.id.quantity_text);
                EditText dateText = (EditText) activity.findViewById(R.id.date_text);
                nameText.setText(item.getName());
                quantityText.setText(Double.toString(item.getAmount()));
                if(item.getCalendar().after(Calendar.getInstance())) {
                    String date = item.getCalendar().get(Calendar.MONTH) +
                            "/" + item.getCalendar().get(Calendar.DAY_OF_MONTH) +
                            "/" + item.getCalendar().get(Calendar.YEAR);
                    dateText.setText(date);
                }
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
