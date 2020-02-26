package com.example.e_regulator;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private LinearLayout privateDataContainer;
    private TextView numberOfSavedDevices;
    private ImageView dropUp;
    private ImageView dropDown;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private LinearLayout logout;
    public static int countDevices = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilelayout);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_account_circle_black_24dp);
        getSupportActionBar().setTitle("Profil");

        privateDataContainer = findViewById(R.id.privateData_container);
        dropUp = findViewById(R.id.dropUp);
        dropDown = findViewById(R.id.dropDown);
        logout = findViewById(R.id.logout);
        numberOfSavedDevices = findViewById(R.id.number_of_saved_devices);

        firebaseAuth = FirebaseAuth.getInstance();


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Device");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChildren()){
                    countDevices = 0;
                } else {
                    countDevices = (int) dataSnapshot.getChildrenCount();
                }
                 numberOfSavedDevices.setText(Integer.toString(countDevices));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });


        dropUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropUp.setVisibility(View.GONE);
                dropDown.setVisibility(View.VISIBLE);
                privateDataContainer.setVisibility(View.GONE);

            }
        });

        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropDown.setVisibility(View.GONE);
                dropUp.setVisibility(View.VISIBLE);
                privateDataContainer.setVisibility(View.VISIBLE);
            }
        });

    }


    public BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.feed:
                    startActivity(new Intent(getApplication(), HomepageActivity.class));
                    break;
                case R.id.user:
                    if(getBaseContext() instanceof ProfileActivity){
                        break;
                    } else {
                        startActivity(new Intent(getApplication(), ProfileActivity.class));
                        break;
                    }
                case R.id.forum:
                    startActivity(new Intent(getApplication(), CommunityActivity.class));
                    break;
            }
            return true;
        }
    };



}
