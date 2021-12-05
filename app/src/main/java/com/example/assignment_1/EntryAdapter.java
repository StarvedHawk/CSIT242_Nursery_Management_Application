package com.example.assignment_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EntryAdapter extends ArrayAdapter<String> {

    private Context myContext;
    private ArrayList<String> entry_list;

    public EntryAdapter(Context x, ArrayList<String> e){
        super(x,0,e);
        myContext = x;
        entry_list = e;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.single_entry_layout,parent,false);
        }
        String currentExpense = entry_list.get(position);

        TextView expenses_string = listItem.findViewById(R.id.text_e_id);
        expenses_string.setText(currentExpense);

        return listItem;

    }

}
