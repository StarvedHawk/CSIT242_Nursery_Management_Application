package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment_1.Model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Objects;

public class AllStudentStatementActivity extends AppCompatActivity {

    private ListView listview;
    private EntryAdapter myAdapter;
    Context myContext;

    TextView total_stud;
    TextView total_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_student_statement_activity);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_grey)));
        getSupportActionBar().setTitle("All Student Statement Result");

        myContext = this;
        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        int day = i1.getIntExtra("day",0);
        int month = i1.getIntExtra("month",0);
        int year = i1.getIntExtra("year",0);

        total_stud = findViewById(R.id.stud_r_id);
        total_cost = findViewById(R.id.cost_r_id);

        DatabaseHelper db = new DatabaseHelper(AllStudentStatementActivity.this);
        ArrayList<String> allStudentList;

        double total = 0;

        listview = (ListView) findViewById(R.id.AllStudentList);
        if(year!=0 && month!=0 && day!=0)
        {
            allStudentList = db.PRToString(db.filterPayments(day,month,year,NurseryID));
            total = db.getTotalPayments(day,month,year,NurseryID);
        }
        else if(year!=0 && month!=0 && day==0)
        {
            allStudentList = db.PRToString(db.filterPayments(month,year,NurseryID));
            total = db.getTotalPayments(month,year,NurseryID);
        }
        else if(year!=0 && month==0 && day==0)
        {
            allStudentList = db.PRToString(db.filterPayments(year,NurseryID));
            total = db.getTotalPayments(year,NurseryID);
        }else
        {
             allStudentList = db.PRToString(db.getAllPR(NurseryID));
            total = db.getTotalPayments(NurseryID);
        }
        myAdapter = new EntryAdapter(myContext,allStudentList);
        listview.setAdapter(myAdapter);

        int size = allStudentList.size();

       total_stud.setText(Integer.toString(size));
       total_cost.setText(Double.toString(total));

    }
}