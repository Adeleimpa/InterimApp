package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PublishNewOffer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_new_offer);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
    }
}