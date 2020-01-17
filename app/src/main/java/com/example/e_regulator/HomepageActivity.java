package com.example.e_regulator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomepageActivity extends AppCompatActivity {
    private FloatingActionButton floatingButtonAdd,
            floatingButtonClear,floatingButtonYellow,
            floatingButtonPink, floatingButtonBlue;
    private ActionBar actionBar;
    private ListView deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagelayout);

        deviceList = (ListView) findViewById(R.id.added_device_list);
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
                startActivity(new Intent(HomepageActivity.this,addHardwareActivity.class));
                hideTopButton();
            }
        });

        floatingButtonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepageActivity.this,AddDeviceActivity.class));
                hideTopButton();
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


    class AddedDeviceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.deviceitem,null);

            ImageView imageView = (ImageView)convertView.findViewById(R.id.item_icon_category);
            TextView textView = (TextView)convertView.findViewById(R.id.item_description);
            TextView textView1 = (TextView)convertView.findViewById(R.id.item_priority);

            return convertView;
        }
    }
}
