package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by cohen on 4/06/2017.
 */

public class SendRecord extends Activity {

    Button sendButton;
    private String message;
    private EditText num;

    public SendRecord() {
    }

    @Override
    protected void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.send_record);

        Intent intent = getIntent();

        message = intent.getStringExtra("notAtHomes");



        if (ContextCompat.checkSelfPermission(SendRecord.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(SendRecord.this,
                    Manifest.permission.SEND_SMS)){
                ActivityCompat.requestPermissions(SendRecord.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);

            }else {
                ActivityCompat.requestPermissions(SendRecord.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }else {
            // do nothing
        }

        sendButton = (Button)findViewById(R.id.sendButton);
        num = (EditText)findViewById(R.id.phoneNumberText);


                sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = num.getText().toString();
                String sms = message;

                try {

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, sms, null, null);
                    Toast.makeText(SendRecord.this, "Sent!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(SendRecord.this, "Send Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  @NonNull String[] permissions,  @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(SendRecord.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}