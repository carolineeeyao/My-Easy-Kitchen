package com.myeasykitchen.myeasykitchen;


import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Item {

    private long timeToAdd = 3*24*60*60*1000;
    private String name = "";
    private String quantity = "";
    private String key = "";
    private Date expireDate = new Date();


    public Item(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
        this.expireDate = new Date(Long.MAX_VALUE);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public void setQuantity(String quantity)
    {
        this.quantity =  quantity;
    }

    public String getName()
    {
        return this.name;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getQuantity() {
        return quantity;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void noExpireDate() { this.expireDate = new Date(Long.MAX_VALUE); }

    // called every 24 hours
    public void checkExpiration() {
        if (expireDate.getTime() - (new Date()).getTime() < timeToAdd) {
//            notifyObservers();
        }
    }
}
