package com.serifyasargmail.fixnfix;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Detay extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 11;
    String phoneNo = "";
    String message = "Your repair request has been confirmed. Our technician will contact you as soon as possible. [Fixnfix]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        TextView d1 = findViewById(R.id.d1);
        TextView d2 = findViewById(R.id.d2);
        TextView d3 = findViewById(R.id.d3);
        TextView d4 = findViewById(R.id.d4);
        TextView d5 = findViewById(R.id.d5);
        TextView d6 = findViewById(R.id.d6);
        TextView d7 = findViewById(R.id.d7);
        TextView d8 = findViewById(R.id.d8);
        TextView d9 = findViewById(R.id.d9);
        TextView d10 = findViewById(R.id.d10);
        TextView d11 = findViewById(R.id.d11);



        Bundle extras = getIntent().getExtras();
        Repair customer = (Repair) getIntent().getSerializableExtra("customer");

        d1.setText(customer.getName());
        d2.setText(customer.getPhone());
        d3.setText(customer.getEmail());
        d4.setText(customer.getAddress());
        d5.setText(customer.getCity());
        d6.setText(customer.getPostcode());
        d7.setText(customer.getDevice());
        d8.setText(customer.getRepair().replace("<br>", " "));
        d9.setText("£" + customer.getCost());
        d10.setText(customer.getType());
        d11.setText(customer.getMessage());

        phoneNo = customer.getPhone();
        phoneNo=phoneNo.trim();
        String fc= String.valueOf(phoneNo.charAt(0));
        if(fc!="0"){
            phoneNo="0"+phoneNo;
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    requestpermisson();
                } catch (Exception e) {
                    Toast.makeText(Detay.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    public void requestpermisson() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                // permission is already granted
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }


    }

}