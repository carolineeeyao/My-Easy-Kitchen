package com.myeasykitchen.myeasykitchen.models;

/**
 * Created by Ali on 11/25/2016.
 */

public class KitchenItem extends Item {
    protected String expiration;
    public KitchenItem () {
        super();
        this.expiration = "";
    }
    public KitchenItem (String name, double amount, String ownerName, String expiration) {
        super(name, amount, ownerName);
        this.expiration = expiration;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
