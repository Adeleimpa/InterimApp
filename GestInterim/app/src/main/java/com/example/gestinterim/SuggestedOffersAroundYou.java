package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SuggestedOffersAroundYou extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = SuggestedOffersAroundYou.class.getSimpleName();
    TextView goToMyProfile;
    Button apply;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;
    private LinearLayout linearLayout;
    String location;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_offers_around_you);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        goToMyProfile = findViewById(R.id.GoToMyProfile);
        apply = findViewById(R.id.applybutton);
        goToMyProfile.setOnClickListener(this);

        linearLayout = findViewById(R.id.offersLinearLayout);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("jobs");

        Intent intent = getIntent();
        location = intent.getStringExtra("location");

        retrieveDataFromFireStore();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent applyIntent = new Intent(SuggestedOffersAroundYou.this, Apply.class);
                startActivity(applyIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == goToMyProfile) {
            Intent intent = new Intent(SuggestedOffersAroundYou.this, MyAccount.class);
            startActivity(intent);
            finish();
        }
    }

    private void retrieveDataFromFireStore(){
        collectionReference.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Job> jobList = new ArrayList<>();
            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                Job job = documentSnapshot.toObject(Job.class);
                jobList.add(job);
            }

            for(int i=0; i<jobList.size(); i++){
                if(jobList.get(i).city.matches(location)) {
                    TextView textView = new TextView(this);
                    CheckBox checkBox = new CheckBox(this);
                    textView.setText("Title :" + jobList.get(i).title +
                            "\n" + "Company :" + jobList.get(i).company +
                            "\n" + "City :" + jobList.get(i).city +
                            "\n" + "Description :" + jobList.get(i).description +
                            "\n" + "URL :" + jobList.get(i).url +
                            "\n");
                    textView.setTextSize(14);
                    textView.setTextColor(getResources().getColor(android.R.color.black));
                    linearLayout.addView(checkBox);
                    linearLayout.addView(textView);
                }
            }


        }).addOnFailureListener(e -> Log.e(TAG, "Erreur lors de la récupération des données depuis Firestore", e));

    }


}