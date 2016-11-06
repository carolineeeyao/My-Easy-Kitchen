package com.myeasykitchen.myeasykitchen;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ali on 11/5/2016.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    public ItemViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
    }

    public void bindToItem(Item item) {
        name.setText(item.getName());
    }
}
