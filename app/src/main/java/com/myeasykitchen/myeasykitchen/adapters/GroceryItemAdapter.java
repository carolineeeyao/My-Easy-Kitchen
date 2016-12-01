package com.myeasykitchen.myeasykitchen.adapters;

import com.myeasykitchen.myeasykitchen.models.GroceryItem;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

/**
 * Created by Ali on 11/30/2016.
 */

public class GroceryItemAdapter extends GroceryItem {
    private KitchenItem kitchenItem;
    public GroceryItemAdapter(KitchenItem kitchenItem) {
        super(kitchenItem.getName(), kitchenItem.getAmount(), kitchenItem.getOwnerName());
        this.kitchenItem = kitchenItem;
    }
}
