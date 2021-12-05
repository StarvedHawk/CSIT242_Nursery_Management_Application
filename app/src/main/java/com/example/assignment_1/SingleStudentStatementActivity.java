package com.example.assignment_1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_1.Model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Objects;

public class SingleStudentStatementActivity extends AppCompatActivity {

    private ListView listview;
    private EntryAdapter myAdapter;
    Context myContext;

    TextView total_stud;
    TextView total_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_student_statement_display);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        getSupportActionBar().setTitle("Generated Student Statement");

        myContext = this;
        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        int StudentID = i1.getIntExtra("StudentID",0);
        int day = i1.getIntExtra("day",0);
        int month = i1.getIntExtra("month",0);
        int year = i1.getIntExtra("year",0);

        total_stud = findViewById(R.id.total_student_id);
        total_cost = findViewById(R.id.cost_student_id);

        DatabaseHelper db = new DatabaseHelper(SingleStudentStatementActivity.this);
        ArrayList<String> allStudentList;

        double total = 0;

        listview = (ListView) findViewById(R.id.display_single_student_id);
        if(year!=0 && month!=0 && day!=0)
        {
            allStudentList = db.PRToString(db.filterStudentPayments(StudentID,day,month,year,NurseryID));
            total = db.getTotalStudentPayments(StudentID,day,month,year,NurseryID);
        }
        else if(year!=0 && month!=0 && day==0)
        {
            allStudentList = db.PRToString(db.filterStudentPayments(StudentID,month,year,NurseryID));
            total = db.getTotalStudentPayments(StudentID,month,year,NurseryID);
        }
        else if(year!=0 && month==0 && day==0)
        {
            allStudentList = db.PRToString(db.filterStudentPayments(StudentID,year,NurseryID));
            total = db.getTotalStudentPayments(StudentID,year,NurseryID);
        }else
        {
            allStudentList = db.PRToString(db.filterStudentPayments(StudentID,NurseryID));
            total = db.getTotalStudentPayments(StudentID,NurseryID);
        }
        myAdapter = new EntryAdapter(myContext,allStudentList);
        listview.setAdapter(myAdapter);

        int size = allStudentList.size();

        total_stud.setText(Integer.toString(size));
        total_cost.setText(Double.toString(total));

    }
}
