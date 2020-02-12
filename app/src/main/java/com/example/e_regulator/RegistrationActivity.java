package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    private EditText rUsername;
    private EditText rEmail;
    private EditText rPassword;
    private EditText rPasswordConfirm;
    private ActionBar actionBar;
    private Button registerButton;
    private TextView registerError;
    private TextView registerLengthError;
    private TextView rSuccessfull;
    private TextView rNotSuccessfull;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationlayout);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_green_energy);
        actionBar.setTitle(R.string.app_name);


        databaseReference  = FirebaseDatabase.getInstance().getReference().child("User");
        firebaseAuth = FirebaseAuth.getInstance();

        rEmail = findViewById(R.id.text_email);
        rPassword = findViewById(R.id.register_text_password);
        rPasswordConfirm = findViewById(R.id.text_password_confirm);
        rUsername = findViewById(R.id.text_rusername);
        registerButton = findViewById(R.id.register_button);
        registerError = findViewById(R.id.registerInput_error);
        rSuccessfull = findViewById(R.id.register_succesfull);
        rNotSuccessfull = findViewById(R.id.register_notsuccesfull);
        registerLengthError = findViewById(R.id.registerInputLength_error);


     registerButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             firebaseAuth.createUserWithEmailAndPassword(rEmail.getText().toString(), rPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(getIsEmpty(rEmail) || getIsEmpty(rPassword) || getIsEmpty(rUsername)){
                         registerError.setVisibility(View.VISIBLE);
                     } else  if(!isUsernameAndPasswordValid(rUsername,rPassword)){
                         registerLengthError.setVisibility(View.VISIBLE);
                     } else if(task.isSuccessful()){
                         String id = databaseReference.push().getKey();
                         User newUser = new User(id,rUsername.getText().toString(),rEmail.getText().toString(),rPassword.getText().toString());
                         databaseReference.setValue(newUser);
                         rSuccessfull.setVisibility(View.VISIBLE);
                         startActivity(new Intent(RegistrationActivity.this,MainActivity.class));

                     } else {
                         rNotSuccessfull.setText(task.getResult().toString());
                         rNotSuccessfull.setVisibility(View.VISIBLE);
                     }
                 }
             });
         }
     });

    }

    public boolean getIsEmpty(EditText editText){
        String value = editText.getText().toString();
        return value.isEmpty();
    }

    public boolean getIsLengthValid(EditText editText){
        return editText.getText().length() >= 6;
    }

    public boolean isUsernameAndPasswordValid(EditText editText1, EditText editText2){
        return !getIsEmpty(editText1) && !getIsEmpty(editText2) &&
                getIsLengthValid(editText1) &&
                getIsLengthValid(editText2) ;
    }

}
