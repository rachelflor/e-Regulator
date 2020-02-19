package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    private TextView noDevice;
    private ListView deviceList;
    private ArrayList<Device> arrayDeviceList;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagelayout);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_green_energy);
        getSupportActionBar().setTitle("Devices");

        arrayDeviceList = new ArrayList<Device>(){
            Device device1 = new Device("1","1",3,"some description","some category");
            Device device2 = new Device("2","1",3,"some description","some category");

        };
        deviceList = (ListView) findViewById(R.id.added_device_list);
        noDevice = findViewById(R.id.no_device_text);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Device");




        databaseReference.addValueEventListener(new ValueEventListener() {
            AddedDeviceAdapter adapter = new AddedDeviceAdapter(HomepageActivity.this,arrayDeviceList);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChildren()){
                    noDevice.setVisibility(View.VISIBLE);
                } else {
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            /* String id = data.child("id").getValue(String.class);
                             String userId = data.child("userId").getValue(String.class);
                            // int priority = data.child("priority").getValue(Integer.class);
                             String description = data.child("description").getValue(String.class);
                             String category = data.child("category").getValue(String.class);


                         device = new Device(id,userId,1,description,category); */

                        arrayDeviceList.add(new Device("6","12",2,"new","new"));
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

                textView0.setText(device.category);
                textView1.setText(device.description);
                textView2.setText(String.valueOf(device.priority));

                return convertView;
            }
        }
    }
