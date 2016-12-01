package com.myeasykitchen.myeasykitchen.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by Ali on 11/25/2016.
 */

@IgnoreExtraProperties
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

    @Exclude
    public Calendar getExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        String[] date = expiration.split("/");
        calendar.set(Calendar.MONTH,Integer.parseInt(date[0])-1);
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date[1]));
        calendar.set(Calendar.YEAR,Integer.parseInt(date[2]));
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 20);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }
}
