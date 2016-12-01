package com.myeasykitchen.myeasykitchen.adapters;

import com.myeasykitchen.myeasykitchen.models.GroceryItem;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

/**
 * Created by Ali on 11/30/2016.
 */

public class KitchenItemAdapter extends KitchenItem {
    private GroceryItem groceryItem;

    public KitchenItemAdapter (GroceryItem groceryItem) {
        super(groceryItem.getName(), groceryItem.getAmount(), groceryItem.getOwnerName(), "");
        this.groceryItem = groceryItem;
    }
}
