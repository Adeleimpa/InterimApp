package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

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

public class OffersList extends AppCompatActivity {

    public static final String TAG = SuggestedOffersAroundYou.class.getSimpleName();
    TextView goToMyProfile;
    Button apply;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;
    private LinearLayout linearLayout;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        apply = findViewById(R.id.applyOfferButton);
        goToMyProfile = findViewById(R.id.gotoaccountTextview);

        linearLayout = findViewById(R.id.listOffersLinearLayout);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("jobs");

        Intent intent = getIntent();
        location = intent.getStringExtra("city");

        retrieveDataFromFireStore();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent applyIntent = new Intent(OffersList.this, Apply.class);
                startActivity(applyIntent);
                finish();
            }
        });

        goToMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountIntent = new Intent(OffersList.this, MyAccount.class);
                startActivity(accountIntent);
                finish();
            }
        });
    }

    private void retrieveDataFromFireStore(){
        collectionReference.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Job> jobList = new ArrayList<>();
            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                Job job = documentSnapshot.toObject(Job.class);
                jobList.add(job);
            }

            for(int i=0; i<jobList.size(); i++) {
                if (jobList.get(i).city.equals(location)) {
                    CheckBox checkBox = new CheckBox(this);
                    TextView textView = new TextView(this);
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