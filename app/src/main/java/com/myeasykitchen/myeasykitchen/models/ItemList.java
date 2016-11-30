package com.myeasykitchen.myeasykitchen.models;

/**
 * Created by Ali on 10/19/2016.
 */

public class ItemList  {
    private String name;

    public ItemList() {
        this.name = "";
    }

    public ItemList(String name, String listType) {
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
