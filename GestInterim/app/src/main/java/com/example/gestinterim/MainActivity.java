package com.example.gestinterim;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< Updated upstream

public class MainActivity extends AppCompatActivity { // Login activity

=======
<<<<<<< HEAD
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { // Login activity

    Button signup_button, login_button;
    EditText mail, password;
=======

public class MainActivity extends AppCompatActivity { // Login activity

>>>>>>> Stashed changes
    EditText email;
    EditText password;
    Button login_button;
    Button signup_button;
>>>>>>> 348c6976987641af5eda1e2a266093bf1325cf36

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup_button = findViewById(R.id.signupbutton);
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
        login_button = findViewById(R.id.loginbutton);
        mail = findViewById(R.id.mailinput);
        password = findViewById(R.id.password);
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
>>>>>>> 348c6976987641af5eda1e2a266093bf1325cf36
>>>>>>> Stashed changes

        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, Signing_up.class);
                startActivity(i);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = mail.getText().toString();
                String pw = password.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pw)) {
                    Toast.makeText(MainActivity.this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(MainActivity.this, MyAccount.class);
                    startActivity(i);
                }
            }
        });

    }


}

