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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagelayout);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_green_energy);
        getSupportActionBar().setTitle("Devices");

        deviceList = (ListView) findViewById(R.id.added_device_list);
        noDevice = findViewById(R.id.no_device_text);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Device");

        databaseReference.addValueEventListener(new ValueEventListener() {
            AddedDeviceAdapter adapter = new AddedDeviceAdapter(HomepageActivity.this,R.layout.deviceitem,arrayDeviceList);




            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChildren()){
                    noDevice.setVisibility(View.VISIBLE);
                } else {
                    for(DataSnapshot data : dataSnapshot.getChildren()){

                        String id = data.child("id").getValue().toString();
                        String userId = data.child("userId").getValue().toString();
                        int priority = Integer.parseInt(data.child("priority").getValue().toString());
                        String description = data.child("description").getValue().toString();
                        String category = data.child("category").getValue().toString();


                        Device device = new Device(id,userId,priority,description,category);
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

            public AddedDeviceAdapter(@NonNull Context context, int resource, @NonNull List<Device> devices) {
                super(context, resource, devices);
                this.context = context;
                this.devices = devices;
            }

            @Override
            public int getCount() {
                return 1;
            }

           /* @Override
            public Object getItem(int position) {
                return null;
           }  */

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
