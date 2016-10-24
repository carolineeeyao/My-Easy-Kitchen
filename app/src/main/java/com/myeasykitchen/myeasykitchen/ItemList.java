package com.myeasykitchen.myeasykitchen;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Ali on 10/19/2016.
 */

public class ItemList  {
    private ArrayList<Item> gList;

    public ItemList() {
        gList = new ArrayList<>();
    }

    public ArrayList<Item> getList() {
        return gList;
    }

    public void setList(ArrayList<Item> gList) {
        this.gList = gList;
    }

}
