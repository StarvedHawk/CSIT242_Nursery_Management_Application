package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Nursery;

import java.util.Objects;

public class buildNursery extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    Spinner hours;

    Button builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_nursery);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_black)));
        getSupportActionBar().setTitle("Add Nursery");

        ed1 = findViewById(R.id.build_branch);
        ed2 = findViewById(R.id.build_min_id);
        ed3= findViewById(R.id.build_max_id);
        hours = findViewById(R.id.build_hour_spinner);

        builder = findViewById(R.id.build_b_id);

        Intent i1 = getIntent();
        DatabaseHelper db = new DatabaseHelper(buildNursery.this);


        builder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String branchname = ed1.getText().toString();
                    int min_cost = Integer.parseInt(ed2.getText().toString());
                    int max_cost = Integer.parseInt(ed3.getText().toString());
                    int minHours = Integer.parseInt(hours.getSelectedItem().toString());

                    Nursery newNursery = new Nursery(-1,branchname,min_cost,max_cost,minHours);

                    if(!branchname.matches("") && min_cost!=0 && max_cost!=0){
                        if(branchname.matches("^([a-zA-Z\\s]*)$")){
                            db.AddNursery(newNursery);
                            Toast.makeText(getApplicationContext(), "Successfully Added",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Pick a better first name,We don't accept Elon's naming sense",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Successfully FAILED!",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){}
                ed1.getText().clear();
                ed2.getText().clear();
                ed3.getText().clear();
            }
        });
    }
}