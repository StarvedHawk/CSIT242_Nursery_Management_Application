package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Student;

import java.util.Objects;

public class EnrollStudentActivity extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    EditText ed4;
    EditText ed5;
    Button enrollStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_student);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        getSupportActionBar().setTitle("Enroll Student");

        ed1 = findViewById(R.id.f_name_id);
        ed2 = findViewById(R.id.second_name_id);
        ed3 = findViewById(R.id.dob_id);
        ed4 = findViewById(R.id.p_numb_id);
        ed5 = findViewById(R.id.email_id);
        enrollStudent = findViewById(R.id.enroll_b_id);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);

        DatabaseHelper db = new DatabaseHelper(EnrollStudentActivity.this);


        enrollStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String firstName = ed1.getText().toString();
                    String secondName = ed2.getText().toString();
                    String DOB = ed3.getText().toString();
                    String parentNumber = ed4.getText().toString();
                    String email = ed5.getText().toString();

                    Student newStudent = new Student(-1,NurseryID,firstName,secondName,parentNumber,DOB,email);

                    if(!firstName.matches("") && !secondName.matches("") && !DOB.matches("") && !parentNumber.matches("") && !email.matches("")){
                        if(firstName.matches("^([a-zA-Z\\s]*)$"))
                            if(secondName.matches("^([a-zA-Z\\s]*)$"))
                                if(parentNumber.matches("^([0-9]{10})$"))
                                    if(DOB.matches("^(0[1-9]|[12][0-9]|3[01])[- \\/.](0[1-9]|1[012])[- \\/.]([0-9]{4})$"))
                                        if(email.matches("^([\\S]*@[\\S]*\\.com)$")){
                                            db.AddStudent(newStudent);
                                            Toast.makeText(getApplicationContext(), "Successfully Added",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                            Toast.makeText(getApplicationContext(),"Format : username@domain.com",Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(getApplicationContext(),"dd/mm/yyyy",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getApplicationContext(),"Dunno where you live but our phone numbers are 10 digits",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(),"We don't accept Elon's naming sense,even for surnames",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Pick a better first name,We don't accept Elon's naming sense",Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Successfully FAILED!",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){}
            }
        });
    }
}