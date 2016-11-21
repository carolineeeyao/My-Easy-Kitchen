package com.myeasykitchen.myeasykitchen.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.Item;

/**
 * Created by Ali on 11/20/2016.
 */

public class ItemMenuFragment extends DialogFragment {

    private TextView editItem;
    private TextView removeItem;
    private Item item;

    public ItemMenuFragment () {}

    public static ItemMenuFragment newInstance(Item item) {

        Bundle args = new Bundle();

        ItemMenuFragment fragment = new ItemMenuFragment();
        args.putString("itemName", item.getName());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_menu, container);
        editItem = (TextView) view.findViewById(R.id.edit_item);
        removeItem = (TextView) view.findViewById(R.id.remove_item);
        getDialog().setTitle(getArguments().getString("itemName"));

        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
