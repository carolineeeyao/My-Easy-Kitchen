package com.myeasykitchen.myeasykitchen.activities;

import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

import java.util.Calendar;

public class AddKitchenItemActivity extends AddItemActivity {
    private EditText expirationText;


    @Override
    public void save_item() {
        try {
            KitchenItem newItem = getKitchenItem();
            String itemID = getIntent().getStringExtra(getString(R.string.item_id));
            // ****** if item exists ******o
            if(!itemID.equals("")) {
                databaseClient.setKitchenItem(getIntent().getStringExtra(getString(R.string.list_id)), itemID, newItem);
            } else {
                databaseClient.addItem(getIntent().getStringExtra(getString(R.string.list_id)), newItem);
            }
            Toast.makeText(this,getString(R.string.add_item_success),Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this,getString(R.string.add_item_fail),Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    public KitchenItem getKitchenItem() throws Exception {
        String name = nameText.getText().toString().trim();
        double amount = Double.parseDouble(quantityText.getText().toString());
        String expiration = expirationText.getText().toString().trim();
        if(!expiration.equals("") && !expiration.matches("^(((0?[1-9]|1[012])/(0?[1-9]|1\\d|2[0-8])|(0?[13456789]|1[012])/(29|30)|(0?[13578]|1[02])/31)/(19|[2-9]\\d)\\d{2}|0?2/29/((19|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|(([2468][048]|[3579][26])00)))$"))
            throw new Exception("Invalid Date");
        if(name.equals(""))
            throw new Exception("Empty Name Field");
        String ownerName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        KitchenItem newItem = new KitchenItem(name, amount, ownerName, expiration);
        if(!newItem.getExpiration().equals("")) {
            Calendar c = newItem.getExpirationDate();
            c.add(Calendar.DAY_OF_MONTH, 1);
            if (c.before(Calendar.getInstance()))
                throw new Exception();
        }
        return newItem;
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
