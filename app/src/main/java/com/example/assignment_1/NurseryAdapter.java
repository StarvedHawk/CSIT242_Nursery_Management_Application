package com.example.assignment_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Nursery;

import java.util.ArrayList;

public class NurseryAdapter extends ArrayAdapter<Nursery> {

    private Context myContext;
    private ArrayList<Nursery> nursery_list;

    public NurseryAdapter(Context x, ArrayList<Nursery> s){
        super(x,0,s);
        myContext = x;
        nursery_list = s;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.single_nursery_layout,parent,false);
        }
        Nursery currentNursery = nursery_list.get(position);

        DatabaseHelper db = new DatabaseHelper(myContext);

        TextView Nursery_ID = listItem.findViewById(R.id.nursery_nursery_id);
        Nursery_ID.setText(currentNursery.getNurseryID()+"");

        TextView nursery_name = listItem.findViewById(R.id.nursery_nursery_name);
        nursery_name.setText(currentNursery.getNurseryName()+"");

        TextView minHours = listItem.findViewById(R.id.nursery_minHours);
        minHours.setText(currentNursery.getMinHours()+"");

        TextView precharge = listItem.findViewById(R.id.nursery_precharge);
        precharge.setText(currentNursery.getPreCharge()+"");

        TextView postcharge = listItem.findViewById(R.id.nursery_postcharge);
        postcharge.setText(currentNursery.getPostCharge()+"");

        return listItem;

    }

}
