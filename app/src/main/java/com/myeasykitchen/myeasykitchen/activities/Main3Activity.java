package com.myeasykitchen.myeasykitchen.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.myeasykitchen.myeasykitchen.R;

public class Main3Activity extends AppCompatActivity {
    Context context = this;
    public String[] mNavTitles = {"Kitchen List", "Grocery List"};
    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //mNavTitles = getResources().getStringArray(R.array.navigation_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main3);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mNavTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position, view);
            }
            private void selectItem(int position, View view) {
                switch(position){
                    case 0: startActivity(new Intent(context, KitchenActivity.class));
                    case 1: startActivity(new Intent(context, GroceryActivity.class));
                }

                // Highlight the selected item and close the drawer
                mDrawerList.setItemChecked(position, true);
                mDrawerLayout.closeDrawer(Main3Activity.this.mDrawerList);

            }
        });

    }
}
