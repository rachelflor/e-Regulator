package com.example.e_regulator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.e_regulator.R;

import org.jetbrains.annotations.NotNull;

public class DeviceAdapter extends BaseAdapter {
    Context context;
    String [] text ;
    int[] drawable;

    public DeviceAdapter(Context context, String[] text, int[] drawable){
        this.context = context;
        this.text = text;
        this.drawable = drawable;
    }

    @Override
    public int getCount() {
        return 0;
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

       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View row = inflater.inflate(R.layout.devicecategorylayout,parent,false);

       TextView textView = (TextView) row.findViewById(R.id.item);
       ImageView imageView = (ImageView) row.findViewById(R.id.image_item);

       textView.setText(text[position]);
       imageView.setImageResource(drawable[position]);

        return row;
    }

    static class ViewHolder{
        TextView textName;
        ImageView icon;
    }
}
