package com.myeasykitchen.myeasykitchen.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.activities.AddKitchenItemActivity;
import com.myeasykitchen.myeasykitchen.models.Item;

/**
 * Created by Ali on 11/30/2016.
 */
public class KitchenItemMenuFragment extends ItemMenuFragment {

    public static KitchenItemMenuFragment newInstance(Item item, String listID, String itemID) {

        Bundle args = new Bundle();

        KitchenItemMenuFragment fragment = new KitchenItemMenuFragment();
        args.putString(ITEM_NAME, item.getName());
        args.putString(ITEM_ID, itemID);
        args.putString(LIST_ID, listID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setEditItemListener() {
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddKitchenItemActivity.class);
                myIntent.putExtra(getString(R.string.list_id), getArguments().getString(LIST_ID));
                myIntent.putExtra(getString(R.string.item_id), getArguments().getString(ITEM_ID));
                getActivity().startActivity(myIntent);
                KitchenItemMenuFragment.this.dismiss();
            }
        });
    }
}
