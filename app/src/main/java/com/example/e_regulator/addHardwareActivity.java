package com.example.e_regulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class addHardwareActivity extends AppCompatActivity {
    Resources res = getResources();
    private ListView hardwareDeviceList;
    String [] hardwareDeviceName;
    int[] drawable = {R.drawable.ic_smartphone_call, R.drawable.ic_smartwatch, R.drawable.ic_laptop, R.drawable.ic_earphones,R.drawable.ic_tablet_mac_black_24dp};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardwarelayout);

        hardwareDeviceName = res.getStringArray(R.array.deviceCategorie);

        hardwareDeviceList = findViewById(R.id.device_list);

        CustomAdapter adapter = new CustomAdapter();
        hardwareDeviceList.setAdapter(adapter);

        hardwareDeviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(addHardwareActivity.this, "" +hardwareDeviceName[position],Toast.LENGTH_SHORT).show();
                //view.setBackgroundColor(res.getColor(R.color.inputBackground));

            }
        });
    }

    class CustomAdapter extends BaseAdapter {

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
            textView.setText(hardwareDeviceName[position]);

            return convertView;
        }
    }
}
