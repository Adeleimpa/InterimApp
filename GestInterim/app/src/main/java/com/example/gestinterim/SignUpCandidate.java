package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpCandidate extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText city;
    EditText nationality;
    EditText phone_nr;
    EditText email;
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

        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkData();
            }
        });
    }

    // check if data entered are correct
    void checkData() {
        if (isEmpty(firstName)) {
            firstName.setError("First name is required!");
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }

        if (!isEmail(email)) {
            email.setError("Enter valid email!");
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}