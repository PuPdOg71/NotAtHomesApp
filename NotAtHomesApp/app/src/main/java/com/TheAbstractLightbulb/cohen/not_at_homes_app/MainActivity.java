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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    DBHelper dbHelper;

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

    public void toDelete(View view) {
        Button deleteButton = (Button) findViewById(R.id.deleteButton1);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, EditData.class);
                startActivity(intent2);
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
        ListView listview = (ListView) findViewById(R.id.MainListView);
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String map = adapterView.getItemAtPosition(i).toString();
                String name = adapterView.getItemAtPosition(i).toString();
                String date = adapterView.getItemAtPosition(i).toString();
                String notAtHomes = adapterView.getItemAtPosition(i).toString();
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
                } else {
                    Toast.makeText(getBaseContext(), "No ID associated with that name", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}