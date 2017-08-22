package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    DBHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;
    private String map;
    private String location;
    private String date;
    private String note;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        displayList();
    }

    public void displayList() {
        final Cursor cursor = dbHelper.getData();
        String from[] = new String[]{DBHelper.LOCATION};
        int to[] = new int[]{R.id.ListLayout1};
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.row_item, cursor, from, to, 0);


        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(simpleCursorAdapter);
        while (cursor.moveToNext()) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(cursor.getCount()>0) {
                    cursor.moveToPosition(i);
                    Intent intent = new Intent(MainActivity.this, EditData.class);
                    intent.putExtra("ref_id", cursor.getString(cursor.getColumnIndex(DBHelper.REF_ID)));
                    intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(DBHelper._id)));
                    intent.putExtra("Map", cursor.getString(cursor.getColumnIndex(DBHelper.MAP_NO)));
                    intent.putExtra("Location", cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION)));
                    intent.putExtra("Date", cursor.getString(cursor.getColumnIndex(DBHelper.DATE)));
                    intent.putExtra("Not_at_homes", cursor.getString(cursor.getColumnIndex(DBHelper.NOTATHOMES)));
                    startActivity(intent);
                }
            }
        });
    }

    }


    public void toInput(View view) {
        Button addbutton = (Button) findViewById(R.id.addButton);
        Intent intent = new Intent(MainActivity.this, InputPage.class);
        startActivity(intent);
    }
}