package com.myeasykitchen.myeasykitchen.models;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Ali on 12/4/2016.
 */
public class KitchenItemTest {
    private final KitchenItem kitchenItem = new KitchenItem("name", 5, "owner", "12/23/2016");
    @Test
    public void getName() throws Exception {
        assertEquals(kitchenItem.getName(), "name");
    }

    @Test
    public void getExpiration() throws Exception {
        assertEquals(kitchenItem.getExpiration(), "12/23/2016");
    }

    @Test
    public void getExpirationDate() throws Exception {
        Calendar c = kitchenItem.getExpirationDate();
        assertEquals(c.get(Calendar.MONTH), Calendar.DECEMBER);
        assertEquals(c.get(Calendar.DAY_OF_MONTH), 23);
        assertEquals(c.get(Calendar.YEAR), 2016);
    }

    @Test
    public void getAmount() throws Exception {
        assertEquals(kitchenItem.getAmount(), 5.0, 0.05);
    }

    @Test
    public void getOwnerName() throws Exception {
        assertEquals(kitchenItem.getOwnerName(), "owner");
    }
}