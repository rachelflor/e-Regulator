package com.example.e_regulator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth firebaseAuth;
    private EditText editUsername, editPassword;
    private Button loginButton;
    private TextView loginError, newRegistration;
    private ActionBar actionBar;

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        firebaseAuth.updateCurrentUser(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);

        firebaseAuth = FirebaseAuth.getInstance();

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_green_energy);
        actionBar.setTitle(R.string.app_name);

        editUsername = findViewById(R.id.text_username) ;
        editPassword = findViewById(R.id.text_password);
        loginButton = findViewById(R.id.login_button);
        loginError = findViewById(R.id.loginInput_error);
        newRegistration = findViewById(R.id.register_link);

        editUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(loginError.getVisibility() == View.VISIBLE){
                    loginError.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(loginError.getVisibility() == View.VISIBLE){
                    loginError.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        newRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));

            }
        });


        loginButton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){
                if(getIsEmpty(editUsername) || getIsEmpty(editPassword)){
                    loginError.setVisibility(View.VISIBLE);
                }
                if(isUsernameAndPasswordValid(editUsername,editPassword,6,6)){
                    startActivity(new Intent(MainActivity.this,HomepageActivity.class));
                }
            }
        });
    }

    private boolean getIsEmpty(EditText editText){
        String value = editText.getText().toString();
       return value.isEmpty();
    }

    private boolean getIsLengthValid(EditText editText, int minLength){
       return editText.getText().length() >= minLength;
    }

    private boolean isUsernameAndPasswordValid(EditText editText1, EditText editText2, int length1, int length2){
        return !getIsEmpty(editText1) && !getIsEmpty(editText2) &&
                getIsLengthValid(editText1,length1) &&
                getIsLengthValid(editText2,length2) ;
    }

}
