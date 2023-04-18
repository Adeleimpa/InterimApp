package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity { // Login activity

    EditText email;
    EditText password;
    Button login_button;
    Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup_button = findViewById(R.id.signupbutton);
        email = findViewById(R.id.mailinput);
        password = findViewById(R.id.password);
        login_button = findViewById(R.id.loginbutton);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO verify username and password are in database
                //checkUsername();
                //checkPassword();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, SignUpCandidate.class);
                startActivity(i);
            }
        });
    }
}

