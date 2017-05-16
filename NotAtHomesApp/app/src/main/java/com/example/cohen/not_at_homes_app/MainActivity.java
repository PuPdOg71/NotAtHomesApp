package com.example.cohen.not_at_homes_app;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.content.Intent;
import android.view.View;



public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
    }

    public void toInputScreen(View view){
        Button addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, inputPage.class);
                startActivity(intent1);
            }
        });

    }
}