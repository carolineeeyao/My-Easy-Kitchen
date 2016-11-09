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
    private String name;

    public ItemList() {
        this.name = "";
    }

    public ItemList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
