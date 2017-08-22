package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    private String selectedREF;
    private int refTRANSFER;
    private String selectedMap;
    private String selectedName;
    private String selectedDate;
    private String selectedNotAtHomes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);
        final Button saveButton2 = (Button) findViewById(R.id.saveButton2);
        Button deleteButton1 = (Button) findViewById(R.id.deleteButton1);
        final EditText textNotAtHomes2 = (EditText) findViewById(R.id.textNotAtHomes2);
        final EditText mapNoDisplay2 = (EditText) findViewById(R.id.mapNoDisplay2);
        final EditText dateDisplay2 = (EditText) findViewById(R.id.dateDisplay2);
        final EditText ListName2 = (EditText) findViewById(R.id.ListName2);
        final DBHelper DB = new DBHelper(this);


        Intent receivedIntent = getIntent();
        // id
        selectedID = receivedIntent.getIntExtra("id", 0);
        //ref_id
        selectedREF = receivedIntent.getStringExtra("ref_id");
        // Map no
        selectedMap = receivedIntent.getStringExtra("Map");
        // Name
        selectedName = receivedIntent.getStringExtra("Location");
        // Date
        selectedDate = receivedIntent.getStringExtra("Date");
        // Not at homes note file
        selectedNotAtHomes = receivedIntent.getStringExtra("Not_at_homes");

        ListName2.setText(selectedName);
        mapNoDisplay2.setText(selectedMap);
        dateDisplay2.setText(selectedDate);
        textNotAtHomes2.setText(selectedNotAtHomes);

        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapNoDisplay2 == null) {
                    Dialog();
                } else if (ListName2 == null) {
                    Dialog();
                } else if (dateDisplay2 == null) {
                    Dialog();
                } else if (textNotAtHomes2 == null) {
                    Dialog();
                } else {

                    DB.updateData(selectedID, mapNoDisplay2.getText().toString(), ListName2.getText().toString(), dateDisplay2.getText().toString(), textNotAtHomes2.getText().toString());
                    Intent intent = new Intent(EditData.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Changes where saved", Toast.LENGTH_LONG).show();
                }


            }

        });
    }

            public void Dialog() {

                AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext())
                        .setTitle("'Empty Boxes'")
                        .setMessage("Some boxes have been left empty. \n Are you sure you want to continue?")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbhelper.updateData(selectedID, selectedMap, selectedName, selectedDate, selectedNotAtHomes);
                                Intent intent = new Intent(EditData.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getBaseContext(), "Changes where saved", Toast.LENGTH_LONG).show();
                            }
                        });
            }

    public void toSend(View view) {
        Intent intent1 = new Intent(EditData.this, SendRecord.class);
        intent1.putExtra("notAtHomes", selectedNotAtHomes);
        startActivity(intent1);
    }

    public void toDeleteNote(View view) {

        DBHelper dbHelper = new DBHelper(getBaseContext());
        int deletedRows = dbHelper.deleteData(selectedID);
        if (deletedRows > 0){
            Toast.makeText(getBaseContext(), selectedName + " deleted successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
        }
        Intent intent5 = new Intent(EditData.this, MainActivity.class);
        startActivity(intent5);
    }
}

