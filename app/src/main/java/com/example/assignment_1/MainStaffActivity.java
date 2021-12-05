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
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Student;

import java.util.ArrayList;
import java.util.Objects;

public class MainStaffActivity extends AppCompatActivity {

    Button enroll;
    Button charge_student;
    Button generate_statement;
    TextView tv1;

    private ListView listview;
    private StudentAdapter myAdapter;
    Context myContext;

    ImageButton info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_main);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        getSupportActionBar().setTitle("STAFF - Manage Student");

        myContext = this;

        enroll = findViewById(R.id.enroll_stu_id);
        charge_student = findViewById(R.id.ch_stu);
        generate_statement = findViewById(R.id.gen_single_stu_id);
        info = findViewById(R.id.helper_id);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        int StaffID = i1.getIntExtra("StaffID",0);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(getApplicationContext(),EnrollStudentActivity.class);
                e.putExtra("NurseryID",NurseryID);
                startActivity(e);
            }
        });

        charge_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1 = findViewById(R.id.table_name);
                tv1.setText("Charge Student");
                tv1.setVisibility(View.VISIBLE);

                DatabaseHelper db = new DatabaseHelper(MainStaffActivity.this);

                listview = (ListView) findViewById(R.id.StudentList);
                ArrayList<Student> studentList = db.getAllStudents(NurseryID);

                myAdapter = new StudentAdapter(myContext,studentList);
                listview.setAdapter(myAdapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Student name = (Student) listview.getItemAtPosition(position);
                        int StudentID = name.getStudentID();

                        Intent i = new Intent(getApplicationContext(),ChargeStudentActivity.class);
                        i.putExtra("NurseryID",NurseryID);
                        i.putExtra("StudentID",StudentID);
                        startActivity(i);
                    }
                });
            }
        });

        generate_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1 = findViewById(R.id.table_name);
                tv1.setText("Generate Student Statement");
                tv1.setVisibility(View.VISIBLE);

                DatabaseHelper db = new DatabaseHelper(MainStaffActivity.this);

                listview = (ListView) findViewById(R.id.StudentList);
                ArrayList<Student> studentList = db.getAllStudents(NurseryID);

                myAdapter = new StudentAdapter(myContext,studentList);
                listview.setAdapter(myAdapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Student name = (Student) listview.getItemAtPosition(position);
                        int StudentID = name.getStudentID();

                        Intent s = new Intent(getApplicationContext(),GenerateStatementActivity.class);
                        s.putExtra("NurseryID",NurseryID);
                        s.putExtra("StudentID",StudentID);
                        startActivity(s);
                    }
                });
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainStaffActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Help");
                builder.setMessage("Generate Student Statement: Select student from the displayed information \n\n" +
                        "Charge Student: Select student from the displayed information \n\n" +
                        "Enroll Student: Will redirect you to an enrollment page");
                builder.setPositiveButton("OK",null);
                builder.show();
            }
        });




    }
}
