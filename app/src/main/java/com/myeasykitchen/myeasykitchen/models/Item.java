package com.myeasykitchen.myeasykitchen.models;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;


@IgnoreExtraProperties
public abstract class Item {

    private String name;
    private String key;
    private double amount;
    private String ownerName;

    public Item () {
        this("",0, "");
    }
    public Item(String name, double amount, String ownerName) {
        this.name = name;
        this.amount = amount;
        this.ownerName = ownerName;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

}
