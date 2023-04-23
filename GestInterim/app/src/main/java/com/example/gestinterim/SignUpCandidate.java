package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpCandidate extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText city;
    EditText nationality;
    EditText phone_nr;
    EditText email;
    EditText pw;
    EditText confirm;
    Button signup_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_candidate);

        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        city = findViewById(R.id.city);
        nationality = findViewById(R.id.nationality);
        phone_nr = findViewById(R.id.PhoneNumber);
        email = findViewById(R.id.EMAIL);
        signup_button = findViewById(R.id.signUp);
        pw = findViewById(R.id.MDP);
        confirm = findViewById(R.id.CONFIRMMDP);

        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String prenom = firstName.getText().toString();
                String nom = lastName.getText().toString();
                String tel = phone_nr.getText().toString();
                String password = pw.getText().toString();
                String confirmpassword = confirm.getText().toString();
                if(TextUtils.isEmpty(prenom) || TextUtils.isEmpty(nom) || TextUtils.isEmpty(tel) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(SignUpCandidate.this, "Les champs avec une * doivent être remplis !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(SignUpCandidate.this, ConfirmationCode.class);
                    startActivity(i);
                }

            }
        });
    }


}