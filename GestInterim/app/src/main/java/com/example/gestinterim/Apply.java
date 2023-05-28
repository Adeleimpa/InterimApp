package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Apply extends AppCompatActivity {

    EditText name, surname, nationality, city, phone, mail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        name = findViewById(R.id.firstNameEdittext);
        surname = findViewById(R.id.lastNameEdittext);
        nationality = findViewById(R.id.nationalityEditText);
        city = findViewById(R.id.cityEdittext);
        phone = findViewById(R.id.phoneEditText);
        mail = findViewById(R.id.mailEdittext);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(user.getEmail());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user_data = documentSnapshot.toObject(User.class);
                if(user_data.getType().equals("candidate")){
                    name.setText(user_data.getFirstname());
                    surname.setText(user_data.getLastname());
                    nationality.setText(user_data.getNationality());
                    city.setText(user_data.getCity());
                    phone.setText(user_data.getTel());
                    mail.setText(user_data.getMail());
                }
            }
        });
    }
}