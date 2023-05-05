package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signing_up extends AppCompatActivity {

    Button candidate, employer, agency, guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing_up);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        candidate = findViewById(R.id.candidatebutton);
        employer = findViewById(R.id.employerbutton);
        agency = findViewById(R.id.agencybutton);
        guest = findViewById(R.id.guestbutton);

        candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signing_up.this, SignUpCandidate.class);
                startActivity(i);
            }
        });

        employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signing_up.this, SignUpEmployer.class);
                startActivity(i);
            }
        });

        agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signing_up.this, SignUpAgency.class);
                startActivity(i);
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signing_up.this, SignUpCandidate.class);
                startActivity(i);
            }
        });
    }
}