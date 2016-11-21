package com.myeasykitchen.myeasykitchen.viewholder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.R;

/**
 * Created by Ali on 11/5/2016.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    public ItemViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
    }

    public void bindToItem(Item item, View.OnLongClickListener listener) {
        name.setText(item.getName());
        itemView.setOnLongClickListener(listener);
    }
}
