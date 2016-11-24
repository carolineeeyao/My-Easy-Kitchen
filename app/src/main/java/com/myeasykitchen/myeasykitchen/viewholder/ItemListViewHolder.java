package com.myeasykitchen.myeasykitchen.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.myeasykitchen.myeasykitchen.models.ItemList;
import com.myeasykitchen.myeasykitchen.R;

/**
 * Created by Ali on 11/8/2016.
 */
public class ItemListViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    public ItemListViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.list_name);
    }

    public void bindToItem(ItemList list, View.OnClickListener listener) {
        name.setText(list.getName());
        itemView.setOnClickListener(listener);
    }
}
