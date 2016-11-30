package com.myeasykitchen.myeasykitchen.activities;

import android.widget.EditText;
import android.widget.Toast;

import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

import java.util.Calendar;

public class AddKitchenItemActivity extends ItemDetailsActivity {
    private EditText expirationText;


    @Override
    public void save_item() {
        try {
            String name = nameText.getText().toString();
            double amount = Double.parseDouble(quantityText.getText().toString());
            String expiration = expirationText.getText().toString().trim();
            String[] date = expiration.split("/");
            KitchenItem newItem;
            if(date.length == 3) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, Integer.parseInt(date[0]));
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[1]));
                calendar.set(Calendar.YEAR, Integer.parseInt(date[3]));
                calendar.set(Calendar.HOUR_OF_DAY, 11);
                calendar.set(Calendar.MINUTE, 0);
                newItem = new KitchenItem(name, amount, calendar);
            } else {
                newItem = new KitchenItem(name, amount);
            }
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
