package com.example.gestinterim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpEmployer extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = SignUpCandidate.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private ProgressDialog mAuthProgressDialog;
    private String mName;
    Button mCreateUserButton;
    EditText mEnterpriseNameEditText, mServiceEditText, mEmailEditText, mPasswordEditText, mConfirmPasswordEditText, mNationalNrEditText, mCityEditText, mPhoneEditText;
    private DatabaseReference mDatabase;

    User user_data;

    final String USER_TYPE = "employer";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_employer);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();

        mCreateUserButton = findViewById(R.id.signUpEmployerButton);
        mEnterpriseNameEditText = findViewById(R.id.enterpriseNameEditText);
        mServiceEditText = findViewById(R.id.serviceEditText);
        mNationalNrEditText = findViewById(R.id.nationalNumberEmployerEditText);
        mCityEditText = findViewById(R.id.cityEditText);
        mPhoneEditText = findViewById(R.id.phoneEmployerEditText);
        mEmailEditText = findViewById(R.id.emailEmployerEditText);
        mPasswordEditText= findViewById(R.id.passwordEmployerEditText);
        mConfirmPasswordEditText = findViewById(R.id.confirmPasswordEmployerEditText);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mCreateUserButton.setOnClickListener(this);

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
        String enterpriseName = mEnterpriseNameEditText.getText().toString().trim();
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
        String serviceName = mServiceEditText.getText().toString().trim();
        String national_nr = mNationalNrEditText.getText().toString().trim();
        String city = mCityEditText.getText().toString().trim();
        String tel = mPhoneEditText.getText().toString().trim();

        mName = mEnterpriseNameEditText.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(enterpriseName);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;

        mAuthProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            user_data = new User(city, tel ,email ,password, USER_TYPE);
                            user_data.setExtraEmployerData(enterpriseName, serviceName, national_nr);
                            storeUserData();
                            Log.d(TAG, "Authentication successful");
                            createFirebaseUserProfile(task.getResult().getUser());
                        } else {
                            Toast.makeText(SignUpEmployer.this,  "Authentication failed.",
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
                    Intent ConfirmationCodeIntent = new Intent(SignUpEmployer.this, ConfirmationCode.class);
                    ConfirmationCodeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ConfirmationCodeIntent.putExtra("phone", mPhoneEditText.getText().toString());
                    startActivity(ConfirmationCodeIntent);
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
            mEnterpriseNameEditText.setError("Please enter your name");
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}