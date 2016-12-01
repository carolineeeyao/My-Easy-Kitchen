package com.myeasykitchen.myeasykitchen.models;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by Ali on 11/25/2016.
 */

public class KitchenItem extends Item {
    protected String expiration;
    protected int alarmID;
    public KitchenItem () {
        super();
        this.expiration = "";
        alarmID = 0;
    }
    public KitchenItem (String name, double amount, String ownerName, String expiration) {
        super(name, amount, ownerName);
        this.expiration = expiration;
        alarmID = 0;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public int getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(int alarmID) {
        this.alarmID = alarmID;
    }

    public Calendar getExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        String[] date = expiration.split("/");
        calendar.set(Calendar.MONTH,Integer.parseInt(date[0]));
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date[1]));
        calendar.set(Calendar.YEAR,Integer.parseInt(date[2]));
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 47);
        return calendar;
    }
}
