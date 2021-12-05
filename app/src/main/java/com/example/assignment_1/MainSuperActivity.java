package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Nursery;
import com.example.assignment_1.Model.Staff;

import java.util.ArrayList;
import java.util.Objects;

public class MainSuperActivity extends AppCompatActivity {

    Button add_nursery;
    Button add_staff;
    Button view_staff;
    TextView tv1;

    private ListView listview;
    private StaffAdapter myAdapter;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_super);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_black)));
        getSupportActionBar().setTitle("SUPER ADMIN - Manage Database");

        myContext = this;

        add_nursery = findViewById(R.id.add_nursery);
        add_staff = findViewById(R.id.add_staff);
        view_staff = findViewById(R.id.view_staff);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        int StaffID = i1.getIntExtra("StaffID",0);

        add_nursery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(getApplicationContext(),buildNursery.class);
                startActivity(e);

            }
        });

        add_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1 = findViewById(R.id.super_table_name);
                tv1.setText("Add Staff");
                tv1.setVisibility(View.VISIBLE);

                DatabaseHelper db = new DatabaseHelper(myContext);

                listview = (ListView) findViewById(R.id.StaffList);
                ArrayList<Nursery> nurseryList = db.getAllNurseries();

                NurseryAdapter myNurseryAdapter=new NurseryAdapter(myContext,nurseryList);
                //myAdapter = new StudentAdapter(myContext,studentList);
                listview.setAdapter(myNurseryAdapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Nursery name = (Nursery) listview.getItemAtPosition(position);
                        int passedID = name.getNurseryID();

                        Intent i = new Intent(getApplicationContext(),addStaff.class);
                        i.putExtra("NurseryID",passedID);
                        startActivity(i);
                    }
                });
            }
        });

        view_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1 = findViewById(R.id.super_table_name);
                tv1.setText("Staff Details");
                tv1.setVisibility(View.VISIBLE);

                DatabaseHelper db = new DatabaseHelper(MainSuperActivity.this);

                listview = (ListView) findViewById(R.id.StaffList);
                ArrayList<Staff> staffList = db.getAllStaff();

                myAdapter = new StaffAdapter(myContext,staffList);

                listview.setAdapter(myAdapter);

            }
        });

    }
}