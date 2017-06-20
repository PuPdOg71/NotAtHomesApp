package com.TheAbstractLightbulb.cohen.not_at_homes_app;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private DBHelper dbHelper;
    private NewListAdapter newListAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        populateListView();




    }


    public void toInputScreen(View view) {
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, InputPage.class);
                startActivity(intent1);
            }
        });
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in list view.");
        final Cursor data = dbHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(2));

        }
        ListView MainListView = (ListView)findViewById(R.id.MainListView);
        dbHelper = DBHelper.getInstance(getApplicationContext());
        Cursor cursor = dbHelper.getData();
        if (cursor != null){
            newListAdapter = new  NewListAdapter(getApplicationContext(), cursor, 0);
            MainListView.setAdapter(newListAdapter);
        MainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long ID = (int) adapterView.getItemIdAtPosition(0);
                String map = adapterView.getItemAtPosition(1).toString();
                String name = adapterView.getItemAtPosition(2).toString();
                String date = adapterView.getItemAtPosition(3).toString();
                String notAtHomes = adapterView.getItemAtPosition(4).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + notAtHomes);

                Cursor data = dbHelper.getItemID(map, name, date, notAtHomes);
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editDataIntent = new Intent(MainActivity.this, EditData.class);
                    editDataIntent.putExtra("id", itemID);
                    editDataIntent.putExtra("map", map);
                    editDataIntent.putExtra("name", name);
                    editDataIntent.putExtra("date", date);
                    editDataIntent.putExtra("notAtHomes", notAtHomes);
                    startActivity(editDataIntent);
//                } else {
//                    Toast.makeText(getBaseContext(), "No ID associated with that name", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}