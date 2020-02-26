package com.example.e_regulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {

    private LinearLayout layoutPriority;
    private TextView itemPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.deviceitem);

        layoutPriority = findViewById(R.id.layout_priority);
        itemPriority = findViewById(R.id.item_priority);

        setPriorityColor();

    }


    private void setPriorityColor(){
        if(itemPriority.getText().equals(String.valueOf(3))){
            layoutPriority.setBackground(getResources().getDrawable(R.drawable.ic_smartphonelol));
        }
    }
}
