package com.myeasykitchen.myeasykitchen.activities;

import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddKitchenItemActivity extends ItemDetailsActivity {
    private EditText expirationText;


    @Override
    public void save_item() {
        try {
            String name = nameText.getText().toString();
            double amount = Double.parseDouble(quantityText.getText().toString());
            String expiration = expirationText.getText().toString().trim();
            String[] date = expiration.split("/");
            if(date.length != 3) {
                expiration = "";
            }
            String ownerName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
            KitchenItem newItem = new KitchenItem(name, amount, ownerName, expiration);
            String itemID = getIntent().getStringExtra(getString(R.string.item_id));
            if(!itemID.equals("")) {
                databaseClient.setKitchenItem(getIntent().getStringExtra(getString(R.string.list_id)), itemID, newItem);
            } else {
                databaseClient.addItem(getIntent().getStringExtra(getString(R.string.list_id)), newItem);
            }
            Toast.makeText(this,getString(R.string.add_item_success),Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,getString(R.string.add_item_fail),Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void setup() {
        setContentView(R.layout.activity_add_kitchen_item);
        expirationText = (EditText) findViewById(R.id.date_text);
        String itemID = getIntent().getStringExtra(getString(R.string.item_id));
        if(!itemID.equals("")) {
            databaseClient.displayKitchenItem(getIntent().getStringExtra(getString(R.string.list_id)),itemID, this);
        }
    }
}
