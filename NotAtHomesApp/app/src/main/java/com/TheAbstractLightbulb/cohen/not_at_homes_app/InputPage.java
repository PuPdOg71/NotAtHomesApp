package com.example.cohen.not_at_homes_app;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class InputPage extends Activity {
    private EditText location;
    private EditText mapV;
    private EditText dateV;



    public InputPage() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page );
        Button button = (Button)findViewById(R.id.addButton);
        location = (EditText)findViewById(R.id.location);
        mapV = (EditText)findViewById(R.id.mapNoInput);
        dateV = (EditText)findViewById(R.id.dateBox);



    }

    public void toInputScreen2(View view) {
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(InputPage.this, InputPage2.class);
                intent2.putExtra("location", location.getText().toString());
                intent2.putExtra("map", mapV.getText().toString());
                intent2.putExtra("date", dateV.getText().toString());

                startActivity(intent2);
            }
        });
    }
}




