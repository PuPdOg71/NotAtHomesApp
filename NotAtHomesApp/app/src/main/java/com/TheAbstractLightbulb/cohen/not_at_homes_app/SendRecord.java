package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by cohen on 4/06/2017.
 */

public class SendRecord extends Activity {
    EditText no;
    TextView msg;
    Button sendButton;
    String message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_record);
        no = (EditText)findViewById(R.id.phoneNumberText);
        msg = (TextView)findViewById(R.id.messageDisplay);
        sendButton = (Button)findViewById(R.id.sendButton);
        Intent receved = getIntent();

        message = receved.getStringExtra("Note");

        msg.setText(message);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = no.getText().toString();
                String message = msg.getText().toString();
                sendSms(number, message);
            }
        });
    }

    protected void sendSms(String number, String message){
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, null, null);
        Toast.makeText(getApplicationContext(), "Message sent to " + number, Toast.LENGTH_LONG).show();
    }
}














