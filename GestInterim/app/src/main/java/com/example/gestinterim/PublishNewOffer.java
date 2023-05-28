package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PublishNewOffer extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    EditText titre, entreprise, ville, description, url;
    Button publish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_new_offer);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        titre = findViewById(R.id.titleEdit);
        entreprise = findViewById(R.id.companyEdit);
        ville = findViewById(R.id.cityEdit);
        description = findViewById(R.id.descriptionEdit);
        url = findViewById(R.id.urlEdit);
        publish = findViewById(R.id.publishButton);

        firebaseFirestore = FirebaseFirestore.getInstance();

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titre.getText().toString();
                String company = entreprise.getText().toString();
                String city = ville.getText().toString();
                String desc = description.getText().toString();
                String URL = url.getText().toString();
                Job job = new Job(title, desc, city, company, URL);

                CollectionReference jobCollection = firebaseFirestore.collection("jobs");
                jobCollection.add(job).addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(), "Your advert has been published successfully!", Toast.LENGTH_LONG).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), "Your advert couldn't be posted!", Toast.LENGTH_LONG).show();
                });

                Intent intent = new Intent(PublishNewOffer.this, Offers.class);
                Bundle bundle = new Bundle();
                bundle.putString("city", city);
                bundle.putString("url", URL);
                bundle.putString("company", company);
                bundle.putString("description", desc);
                bundle.putString("title", title);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}