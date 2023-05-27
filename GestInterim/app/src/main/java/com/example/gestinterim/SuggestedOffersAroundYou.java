package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

        List<Job> jobsList = new ArrayList<>();
        Job job1 = new Job("Dog sitter", "You will be asked to take care of a dog.", "Bxl", "company", "url");
        Job job2 = new Job("Marius sitter", "You will be asked to take care of a Marius.", "Bxl", "company", "url");
        jobsList.add(job1);
        jobsList.add(job2);
        RecyclerView recyclerView = findViewById(R.id.listJobs);
        RecyclerView.Adapter<JobAdapter.ViewHolder> adapter = new JobAdapter(jobsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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