package com.example.gestinterim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ConfirmationCode extends AppCompatActivity {

    EditText code;
    Button validate, send_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        code = findViewById(R.id.editTextNumber);
        validate = findViewById(R.id.validateCodeButton);
        send_again = findViewById(R.id.sendAgainButton);

    }
}