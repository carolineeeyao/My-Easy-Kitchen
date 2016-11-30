package com.myeasykitchen.myeasykitchen.models;

import java.util.Calendar;

/**
 * Created by Ali on 11/25/2016.
 */

public class KitchenItem extends Item {
    protected Calendar calendar;
    public KitchenItem () {
        super();
        calendar = null;
    }
    public KitchenItem (String name, double amount, String ownerName, Calendar calendar) {
        super(name, amount, ownerName);
        this.calendar = calendar;
    }
    public KitchenItem (String name, double amount, String ownerName) {
        super(name, amount, ownerName);
        calendar = Calendar.getInstance();
        calendar.clear();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
