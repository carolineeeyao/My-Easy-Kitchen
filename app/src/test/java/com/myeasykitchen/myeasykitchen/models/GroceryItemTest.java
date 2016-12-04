package com.myeasykitchen.myeasykitchen.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ali on 12/4/2016.
 */
public class GroceryItemTest {
    private final GroceryItem groceryItem = new GroceryItem("name", 5, "owner");

    @Test
    public void getName() throws Exception {
        assertEquals(groceryItem.getName(), "name");
    }

    @Test
    public void getAmount() throws Exception {
        assertEquals(groceryItem.getAmount(), 5.0, 0.05);
    }

    @Test
    public void getOwnerName() throws Exception {
        assertEquals(groceryItem.getOwnerName(), "owner");
    }
}