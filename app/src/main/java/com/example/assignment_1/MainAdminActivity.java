package com.example.assignment_1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainAdminActivity extends AppCompatActivity {


    Button nursery_expense;
    Button profit_margin;
    Button all_student_statement;
    ImageButton info;

    private ListView listview;
    private StudentAdapter myAdapter;
    Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_grey)));
        getSupportActionBar().setTitle("ADMIN - Manage Expenses");

        myContext = this;

        Intent i1=getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        int StaffID = i1.getIntExtra("StaffID",0);

        nursery_expense = findViewById(R.id.nus_exp_id);
        profit_margin = findViewById(R.id.prof_m);
        all_student_statement = findViewById(R.id.gen_all_stud_id);
        info = findViewById(R.id.helper_id);

        nursery_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent n = new Intent(getApplicationContext(),CurrentExpensesActivity.class);
            n.putExtra("NurseryID",NurseryID);
            startActivity(n);
            }
        });

        profit_margin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(getApplicationContext(),ProfitMarginActivity.class);
                p.putExtra("NurseryID",NurseryID);
                startActivity(p);
            }
        });

        all_student_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent g = new Intent(getApplicationContext(),GenerateAllStatementActivity.class);
                g.putExtra("NurseryID",NurseryID);
                startActivity(g);

            }
        });
    }
}