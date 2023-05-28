package com.example.gestinterim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AllowLocationData extends AppCompatActivity {

    LocationListener androidLocationListener;
    TextView adress;
    String city;
    private FirebaseAuth mAuth;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_location_data);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Button continuer = findViewById(R.id.continueButton);
        adress = findViewById(R.id.viewAdress);
        int LOCATION_REQUEST = 100;

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(user.getEmail());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
          @Override
          public void onSuccess(DocumentSnapshot documentSnapshot) {
              User user_data = documentSnapshot.toObject(User.class);
              continuer.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      Intent intention = new Intent(AllowLocationData.this, SuggestedOffersAroundYou.class);
                      intention.putExtra("location", city);
                      startActivity(intention);
                  }

              });
          }
        });



        ActivityCompat.requestPermissions(AllowLocationData.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location loc;
                loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (loc != null) {
                    // Localisation disponible
                    adress.setText("Vous étiez récemment ici : " + loc.getLatitude() + " / " + loc.getLongitude());
                }
            }
        }else {
            Toast.makeText(this, "Votre appareil n'est pas compatible !", Toast.LENGTH_SHORT).show();
        }

        androidLocationListener = new LocationListener() {
            public void onLocationChanged(Location loc) {
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addresses != null;
                city = addresses.get(0).getLocality();
                adress.setText("Vous êtes actuellement à : "+ city);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000, // en millisecondes
                1, // en mètres
                androidLocationListener);


    }
    }






