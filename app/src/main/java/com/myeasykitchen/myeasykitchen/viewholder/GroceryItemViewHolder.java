package com.myeasykitchen.myeasykitchen.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.Item;

/**
 * Created by Ali on 11/30/2016.
 */
public class GroceryItemViewHolder extends ItemViewHolder {
    private CheckBox checkBox;
    public GroceryItemViewHolder(View itemView) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.check_items);
    }

    public void bindToItem(Item item, View.OnLongClickListener listener, CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        super.bindToItem(item, listener);
        checkBox.setOnCheckedChangeListener(checkedChangeListener);
    }
}
