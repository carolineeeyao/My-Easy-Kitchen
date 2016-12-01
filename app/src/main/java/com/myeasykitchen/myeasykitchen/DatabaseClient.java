package com.myeasykitchen.myeasykitchen;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.myeasykitchen.myeasykitchen.activities.AddGroceryItemActivity;
import com.myeasykitchen.myeasykitchen.activities.AddKitchenItemActivity;
import com.myeasykitchen.myeasykitchen.activities.KitchenActivity;
import com.myeasykitchen.myeasykitchen.adapters.GroceryItemAdapter;
import com.myeasykitchen.myeasykitchen.adapters.KitchenItemAdapter;
import com.myeasykitchen.myeasykitchen.models.GroceryItem;
import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;
import com.myeasykitchen.myeasykitchen.notifications.AlarmCreator;

import java.util.ArrayList;
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

    private final String USERS = "users";
    private final String ITEMS = "items";
    private final String NAME = "name";
    private final String KITCHEN_LISTS = "kitchen lists";
    private final String GROCERY_LISTS = "grocery lists";

    private static final String TAG = "DatabaseClient";
    private int alarmID;

    private DatabaseClient() {
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseClient getInstance() {
        return instance;
    }

    public void setUser(final String userUID, final String username, final Context context) {
        userListsReference = mFirebaseDatabaseReference.child(USERS).child(userUID);
        userListsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                if(!iterator.hasNext()) {
                    String newKitchenList = mFirebaseDatabaseReference.child(KITCHEN_LISTS).push().getKey();
                    userListsReference.child(KITCHEN_LISTS).child(newKitchenList).child(NAME).setValue("Kitchen List");
                    mFirebaseDatabaseReference.child(KITCHEN_LISTS).child(newKitchenList).child(NAME).setValue("Kitchen List");
                    mFirebaseDatabaseReference.child(KITCHEN_LISTS).child(newKitchenList).child(USERS).child(userUID).child(NAME).setValue(username);

                    String newGroceryList = mFirebaseDatabaseReference.child(GROCERY_LISTS).push().getKey();
                    userListsReference.child(GROCERY_LISTS).child(newGroceryList).child(NAME).setValue("Grocery List");
                    mFirebaseDatabaseReference.child(GROCERY_LISTS).child(newGroceryList).child(NAME).setValue("Grocery List");
                    mFirebaseDatabaseReference.child(GROCERY_LISTS).child(newGroceryList).child(USERS).child(userUID).child(NAME).setValue(username);

                    userListsReference.child(NAME).setValue(username);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        DatabaseReference userKitchenLists = userListsReference.child(KITCHEN_LISTS);
        userKitchenLists.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()) {
                    final String kList = iterator.next().getKey();
                    mFirebaseDatabaseReference.child(ITEMS).child(kList).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            KitchenItem item = dataSnapshot.getValue(KitchenItem.class);
                            item.setAlarmID(alarmID);
                            alarmID++;
                            dataSnapshot.getRef().setValue(item);

                            if(!item.getExpiration().equals(""))
                                AlarmCreator.create(context, item.getExpirationDate(),item.getAlarmID(), item.getName(), "This item is about to expire");
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            KitchenItem item = dataSnapshot.getValue(KitchenItem.class);
                            if(!item.getExpiration().equals(""))
                                AlarmCreator.create(context, item.getExpirationDate(),item.getAlarmID(), item.getName(), "This item is about to expire");

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void addItem(String listID, Item newItem) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(ITEMS).child(listID);
        String key = mListRef.push().getKey();
        newItem.setKey(key);
        mListRef.child(key).setValue(newItem);
    }

    public void setKitchenItem(String listID, String itemID, KitchenItem item) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(ITEMS).child(listID);
        mListRef.child(itemID).setValue(item);
    }

    public void setGroceryItem(String listID, String itemID, GroceryItem item) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(ITEMS).child(listID);
        mListRef.child(itemID).setValue(item);
    }

    public void removeItem(String listID, String itemKey) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(ITEMS).child(listID);
        mListRef.child(itemKey).removeValue();
    }

    public void displayGroceryItem(String listID, final String itemKey, final AddGroceryItemActivity activity) {
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(ITEMS).child(listID);
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
        DatabaseReference mListRef = mFirebaseDatabaseReference.child(ITEMS).child(listID);
        mListRef.child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                KitchenItem item = dataSnapshot.getValue(KitchenItem.class);
                EditText nameText = (EditText)  activity.findViewById(R.id.name_text);
                EditText quantityText = (EditText)  activity.findViewById(R.id.quantity_text);
                EditText dateText = (EditText) activity.findViewById(R.id.date_text);
                nameText.setText(item.getName());
                quantityText.setText(Double.toString(item.getAmount()));
                dateText.setText(item.getExpiration());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Query getList(String listID) {
        return mFirebaseDatabaseReference.child(ITEMS).child(listID);
    }

    public Query getUserKitchenLists() {
        return userListsReference.child(KITCHEN_LISTS);
    }

    public Query getUserGroceryLists() {
        return userListsReference.child(GROCERY_LISTS);
    }

    public void exportToGrocery(final DatabaseReference itemRef) {
        DatabaseReference userGroceryLists = userListsReference.child(GROCERY_LISTS);
        userGroceryLists.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                if(iterator.hasNext()) {
                    final String gList = iterator.next().getKey();
                    itemRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            KitchenItem item = dataSnapshot.getValue(KitchenItem.class);
                            addItem(gList, new GroceryItemAdapter(item));
                            itemRef.removeValue();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void exportToKitchen(final DatabaseReference itemRef) {
        DatabaseReference userKitchenLists = userListsReference.child(KITCHEN_LISTS);
        userKitchenLists.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                if(iterator.hasNext()) {
                    final String kList = iterator.next().getKey();
                    itemRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GroceryItem item = dataSnapshot.getValue(GroceryItem.class);
                            addItem(kList, new KitchenItemAdapter(item));
                            itemRef.removeValue();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
