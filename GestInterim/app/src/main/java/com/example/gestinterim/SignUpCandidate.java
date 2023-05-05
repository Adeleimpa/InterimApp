package com.example.gestinterim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpCandidate extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SignUpCandidate.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private ProgressDialog mAuthProgressDialog;
    private String mName;
    Button mCreateUserButton;
    EditText mFirstNameEditText, mLastNameEditText, mEmailEditText, mPasswordEditText, mConfirmPasswordEditText, mNationalityEditText, mCityEditText, mPhoneEditText;
    private DatabaseReference mDatabase;

    User user_data;

    /*String firstName;
    String lastName;
    String nationality;
    String city;
    String phoneNumber;
    String password;
    String email;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_candidate);
        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();

        mCreateUserButton = findViewById(R.id.signUpButton);
        mFirstNameEditText = findViewById(R.id.firstNameEditText);
        mLastNameEditText = findViewById(R.id.lastNameEditText);
        mNationalityEditText = findViewById(R.id.nationality);
        mCityEditText = findViewById(R.id.cityEditText);
        mPhoneEditText = findViewById(R.id.phoneNumberEditText);
        mEmailEditText = findViewById(R.id.emailEditText);
        mPasswordEditText= findViewById(R.id.passwordEditText);
        mConfirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mCreateUserButton.setOnClickListener(this);

        //Tentative de stockage des données de chaque utilisateur dans la base de données firebase
        //Pas d'erreur, mais ne sauvegarde rien dans la BDD
        /*mCreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFirstNameEditText.getText().toString() != " "){
                    firstName = mFirstNameEditText.getText().toString();
                }
                if(mLastNameEditText.getText().toString() != " "){
                    lastName =mLastNameEditText.getText().toString();
                }
                if(mNationalityEditText.getText().toString() != " "){
                    nationality = mNationalityEditText.getText().toString();
                }
                if(mCityEditText.getText().toString() != " "){
                    city = mCityEditText.getText().toString();
                }
                if(mPhoneEditText.getText().toString() != " "){
                    phoneNumber = mPhoneEditText.getText().toString();
                }
                if(mEmailEditText.getText().toString() != " "){
                    email = mEmailEditText.getText().toString();
                }
                if(mPasswordEditText.getText().toString() != " "){
                    password = mPasswordEditText.getText().toString();
                }

                writeNewUser("user", firstName, lastName, nationality, city, phoneNumber, email, password);
                Intent i = new Intent(SignUpCandidate.this, MyAccount.class);
                startActivity(i);
                finish();
            }
        });*/
        onClick(mCreateUserButton);
        createAuthStateListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createNewUser() {
        String name = mLastNameEditText.getText().toString().trim();
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
        String first_name = mFirstNameEditText.getText().toString().trim();
        String nationality = mNationalityEditText.getText().toString().trim();
        String city = mCityEditText.getText().toString().trim();
        String tel = mPhoneEditText.getText().toString().trim();
        
        mName = mFirstNameEditText.getText().toString().trim();
        
        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;
        
        mAuthProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            user_data = new User(first_name, name, nationality, city, tel ,email ,password);
                            storeUserData();
                            Log.d(TAG, "Authentication successful");
                            createFirebaseUserProfile(task.getResult().getUser());
                        } else {
                            Toast.makeText(SignUpCandidate.this,  "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignUpCandidate.this, MyAccount.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }
    private void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, user.getDisplayName());
                        }
                    }

                });
    }

    @Override
    public void onClick(View view) {

        if (view == mCreateUserButton) {
            createNewUser();
        }

    }

    // Store user data in cloud firestore
    private void storeUserData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(user_data.getMail()).set(user_data);
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mFirstNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    public void writeNewUser(String userId, String fname, String lname, String nat, String city, String tel, String email, String pw) {
        User user = new User(fname, lname, nat, city, tel, email, pw);
        mDatabase.child("users").child(userId).setValue(user);
    }
}