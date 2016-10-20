package com.myeasykitchen.myeasykitchen;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Ali on 10/19/2016.
 */

public class GroceryList implements Observer{
    private ArrayList<Item> gList;

    public GroceryList() {
        gList = new ArrayList<>();
    }


    @Override
    public void update(Observable observable, Object data) {

    }

    public ArrayList<Item> getGroceryList() {
        return gList;
    }

    public void setGroceryList(ArrayList<Item> gList) {
        this.gList = gList;
    }
}
