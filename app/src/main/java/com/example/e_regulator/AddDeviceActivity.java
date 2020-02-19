package com.example.e_regulator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDeviceActivity extends AppCompatActivity {
    public Spinner  spinner;
    private ListView deviceList;
    private String [] deviceName;
    private Button addDeviceButton;
    public EditText description;
    private String categoryContainer;
   // private int [] drawable =  {R.drawable.ic_microwave, R.drawable.ic_refrigerator, R.drawable.ic_kitchen_black_24dp};

    DatabaseReference databaseReference;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewdevice);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        categoryContainer = "";
        spinner = findViewById(R.id.spinner);
        description = findViewById(R.id.deviceDescription);
        deviceList = findViewById(R.id.nothardwareDevice);
        deviceName = getResources().getStringArray(R.array.nonHardwareDeviceCategory);
        addDeviceButton = findViewById(R.id.confirm_add);

        DeviceAdapter adapter = new DeviceAdapter();
        deviceList.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Device");
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryContainer = deviceName[position];

            }
        });

        addDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = databaseReference.push().getKey();
                String bezeichnung = description.getText().toString();
                String priority = spinner.getSelectedItem().toString();

                Device newDevice = new Device(id, currentUserId,Integer.parseInt(priority), bezeichnung, categoryContainer);

                if(bezeichnung != null && priority != null && categoryContainer != null){
                    databaseReference.setValue(newDevice, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if(databaseError != null){
                                startActivity(new Intent(AddDeviceActivity.this,HomepageActivity.class));
                            } else {
                                Toast.makeText(AddDeviceActivity.this,"Something went wrong! ",Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }

            }
        });

    }

    class DeviceAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return deviceName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.devicecategorylayout,null);

            TextView textView = (TextView)convertView.findViewById(R.id.item);
           // ImageView imageView = (ImageView)convertView.findViewById(R.id.image_item);

           // imageView.setImageResource(drawable[position]);
            textView.setText(deviceName[position]);

            return convertView;
        }
    }
}
