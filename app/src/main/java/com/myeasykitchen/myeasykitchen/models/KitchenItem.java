package com.myeasykitchen.myeasykitchen.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ali on 11/25/2016.
 */

public class KitchenItem extends Item {
    protected Calendar calendar;
    public KitchenItem () {
        super();
        calendar = null;
    }
    public KitchenItem (String name, double amount, Calendar calendar) {
        super(name, amount);
        this.calendar = calendar;
    }
    public KitchenItem (String name, double amount) {
        super(name, amount);
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
