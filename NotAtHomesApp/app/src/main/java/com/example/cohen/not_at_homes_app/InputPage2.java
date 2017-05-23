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

    private Button saveButton1;
    private EditText message;
    private String Message;
    private TextView[] pairs;
    int num_match;
    private TextView ListName;
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
        TextView txt1 = (TextView)findViewById(R.id.ListName);

        Intent intent2 = this.getIntent();

        location = intent2.getStringExtra("location");
        txt1.setText(String.valueOf(location));
        Toast.makeText(getBaseContext(), location, Toast.LENGTH_LONG).show();

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

    public void linearLayout(TextView[] pairs, int num_match) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        pairs = new TextView[num_match + 1];
        for (int l = 1; l <= num_match; l++) {
            pairs[1].setTextSize(15);
            pairs[1].setLayoutParams(layoutParams);
            pairs[1].setId(l);
            linearLayout.addView(pairs[1]);

        }
    }
}

