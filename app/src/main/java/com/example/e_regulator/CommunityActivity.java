package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CommunityActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private LinearLayout sharewithfriend;
    private LinearLayout sharewithcommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communitylayout);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_group_black_24dp);
        getSupportActionBar().setTitle("Community");

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        sharewithfriend = findViewById(R.id.sharewithfriend);
        sharewithcommunity = findViewById(R.id.sharewithcommunity);

        sharewithfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommunityActivity.this, SharewithfriendActivity.class));
            }
        });

        sharewithcommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommunityActivity.this, SharewithcommunityActivity.class));
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
