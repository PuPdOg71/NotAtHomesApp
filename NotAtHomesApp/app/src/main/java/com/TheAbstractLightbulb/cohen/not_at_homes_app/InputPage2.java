package com.example.cohen.not_at_homes_app;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;



public class InputPage2 extends Activity  {

    private EditText message;
    private TextView[] pairs;
    int num_match;
    private String location;


    public TextView[] getPairs() {
        return pairs;
    }

    public void setPairs(TextView[] pairs) {
        this.pairs = pairs;
    }

    public int getNum_match() {
        return num_match;
    }

    public void setNum_match(int num_match) {
        this.num_match = num_match;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page2);
        message = (EditText) findViewById(R.id.textNotAtHomes);
        TextView listName = (TextView)findViewById(R.id.ListName);
        TextView mapNoDisplay = (TextView)findViewById(R.id.mapNoDisplay);
        TextView dateDisplay = (TextView)findViewById(R.id.dateDisplay);

        Intent intent2 = this.getIntent();

        location = intent2.getStringExtra("location");
        String mapV = intent2.getStringExtra("map");
        String dateV = intent2.getStringExtra("date");

        listName.setText(String.valueOf("Area: " + location));
        mapNoDisplay.setText(String.valueOf("Map no: " + mapV));
        dateDisplay.setText(String.valueOf("Date: " + dateV));


    }

    public void toSaveField(View v) {
        Button saveButton1 = (Button) findViewById(R.id.saveButton1);
        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });


        String message1 = message.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("notathomesapp", MODE_WORLD_READABLE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            try {
                osw.write(message1);
                osw.flush();
                osw.close();
                Toast.makeText(getBaseContext(), "Note: " + location + " was saved successfully", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent2 = new Intent(InputPage2.this, MainActivity.class);
        startActivity(intent2);
    }

    public void linearLayout(int num_match) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView[] pairs = new TextView[num_match + 1];
        for (int l = 1; l <= num_match; l++) {
            pairs[1].setTextSize(15);
            pairs[1].setLayoutParams(layoutParams);
            pairs[1].setId(l);
            pairs[1].setText(location);
            linearLayout.addView(pairs[1]);

        }
    }

}

