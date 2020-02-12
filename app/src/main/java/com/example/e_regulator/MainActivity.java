package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth firebaseAuth;
    private EditText editPassword, editEmail;
    private Button loginButton;
    private TextView loginError, newRegistration, loginLengthError, authError;
    private BottomNavigationView menuNavigation;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_green_energy);
        getSupportActionBar().setTitle(R.string.app_name);

        firebaseAuth = FirebaseAuth.getInstance();

        editPassword = findViewById(R.id.text_password);
        editEmail = findViewById(R.id.text_emailLogin);
        loginButton = findViewById(R.id.login_button);
        loginError = findViewById(R.id.loginInput_error);
        authError = findViewById(R.id.auth_error);
        loginLengthError = findViewById(R.id.loginInputLength_error);
        newRegistration = findViewById(R.id.register_link);

        menuNavigation = findViewById(R.id.nav_container);


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
                if(getIsEmpty(editEmail) || getIsEmpty(editPassword)){
                    loginError.setVisibility(View.VISIBLE);
                } else if(!isUsernameAndPasswordValid(editEmail,editPassword,6,6)) {
                    loginLengthError.setVisibility(View.VISIBLE);
                } else {
                    firebaseAuth.signInWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                startActivity(new Intent(MainActivity.this,HomepageActivity.class));
                            } else {
                                 authError.setVisibility(View.VISIBLE);
                             }
                    }
                });
                }
            }
        });
    }

    public boolean getIsEmpty(EditText editText){
        String value = editText.getText().toString();
       return value.isEmpty();
    }

    public boolean getIsLengthValid(EditText editText, int minLength){
       return editText.getText().length() >= minLength;
    }

    public boolean isUsernameAndPasswordValid(EditText editText1, EditText editText2, int length1, int length2){
        return !getIsEmpty(editText1) && !getIsEmpty(editText2) &&
                getIsLengthValid(editText1,length1) &&
                getIsLengthValid(editText2,length2) ;
    }

   /* private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment fragment = null;

            switch (menuItem.getItemId()) {
                case R.id.user:
                    fragment = new ProfileActivity();
                    break;

                case R.id.forum:
                    fragment = new CommunityActivity();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    } */



}
