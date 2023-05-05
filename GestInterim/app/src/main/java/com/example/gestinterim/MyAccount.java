package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView userDataTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        userDataTextView = findViewById((R.id.UserDataText));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(user.getEmail());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user_data = documentSnapshot.toObject(User.class);

                if(user_data.getType().equals("candidate")){
                    userDataTextView.setText("First name: " + user_data.getFirstname() +
                            "\n\nLast name: " + user_data.getLastname() +
                            "\n\nEmail: " + user.getEmail() +
                            "\n\nPhone number: " + user_data.getTel() +
                            "\n\nCity: " + user_data.getCity() +
                            "\n\nNationality: " + user_data.getNationality());
                }else if(user_data.getType().equals("employer")){
                    userDataTextView.setText("Enterprise: " + user_data.getEnterpriseName() +
                            "\n\nService: " + user_data.getService() +
                            "\n\nNational number: " + user_data.getNational_nr() +
                            "\n\nEmail: " + user.getEmail() +
                            "\n\nPhone number: " + user_data.getTel() +
                            "\n\nCity: " + user_data.getCity() );
                }else if(user_data.getType().equals("agency")){
                    userDataTextView.setText("Agency: " + user_data.getAgencyName() +
                            "\n\nService: " + user_data.getService() +
                            "\n\nNational number: " + user_data.getNational_nr() +
                            "\n\nEmail: " + user.getEmail() +
                            "\n\nPhone number: " + user_data.getTel() +
                            "\n\nCity: " + user_data.getCity() );
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MyAccount.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}