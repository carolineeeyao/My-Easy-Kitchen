package com.myeasykitchen.myeasykitchen.viewholder;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.models.Item;
import com.myeasykitchen.myeasykitchen.models.KitchenItem;

import java.util.Calendar;

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
        if (!item.getExpiration().equals("")) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 2);
            if (item.getExpirationDate().before(c)) {
                itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
            }
        } else {
            itemView.setBackgroundColor(itemView.getResources().getColor(android.R.color.background_light));
        }
    }
}
