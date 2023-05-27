package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuggestedOffersAroundYou extends AppCompatActivity implements View.OnClickListener{

    TextView goToMyProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_offers_around_you);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        goToMyProfile = findViewById(R.id.GoToMyProfile);
        goToMyProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == goToMyProfile) {
            Intent intent = new Intent(SuggestedOffersAroundYou.this, MyAccount.class);
            startActivity(intent);
            finish();
        }
    }
}