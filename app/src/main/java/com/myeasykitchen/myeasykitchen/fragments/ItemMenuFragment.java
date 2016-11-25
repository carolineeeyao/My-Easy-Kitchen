package com.myeasykitchen.myeasykitchen.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myeasykitchen.myeasykitchen.DatabaseClient;
import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.activities.AddItemActivity;
import com.myeasykitchen.myeasykitchen.models.Item;

/**
 * Created by Ali on 11/20/2016.
 */

public class ItemMenuFragment extends DialogFragment {

    private TextView editItem;
    private TextView removeItem;
    private Item item;

    public ItemMenuFragment () {}

    public static ItemMenuFragment newInstance(Item item, String listID, String itemKey) {

        Bundle args = new Bundle();

        ItemMenuFragment fragment = new ItemMenuFragment();
        args.putString("itemName", item.getName());
        args.putString("itemKey", itemKey);
        args.putString("listID", listID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_menu, container);
        editItem = (TextView) view.findViewById(R.id.edit_item);
        removeItem = (TextView) view.findViewById(R.id.remove_item);
        getDialog().setTitle(getArguments().getString("itemName"));

        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddItemActivity.class);
                myIntent.putExtra("list key", getArguments().getString("listID"));
                myIntent.putExtra("item id", getArguments().getString("itemKey"));
                getActivity().startActivity(myIntent);
                ItemMenuFragment.this.dismiss();
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hi", getArguments().getString("listID") + " " + getArguments().getString("itemKey"));
                DatabaseClient.getInstance().removeItem(getArguments().getString("listID"),getArguments().getString("itemKey"));
                ItemMenuFragment.this.dismiss();
            }
        });

        return view;
    }
}
