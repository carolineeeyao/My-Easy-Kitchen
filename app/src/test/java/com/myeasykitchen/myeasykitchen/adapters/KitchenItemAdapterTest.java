package com.myeasykitchen.myeasykitchen.adapters;

import com.myeasykitchen.myeasykitchen.models.GroceryItem;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Ali on 12/4/2016.
 */
public class KitchenItemAdapterTest {
    private final GroceryItem groceryItem = new GroceryItem("name", 5, "owner");

    @Test
    public void getName() throws Exception {
        KitchenItem kitchenItem = new KitchenItemAdapter(groceryItem);
        assertEquals(groceryItem.getName(), "name");
    }

    @Test
    public void getExpiration() throws Exception {
        KitchenItem kitchenItem = new KitchenItemAdapter(groceryItem);
        assertEquals(kitchenItem.getExpiration(), "");
    }

    @Test
    public void getAmount() throws Exception {
        KitchenItem kitchenItem = new KitchenItemAdapter(groceryItem);
        assertEquals(kitchenItem.getAmount(), 5.0, 0.05);
    }

    @Test
    public void getOwnerName() throws Exception {
        KitchenItem kitchenItem = new KitchenItemAdapter(groceryItem);
        assertEquals(kitchenItem.getOwnerName(), "owner");
    }
}