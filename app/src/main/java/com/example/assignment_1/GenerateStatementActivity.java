package com.example.assignment_1;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GenerateStatementActivity extends AppCompatActivity {

    Spinner day;
    Spinner month;
    Spinner year;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_statement_activity);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        getSupportActionBar().setTitle("Student Statement");

        day = findViewById(R.id.day_spinner);
        month = findViewById(R.id.month_spinner);
        year = findViewById(R.id.year_spinner);
        b1 = findViewById(R.id.gen_single_id);


        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        int StudentID = i1.getIntExtra("StudentID",0);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g = new Intent(getApplicationContext(),SingleStudentStatementActivity.class);
                g.putExtra("NurseryID", NurseryID);
                g.putExtra("StudentID",StudentID);
                g.putExtra("day",Integer.parseInt(day.getSelectedItem().toString()));
                g.putExtra("month",Integer.parseInt(month.getSelectedItem().toString()));
                g.putExtra("year",Integer.parseInt(year.getSelectedItem().toString()));
                startActivity(g);
            }
        });
    }
}
