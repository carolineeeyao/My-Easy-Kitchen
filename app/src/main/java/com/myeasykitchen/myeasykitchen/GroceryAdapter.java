package com.myeasykitchen.myeasykitchen;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jwei on 10/23/2016.
 */

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {
    private ArrayList<Item> mDataset;
    private ArrayList<Boolean> checks;
    private ItemList items;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public CheckBox cb;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.grocery_item_name);
            cb  = (CheckBox) v.findViewById(R.id.check_items);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GroceryAdapter(ItemList items) {
        this.items = items;
        mDataset = items.getList();
        checks = new ArrayList<Boolean>();
        for(int i = 0;i<mDataset.size();i++){
            checks.add(false);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GroceryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_item_row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getName());

        holder.cb.setChecked(checks.get(position));

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checks.set(position, holder.cb.isChecked());
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public boolean isChecked(int pos){
        return checks.get(pos);
    }

    public void update() {
        checks = new ArrayList<Boolean>();
        for(int i = 0;i<mDataset.size();i++){
            checks.add(false);
        }
    }
}