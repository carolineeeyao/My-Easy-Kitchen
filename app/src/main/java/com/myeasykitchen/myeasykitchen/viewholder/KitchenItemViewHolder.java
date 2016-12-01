package com.myeasykitchen.myeasykitchen.viewholder;

import android.view.View;
import android.widget.TextView;

import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

/**
 * Created by Ali on 11/30/2016.
 */
public class KitchenItemViewHolder extends ItemViewHolder {
    private TextView expiration;
    public KitchenItemViewHolder(View itemView) {
        super(itemView);

        expiration = (TextView) itemView.findViewById(R.id.expiration);
    }

    public void bindToItem(KitchenItem item, View.OnLongClickListener listener) {
        super.bindToItem(item, listener);
        expiration.setText(item.getExpiration());
    }
}
