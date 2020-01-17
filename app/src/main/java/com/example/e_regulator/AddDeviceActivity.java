package com.example.e_regulator;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDeviceActivity extends AppCompatActivity {
    public Spinner  spinner;
    private ListView deviceList;
    private String [] deviceName;
    private LinearLayout linearLayout;
    public EditText description;
    private Resources res = getResources();
    private int [] drawable =  {R.drawable.ic_microwave, R.drawable.ic_refrigerator, R.drawable.ic_kitchen_black_24dp};

    DatabaseReference databaseDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewdevice);

        spinner = (Spinner) findViewById(R.id.spinner);
        linearLayout = (LinearLayout) findViewById(R.id.listItem);
        description = (EditText) findViewById(R.id.deviceDescription);
        deviceList = (ListView) findViewById(R.id.nothardwareDevice);
        deviceName = res.getStringArray(R.array.nonHardwareDeviceCategory);

        DeviceAdapter adapter = new DeviceAdapter();
        deviceList.setAdapter(adapter);

        databaseDevices = FirebaseDatabase.getInstance().getReference("Device");

        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddDeviceActivity.this, "" +deviceName[position],Toast.LENGTH_SHORT).show();
               // view.setBackgroundColor(res.getColor(R.color.inputBackground));
                linearLayout.setBackgroundColor(res.getColor(R.color.inputBackground));
            }
        });

    }

    public void addDevice(){
        String bezeichnung = description.getText().toString().trim();
        String priority = spinner.getSelectedItem().toString();
        String category = "";
        int icon = 1;

        if(!TextUtils.isEmpty(bezeichnung) && priority != null){
            String id = databaseDevices.push().getKey();
            Device newDevice = new Device(id, Integer.parseInt(priority), bezeichnung, category, icon);
        }

    }

    class DeviceAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return drawable.length;
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
            ImageView imageView = (ImageView)convertView.findViewById(R.id.image_item);

            imageView.setImageResource(drawable[position]);
            textView.setText(deviceName[position]);

            return convertView;
        }
    }
}
