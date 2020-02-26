package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {
    private FloatingActionButton floatingButtonAdd,
            floatingButtonClear,floatingButtonYellow,floatingButtonPink;
    private BottomNavigationView bottomNavigationView;
    private TextView noDevice;
    private ListView deviceList;
    private ArrayList<Device> arrayDeviceList;
    private DatabaseReference databaseReference;
    private Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagelayout);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_green_energy_small);
        getSupportActionBar().setTitle("Devices");


        arrayDeviceList = new ArrayList<Device>();
            // Device device1 = new Device("1","1",3,"somedescriptionjhesjhjknsnjhsjkhgjhsojlizgofjlsiowjeiojwiorjiolsjkljtisojiosjtkljsijojlkjrsiojtioreoi","some category",2);
            //Device device2 = new Device("2","1",3,"somedescription","some category",3);

        //arrayDeviceList.add(device1);
        //arrayDeviceList.add(device2);
        deviceList = (ListView) findViewById(R.id.added_device_list);
        noDevice = findViewById(R.id.no_device_text);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Device");




        databaseReference.addValueEventListener(new ValueEventListener() {
            AddedDeviceAdapter adapter = new AddedDeviceAdapter(HomepageActivity.this,arrayDeviceList);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChildren()){
                    noDevice.setVisibility(View.VISIBLE);
                } else {
                    Iterable<DataSnapshot> contactChildren = dataSnapshot.getChildren();

                    for(DataSnapshot data : contactChildren){
                            String id = data.child("id").getValue(String.class);
                            String userId = data.child("userId").getValue(String.class);
                            String description = data.child("description").getValue(String.class);
                            String category = data.child("category").getValue(String.class);

                            if(data.child("priority").exists() && data.child("icon").exists()){
                                int priority = data.child("priority").getValue(Integer.class);
                                int icon = data.child("icon").getValue(Integer.class);
                                System.out.println(data.getValue());

                                device = new Device(id,userId,priority,description,category,icon);


                            } else {
                                device = new Device(id, userId, 8, description, category, 1);

                            }
                            arrayDeviceList.add(device);

                        }
                }

                deviceList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        floatingButtonAdd = findViewById(R.id.floating_button);
        floatingButtonClear = findViewById(R.id.floating_button_clear);
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
                startActivity(new Intent(HomepageActivity.this, AddDeviceActivity.class));
                hideTopButton();
            }
        });
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.feed:
                    startActivity(new Intent(getApplication(), HomepageActivity.class));
                    break;
                case R.id.user:
                    startActivity(new Intent(getApplication(), ProfileActivity.class));
                    break;
                case R.id.forum:
                    startActivity(new Intent(getApplication(), CommunityActivity.class));
                    break;
            }
            return true;
        }
    };


    private void hideTopButton(){
            floatingButtonAdd.show();
            floatingButtonClear.hide();
            floatingButtonYellow.hide();
            floatingButtonPink.hide();

        }

        private void showTopButton(){
            floatingButtonAdd.hide();
            floatingButtonClear.show();
            floatingButtonYellow.show();
            floatingButtonPink.show();

        }


        class AddedDeviceAdapter extends ArrayAdapter<Device> {
            private Context context;
            private List<Device> devices;

            public AddedDeviceAdapter(@NonNull Context context, @NonNull List<Device> devices) {
                super(context, 0, devices);
                this.context = context;
                this.devices = devices;
            }

            @Override
            public int getCount() {
                return arrayDeviceList.size();
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                Device device = getItem(position);

                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.deviceitem,null);
                }

                TextView textView0 = (TextView)convertView.findViewById(R.id.item_category);
                TextView textView1 = (TextView)convertView.findViewById(R.id.item_description);
                TextView textView2 = (TextView)convertView.findViewById(R.id.item_priority);
                ImageView image = (ImageView)convertView.findViewById(R.id.icon_category);

                textView0.setText(device.category);
                textView1.setText(device.description);
                textView2.setText(Integer.toString(device.priority));


                return convertView;
            }
        }
    }
