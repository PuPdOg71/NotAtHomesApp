package com.TheAbstractLightbulb.cohen.not_at_homes_app;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class InputPage2 extends Activity {
    private String location;
    private String mapV;
    private String dateV;
    private EditText not_at_homes;
    DBHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page2);
        TextView listName = (TextView) findViewById(R.id.ListName);
        TextView mapNoDisplay = (TextView) findViewById(R.id.mapNoDisplay);
        TextView dateDisplay = (TextView) findViewById(R.id.dateDisplay);
        not_at_homes = (EditText)findViewById(R.id.textNotAtHomes);


        dbHelper = new DBHelper(this);

        Intent intent2 = this.getIntent();

        location = intent2.getStringExtra("location");
        mapV = intent2.getStringExtra("map");
        dateV = intent2.getStringExtra("date");


        listName.setText(String.valueOf("Area: " + location));
        mapNoDisplay.setText(String.valueOf("Map no: " + mapV));
        dateDisplay.setText(String.valueOf("Date: " + dateV));






    }


    public void toSaveField(View v) {
        Button saveButton1 = (Button) findViewById(R.id.saveButton1);
        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insertData(mapV, location, dateV, not_at_homes.getText().toString());
                Intent intent = new Intent(InputPage2.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Note: " + location + " was saved successfully", Toast.LENGTH_LONG).show();


            }


        });


    }
}

