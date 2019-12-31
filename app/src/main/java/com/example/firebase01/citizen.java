package com.example.firebase01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class citizen extends AppCompatActivity {

    Button btnReport, btnShowReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);

        btnReport = (Button) findViewById(R.id.btnReport);
        btnShowReport = (Button) findViewById(R.id.btnShowReport);

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(citizen.this,PostActivity.class);
                startActivity(I);
            }
        });
        btnShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(citizen.this,showCrimes.class);
                startActivity(I);
            }
        });
    }
}
