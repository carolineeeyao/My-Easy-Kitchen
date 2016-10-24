package com.myeasykitchen.myeasykitchen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class KitchenAdapter extends ArrayAdapter<Item> {

    Context context;
    int layoutResourceId;
    ArrayList<Item> data;
    ItemList list;

    public KitchenAdapter(Context context, int layoutResourceId, ArrayList<Item> data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    public KitchenAdapter(Context context, int layoutResourceId, ItemList list)
    {
        super(context, layoutResourceId, list.getList());
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.list = list;
        this.data = list.getList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        ItemHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ItemHolder();
            holder.name = (TextView)row.findViewById(R.id.name);

            row.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)row.getTag();
        }

        Item item = data.get(position);
        holder.name.setText(item.getName());

        return row;
    }

    static class ItemHolder
    {
        TextView name;
    }
}
