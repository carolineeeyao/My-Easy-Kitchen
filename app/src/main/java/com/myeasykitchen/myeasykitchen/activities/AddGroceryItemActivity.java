package com.myeasykitchen.myeasykitchen.activities;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.GroceryItem;

public class AddGroceryItemActivity extends ItemDetailsActivity {
    @Override
    public void save_item() {
        try {
            String name = nameText.getText().toString();
            double amount = Double.parseDouble(quantityText.getText().toString());
            String ownerName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            GroceryItem newItem = new GroceryItem(name,amount, ownerName);
            String itemID = getIntent().getStringExtra(getString(R.string.item_id));
            if(!itemID.equals("")) {
                databaseClient.setGroceryItem(getIntent().getStringExtra(getString(R.string.list_id)), itemID, newItem);
            } else {
                databaseClient.addItem(getIntent().getStringExtra(getString(R.string.list_id)), newItem);
            }
            Toast.makeText(this, getString(R.string.add_item_success), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(this, getString(R.string.add_item_fail), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void setup() {
        setContentView(R.layout.activity_add_grocery_item);
        String itemID = getIntent().getStringExtra(getString(R.string.item_id));
        if(!itemID.equals("")) {
            databaseClient.displayGroceryItem(getIntent().getStringExtra(getString(R.string.list_id)),itemID, this);
        }
    }
}
