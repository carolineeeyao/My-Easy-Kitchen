package com.myeasykitchen.myeasykitchen.adapters;

import com.myeasykitchen.myeasykitchen.models.GroceryItem;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ali on 12/4/2016.
 */
public class GroceryItemAdapterTest {
    private final KitchenItem kitchenItem = new KitchenItem("name", 5, "owner", "05/16/2015");

    @Test
    public void getName() throws Exception {
        GroceryItem groceryItem = new GroceryItemAdapter(kitchenItem);
        assertEquals(groceryItem.getName(), "name");
    }

    @Test
    public void getAmount() throws Exception {
        GroceryItem groceryItem = new GroceryItemAdapter(kitchenItem);
        assertEquals(groceryItem.getAmount(), 5.0, 0.05);
    }

    @Test
    public void getOwnerName() throws Exception {
        GroceryItem groceryItem = new GroceryItemAdapter(kitchenItem);
        assertEquals(groceryItem.getOwnerName(), "owner");
    }

}