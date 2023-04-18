package com.example.gestinterim;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpCandidate extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText city;
    EditText nationality;
    EditText phone_nr;
    EditText email;
    Button signup_button;
    EditText password;
    EditText confirm_password;

    private FirebaseAuth mAuth;



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
        password = findViewById(R.id.MDP);
        confirm_password = findViewById(R.id.CONFIRMMDP);

        String email_str = email.getText().toString();
        String password_str = password.getText().toString();

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

        if (isEmpty(password)) {
            password.setError("Empty password!");
        }

        if(password != confirm_password){
            confirm_password.setError("Several passwords used!");
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