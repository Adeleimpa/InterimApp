package com.example.gestinterim;

import static com.example.gestinterim.SignUpCandidate.TAG;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class ConfirmationCode extends AppCompatActivity {
    private static final String TAG = "ConfirmCodeActivity";
    EditText code;
    Button validate, send_again, send_code;
    String phone;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);

        mAuth = FirebaseAuth.getInstance();
        code = findViewById(R.id.editTextNumber);
        validate = findViewById(R.id.validateCodeButton);
        send_again = findViewById(R.id.sendAgainButton);
        send_code = findViewById(R.id.sendCodeButton);

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        send_code.setOnClickListener(view -> {
                StringBuilder tel = new StringBuilder();

                tel.append("+33 ");
                String[] list = phone.split("");
                for (int i = 0; i < phone.length()-1; i+=2) {
                    tel.append((String) (list[i] + list[i + 1]));
                    tel.append(" ");
                }
                Log.d(TAG, tel.toString());

                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Intent intention = new Intent(ConfirmationCode.this, AllowLocationData.class);
                        startActivity(intention);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }
                };
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(String.valueOf(tel))
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(this)
                                .setCallbacks(mCallbacks)
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);

        });



    }


}
