package com.myeasykitchen.myeasykitchen;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Ali on 10/19/2016.
 */

public class ItemList implements Observer {
    private ArrayList<Item> gList;

    public ItemList() {
        gList = new ArrayList<>();
    }

    public ArrayList<Item> getGroceryList() {
        return gList;
    }

    public void setGroceryList(ArrayList<Item> gList) {
        this.gList = gList;
    }

    @Override
    public void update(Observable observable, Object data) {
        gList.add((Item) observable);
    }
}
