package com.myeasykitchen.myeasykitchen.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myeasykitchen.myeasykitchen.DatabaseClient;
import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.R;

public class AddItemActivity extends ItemDetailsActivity {

    @Override
    public void save_item() {
        Item newItem = new Item(nameText.getText().toString());
        databaseClient.addItem(getIntent().getStringExtra("list key"), newItem);
        finish();
    }

    @Override
    public void setup() {
        String itemID = getIntent().getStringExtra("item id");
        if(!itemID.equals("")) {
            databaseClient.editItem(getIntent().getStringExtra("list key"),itemID, nameText);
        }
    }
}
