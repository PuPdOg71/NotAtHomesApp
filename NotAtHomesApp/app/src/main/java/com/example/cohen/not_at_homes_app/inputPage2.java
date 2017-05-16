package com.example.cohen.not_at_homes_app;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class inputPage2 extends Activity {
    Button saveButton1, button76;
    EditText message;
    String Message;
    int data_block = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page2);
        message = (EditText) findViewById(R.id.textNotAtHomes);

    }

    public void toSaveField(View v) {
        saveButton1 = (Button) findViewById(R.id.saveButton1);
        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });


        Message = message.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("Text.txt", MODE_WORLD_READABLE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            try {
                osw.write(Message);
                osw.flush();
                osw.close();
                Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void toTest(View view){
        button76 = (Button)findViewById(R.id.button76);
        button76.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("Text.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    char[] data = new char[data_block];
                    String final_data = "";
                    int size;
                    try {
                        while((size = isr.read(data))>0){
                            String read_data = String.copyValueOf(data, 0, size);
                            final_data += read_data;
                            data = new char[data_block];
                        }
                        Toast.makeText(getBaseContext(), "Message : " + final_data, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

