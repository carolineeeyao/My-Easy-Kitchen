package com.myeasykitchen.myeasykitchen.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myeasykitchen.myeasykitchen.DatabaseClient;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.activities.AddKitchenItemActivity;
import com.myeasykitchen.myeasykitchen.models.Item;

/**
 * Created by Ali on 11/20/2016.
 */

public abstract class ItemMenuFragment extends DialogFragment {

    protected TextView editItem;
    protected TextView removeItem;
    protected Item item;

    protected static final String ITEM_NAME = "itemName";
    protected static final String ITEM_ID = "itemID";
    protected static final String LIST_ID = "listID";

    public ItemMenuFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_menu, container);
        editItem = (TextView) view.findViewById(R.id.edit_item);
        removeItem = (TextView) view.findViewById(R.id.remove_item);
        getDialog().setTitle(getArguments().getString(ITEM_ID));

        setEditItemListener();

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseClient.getInstance().removeItem(getArguments().getString(LIST_ID),getArguments().getString(ITEM_ID));
                ItemMenuFragment.this.dismiss();
            }
        });

        return view;
    }

    protected abstract void setEditItemListener();
}
