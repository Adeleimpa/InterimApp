package com.example.gestinterim;

import static com.example.gestinterim.SignUpCandidate.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Offers extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = Offers.class.getSimpleName();
    ImageView mInfos, mPreferences, mOffers, mApplications, mMessages;
    TextView goToListInterims;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;
    private LinearLayout linearLayout;
    private FirebaseAuth mAuth;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        linearLayout = findViewById(R.id.linearLayout);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("jobs");

        mInfos = findViewById(R.id.informationImage);
        mPreferences = findViewById(R.id.preferencesImage);
        mOffers = findViewById(R.id.offersImage);
        mApplications = findViewById(R.id.applicationsImage);
        mMessages = findViewById(R.id.messagesImage);

        goToListInterims = findViewById(R.id.GoToListInterims);
        goToListInterims.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(user.getEmail());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

          @Override
          public void onSuccess(DocumentSnapshot documentSnapshot) {
              User user_data = documentSnapshot.toObject(User.class);
              if(user_data.getType().equals("employer")){
                  Button new_advert = new Button(Offers.this);
                  new_advert.setText("Add new offer");
                  new_advert.setBackgroundColor(getResources().getColor(R.color.flash_purple));
                  new_advert.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          Intent intent = new Intent(Offers.this, PublishNewOffer.class);
                          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                          startActivity(intent);

                      }
                  });
                  linearLayout.addView(new_advert);

                  Bundle receivedBundle = getIntent().getExtras();
                  if(receivedBundle != null){
                      String titre = receivedBundle.getString("title");
                      TextView textView = new TextView(Offers.this);
                      textView.setText("Titre :" + receivedBundle.getString("title") + "\n"+
                                        "Entreprise :" + receivedBundle.get("company") + "\n" +
                                        "Ville :" + receivedBundle.getString("city") + "\n" +
                                        "Description :" + receivedBundle.getString("description") + "\n" +
                                        "URL :" + receivedBundle.getString("url") + "\n");
                      textView.setTextSize(14);
                      textView.setTextColor(getResources().getColor(android.R.color.black));
                      linearLayout.addView(textView);
                  }

              }
          }
        });

        mPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Offers.this, Preferences.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        mMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Offers.this, Messages.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        mApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Offers.this, Applications.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        mOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Offers.this, Offers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        mInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Offers.this, MyAccount.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == goToListInterims) {
            Intent intent = new Intent(Offers.this, SuggestedOffersAroundYou.class);
            startActivity(intent);
            finish();
        }
    }


}
