package com.serifyasargmail.cevsen;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Cevşen ve Dualar");
        Button btnCevsen=findViewById(R.id.btnCevsen);
        btnCevsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s = "cevsen";
                Intent i = new Intent(getApplicationContext(), CevsenActivity.class);
                i.putExtra("dua",s);
                startActivity(i);

            }
        });

        Button btnBedir=findViewById(R.id.btnBedir);
        btnBedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s = "abedir";
                Intent i = new Intent(getApplicationContext(), CevsenActivity.class);
                i.putExtra("dua",s);
                startActivity(i);

            }
        });


        Button btnYasin=findViewById(R.id.btnYasin);
        btnYasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s = "yasin";
                Intent i = new Intent(getApplicationContext(), CevsenActivity.class);
                i.putExtra("dua",s);
                startActivity(i);

            }
        });


        Button btnSabah=findViewById(R.id.btnSabah);
        btnSabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s = "sabah";
                Intent i = new Intent(getApplicationContext(), CevsenActivity.class);
                i.putExtra("dua",s);
                startActivity(i);

            }
        });


        Button btnAksam=findViewById(R.id.btnAksam);
        btnAksam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s = "aksam";
                Intent i = new Intent(getApplicationContext(), CevsenActivity.class);
                i.putExtra("dua",s);
                startActivity(i);

            }
        });

        Button btnFetih=findViewById(R.id.btnFetih);
        btnFetih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s = "fetih";
                Intent i = new Intent(getApplicationContext(), CevsenActivity.class);
                i.putExtra("dua",s);
                startActivity(i);

            }
        });


    }
}
