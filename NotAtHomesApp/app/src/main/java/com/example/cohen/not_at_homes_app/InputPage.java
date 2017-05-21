package com.example.cohen.not_at_homes_app;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InputPage extends Activity {
    public String name;

    public InputPage() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page );
        Button button = (Button)findViewById(R.id.addButton);
    }


    public InputPage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String name(String name){
        this.name = name;
        EditText editText = (EditText)findViewById(R.id.location);
        String value = editText.getText().toString();
        return value;

    }

    public void toInputScreen2(View view) {
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(InputPage.this, InputPage2.class);
                startActivity(intent2);

            }
        });
    }


}

