package com.anupam.androidcameraxtflite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class secondadapter extends ArrayAdapter<String> {
    private ArrayList<String> predicteditems;
    private Context mContext;

    public secondadapter(ArrayList<String> items,Context mContext)
    {
        super(mContext,0,items);
        this.predicteditems = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_exam_list_layout, parent, false);

        TextView view =  convertView.findViewById(R.id.registered_item_exam_name);
        view.setText(predicteditems.get(position));

        return convertView;
    }
}
