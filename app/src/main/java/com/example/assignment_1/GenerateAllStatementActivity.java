package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Objects;

public class GenerateAllStatementActivity extends AppCompatActivity {

    Spinner day;
    Spinner month;
    Spinner year;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_all_statement_activity);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_grey)));
        getSupportActionBar().setTitle("All Student Statement");

        day = findViewById(R.id.day_spinner);
        month = findViewById(R.id.month_spinner);
        year = findViewById(R.id.year_spinner);
        b1 = findViewById(R.id.gen_all_id);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g_a = new Intent(getApplicationContext(),AllStudentStatementActivity.class);
                g_a.putExtra("NurseryID", NurseryID);
                g_a.putExtra("day",Integer.parseInt(day.getSelectedItem().toString()));
                g_a.putExtra("month",Integer.parseInt(month.getSelectedItem().toString()));
                g_a.putExtra("year",Integer.parseInt(year.getSelectedItem().toString()));
                startActivity(g_a);
            }
        });
    }
}