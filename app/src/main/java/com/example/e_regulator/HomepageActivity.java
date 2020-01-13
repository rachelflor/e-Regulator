package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomepageActivity extends AppCompatActivity {
    private FloatingActionButton floatingButtonAdd,
            floatingButtonClear,floatingButtonYellow,
            floatingButtonPink, floatingButtonBlue;
    private BottomNavigationView navigationView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagelayout);

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_green_energy);
        actionBar.setTitle(R.string.feedpage_title);
        actionBar.setDisplayShowTitleEnabled(true);

        floatingButtonAdd = findViewById(R.id.floating_button);
        floatingButtonClear = findViewById(R.id.floating_button_clear);
        floatingButtonBlue = findViewById(R.id.floatingBlue);
        floatingButtonYellow = findViewById(R.id.floatingYellow);
        floatingButtonPink = findViewById(R.id.floatingPink);

        floatingButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopButton();
            }
        });

        floatingButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTopButton();
            }
        });

        floatingButtonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepageActivity.this,AddDeviceActivity.class));
                hideTopButton();


            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.feed:
                        startActivity(new Intent(HomepageActivity.this,HomepageActivity.class));
                    case R.id.user:
                        startActivity(new Intent(HomepageActivity.this,ProfileActivity.class));
                    case R.id.forum:
                        startActivity(new Intent(HomepageActivity.this,CommunityActivity.class));
                }
                return true;
            }
        });
    }

    private void hideTopButton(){
        floatingButtonAdd.show();
        floatingButtonClear.hide();
        floatingButtonBlue.hide();
        floatingButtonYellow.hide();
        floatingButtonPink.hide();

    }

    private void showTopButton(){
        floatingButtonAdd.hide();
        floatingButtonClear.show();
        floatingButtonBlue.show();
        floatingButtonYellow.show();
        floatingButtonPink.show();

    }
}
