package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    private FirebaseAuth firebaseAuth;
    private ActionBar actionBar;
    private EditText rEmail;
    private EditText rPassword;
   // private DatabaseReference database;
    //Test test;

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        firebaseAuth.updateCurrentUser(currentUser);
       // test.setName("something");
       // database.push().setValue(test);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationlayout);

        firebaseAuth = FirebaseAuth.getInstance();

        rEmail = (EditText) findViewById(R.id.text_email);
        rPassword = (EditText) findViewById(R.id.register_text_password);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_green_energy);
        actionBar.setTitle(R.string.app_name);

        firebaseAuth.createUserWithEmailAndPassword(rEmail.toString(), rPassword.toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "createUserWithEmail : Success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    firebaseAuth.updateCurrentUser(user);
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(RegistrationActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    firebaseAuth.updateCurrentUser(null);
                }
            }
        });
    }
}
