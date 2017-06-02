package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by cohen on 2/06/2017.
 */

public class EditData extends Activity {
    private static final String TAG = "EditData";

    DBHelper dbhelper;

    private int selectedID;
    private String selectedMap;
    private String selectedName;
    private String selectedDate;
    private String selectedNotAtHomes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);
        final Button saveButton2 = (Button)findViewById(R.id.saveButton2);
        Button deleteButton1 = (Button)findViewById(R.id.deleteButton1);
        final EditText textNotAtHomes2 = (EditText)findViewById(R.id.textNotAtHomes2);
        final EditText mapNoDisplay2 = (EditText)findViewById(R.id.mapNoDisplay2);
        final EditText dateDisplay2 = (EditText)findViewById(R.id.dateDisplay2);
        final EditText ListName2 = (EditText)findViewById(R.id.ListName2);
        DBHelper DB = new DBHelper(this);

        Intent receivedIntent = getIntent();
        // ID
        selectedID = receivedIntent.getIntExtra("id",-1);
        // Map no
        selectedMap = receivedIntent.getStringExtra("map");
        // Name
        selectedName = receivedIntent.getStringExtra("name");
        // Date
        selectedDate = receivedIntent.getStringExtra("date");
        // Not at homes note file
        selectedNotAtHomes = receivedIntent.getStringExtra("notAtHomes");

        ListName2.setText(selectedName);
        mapNoDisplay2.setText(selectedMap);
        dateDisplay2.setText(selectedDate);
        textNotAtHomes2.setText(selectedNotAtHomes);

        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item1 = ListName2.getText().toString();
                String item2 = mapNoDisplay2.getText().toString();
                String item3 = dateDisplay2.getText().toString();
                String item4 = textNotAtHomes2.getText().toString();
                if (!item1.equals("")){
                    if (!item2.equals("")){
                        if (!item3.equals("")) {
                            if (!item4.equals("")) {
                                dbhelper.updateData(selectedID, item2, selectedMap, item1, selectedName, item3, selectedDate, item4, selectedNotAtHomes);
                            } else {
                                new AlertDialog.Builder(getBaseContext())
                                        .setTitle("emptyBoxes")
                                        .setMessage("Some boxes have been left empty, /n" +
                                                "are you sure you are done?")
                                        .setNegativeButton("Cancel", null)
                                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).create().show();
                            }

                        }

                        }
                    }

                }
            });

        deleteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.deleteData(selectedID, selectedMap, selectedName, selectedDate, selectedNotAtHomes);
                Intent intent = new Intent(EditData.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Note Deleted successfully", Toast.LENGTH_LONG).show();
            }
        });
        }

    }

