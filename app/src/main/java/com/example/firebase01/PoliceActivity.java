package com.example.firebase01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PoliceActivity extends AppCompatActivity {

    Button btnStatus, btnShowReport, btnAssigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);

        btnShowReport = (Button) findViewById(R.id.btnShowReport);
        btnAssigned = (Button) findViewById(R.id.btnAssigned);
        btnStatus = (Button) findViewById(R.id.btnStatus);

        btnShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(PoliceActivity.this,showCrimes.class);
                startActivity(I);
            }
        });
    }
}
